package com.loan555.kidsapplication.ui.main.account

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.loan555.kidsapplication.R
import com.loan555.kidsapplication.database.AppPreferences
import com.loan555.kidsapplication.databinding.AccountFragmentBinding
import com.loan555.kidsapplication.model.apiCall.DataXacThuc
import com.loan555.kidsapplication.model.apiCall.PostXacThuc
import com.loan555.kidsapplication.model.apiCall.ResultXacThuc
import com.loan555.kidsapplication.model.view.ThongTinThanhVienAdapter
import com.loan555.kidsapplication.repository.myTag
import com.loan555.kidsapplication.ui.MainActivityJava
import com.loan555.kidsapplication.ui.adapter.AccountAdapter
import com.loan555.kidsapplication.ui.login.DataLoginCustom
import com.loan555.kidsapplication.ui.login.LoginActivity
import com.loan555.kidsapplication.ui.login.TaoTaiKhoanTreEmActivity
import com.loan555.kidsapplication.ui.viewmodel.ApiTaiKhoanViewModel
import java.lang.Exception
import java.util.jar.Manifest

class AccountFragment : Fragment() {

    companion object {
        fun newInstance() = AccountFragment()
    }

    private lateinit var viewModel: ApiTaiKhoanViewModel
    private lateinit var binding: AccountFragmentBinding

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data: Intent? = it.data
                doPlay(data)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.account_fragment, container, false)
        viewModel = activity?.let {
            ViewModelProviders.of(it).get(ApiTaiKhoanViewModel::class.java)
        } ?: throw Exception("activity is null")
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initController()
        initEvent()
        initData()
    }

    private fun initController() {
        viewModel.accLogin.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.recycleView.visibility = View.VISIBLE
                binding.btnLogout.visibility = View.VISIBLE
                binding.tenDangNha.text = it.tenTaiKhoan
                binding.btnDangNhapDangKy.visibility = View.GONE
                binding.recycleView.layoutManager =
                    GridLayoutManager(this.context, 2, LinearLayoutManager.HORIZONTAL, false)
                val adapter: AccountAdapter
                if (it.loaiTaiKhoanID == "60cc55d9ffdd4b0015125f8d") {
                    Log.d(myTag, "Load view account nguoi bao ho o day")
                    adapter = AccountAdapter(this.requireContext(), onItemClick).apply {
                        var newList: List<ThongTinThanhVienAdapter> = listOf()
                        newList += ThongTinThanhVienAdapter(null, "Tạo tài khoản trẻ em")
                        setList(newList)
                    }

                } else {
                    Log.d(myTag, "Load view account tre em o day")
                    adapter = AccountAdapter(this.requireContext(), onItemClick).apply {
                        var newList: List<ThongTinThanhVienAdapter> = listOf()
                        newList += ThongTinThanhVienAdapter(null, "Thêm thông tin người thân")
                        setList(newList)
                    }
                }
                binding.recycleView.adapter = adapter
            } else {
                binding.recycleView.visibility = View.GONE
                binding.btnLogout.visibility = View.GONE
                binding.tenDangNha.text = "Bạn chưa đăng nhập "
                binding.btnDangNhapDangKy.visibility = View.VISIBLE
            }
        })
    }

    private fun initEvent() {
        binding.btnDangNhapDangKy.setOnClickListener {
//            startActivity(Intent(this.context,MainActivityJava::class.java))
            startLoginActivity()
        }
        binding.btnLogout.setOnClickListener {
            viewModel.accLogin.value = null
            AppPreferences.init(this.requireContext())
            AppPreferences.userName = ""
            AppPreferences.password = ""
        }
    }

    private fun initData() {
        // ------------------- cho nay de lay data tu sharêrentces
        //------------------ kiem tra dang nhap
        AppPreferences.init(this.requireContext())
        val username = AppPreferences.userName
        val pass = AppPreferences.password
        if (username != "" && pass != "") {
            viewModel.xacThuc(PostXacThuc(username!!, pass!!)).observe(viewLifecycleOwner, {
                if (it != null) {
                    Log.d(myTag, "AppPreferences = $username/$pass")
                    var dataCustom: DataLoginCustom? = null
                    if (it.loaiTaiKhoan?.id == "60cc55e7ffdd4b0015125f8e") {
                        // tre em
                        Log.d(myTag, "dang nhap thanh cong : tre em")
                        dataCustom = DataLoginCustom(
                            it.data!!.id,
                            it.data.tenTaiKhoan,
                            it.loaiTaiKhoan.id
                        )
                    } else if (it.data?.loaiTaiKhoan?.id == "60cc55d9ffdd4b0015125f8d") {
                        // nguoi lon
                        Log.d(myTag, "dang nhap thanh cong : nguoi lon")
                        dataCustom = DataLoginCustom(
                            it.data.id,
                            it.data.tenTaiKhoan,
                            it.data.loaiTaiKhoan.id
                        )
                    }
                    if (dataCustom != null) {
                        viewModel.accLogin.value = dataCustom
                    }
                }
            })
        }
    }

    private fun doPlay(intent: Intent?) {
        Log.d(myTag, "doPlay ")
        val bundle = intent?.getBundleExtra("bundleLogin")
        if (bundle != null) {
            val loginData = bundle.getSerializable("dataLogin") as DataLoginCustom
            viewModel.accLogin.value = loginData
            Log.d(myTag, "doPlay $loginData")
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(this.context, LoginActivity::class.java)
        resultLauncher.launch(intent)
    }

    private val onItemClick: (Int, List<ThongTinThanhVienAdapter>) -> Unit = { pos, info ->
        if (pos == 0) {
            Log.d(myTag, " click ${info[0].thongTin}")

            AppPreferences.init(this.requireContext())
            when (AppPreferences.idLoaiNguoiDung) {
                "60cc55d9ffdd4b0015125f8d" -> {
                    // nguoi lon
                    onClickRequestPermistion()
//                    startActivity(Intent(this.context, TaoTaiKhoanTreEmActivity::class.java))
                }
                "60cc55e7ffdd4b0015125f8e" -> {
                    //Tre em
                }
            }
        } else {
            Log.d(myTag, " click ${info[pos].thongTin}")
        }
    }

    private fun onClickRequestPermistion() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        } else {
            if (this.context?.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(Intent(this.context, TaoTaiKhoanTreEmActivity::class.java))
            } else {
                val permission = listOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                if (activity != null) {
                    ActivityCompat.requestPermissions(
                        this.requireActivity(),
                        permission.toTypedArray(), 1
                    )
                }
            }

        }
    }

    private fun openGallery() {
        Log.d(myTag, "openGallery")
    }
}