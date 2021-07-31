package com.loan555.kidsapplication.ui.login

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.loan555.kidsapplication.R
import com.loan555.kidsapplication.database.AppPreferences
import com.loan555.kidsapplication.databinding.ActivityLoginBinding
import com.loan555.kidsapplication.model.apiCall.DataXacThuc
import com.loan555.kidsapplication.model.apiCall.LoaiTaiKhoan
import com.loan555.kidsapplication.model.apiCall.PostXacThuc
import com.loan555.kidsapplication.repository.myTag
import com.loan555.kidsapplication.ui.viewmodel.ApiTaiKhoanViewModel
import java.io.Serializable

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: ApiTaiKhoanViewModel
    private lateinit var binding: ActivityLoginBinding
    private var pass = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(ApiTaiKhoanViewModel::class.java)
        binding.viewModel = viewModel

        initController()
        initEvent()
    }

    private fun initController() {
        viewModel.getResultXacThuc().observe(this, {
            binding.loading.visibility = View.GONE
            if (it != null) {
                when (it.status) {
                    "success" -> {
                        // --------------------- nen hoi nguoi dung co muon luu tai khoan ddang nhaap ko
                        AppPreferences.init(this)
                        Log.d(myTag, "dang nhap thanh cong $it")
                        binding.mess.setTextColor(Color.parseColor("#03DAC5"))
                        var dataCustom: DataLoginCustom? = null
                        if (it.loaiTaiKhoan?.id == "60cc55e7ffdd4b0015125f8e") {
                            // tre em
                            AppPreferences.idLoaiNguoiDung = it.loaiTaiKhoan?.id
                            AppPreferences.userName = it.data?.sdt
                            AppPreferences.password = pass
                            Log.d(myTag, "dang nhap thanh cong : tre em")
                            dataCustom = DataLoginCustom(
                                it.data!!.id,
                                it.data.tenTaiKhoan,
                                it.loaiTaiKhoan.id
                            )
                        } else if (it.data?.loaiTaiKhoan?.id == "60cc55d9ffdd4b0015125f8d") {
                            // nguoi lon
                            AppPreferences.idLoaiNguoiDung = it.data?.loaiTaiKhoan?.id
                            AppPreferences.userName = it.data?.sdt
                            AppPreferences.password = pass
                            Log.d(myTag, "dang nhap thanh cong : nguoi lon")
                            dataCustom = DataLoginCustom(
                                it.data.id,
                                it.data.tenTaiKhoan,
                                it.data.loaiTaiKhoan.id
                            )
                        }
                        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                        if (dataCustom != null)
                            setDataLogin(dataCustom)
                        this.finish()
                    }
                    "failed" -> {
                        Log.d(myTag, "dang nhap that bai : ${it.status}")
                        viewModel.messLogin.value = "Không tìm thấy người dùng này"
                    }
                }
            }
        })
    }

    private fun initEvent() {
        binding.login.setOnClickListener {
            pass = binding.password.text.toString()
            binding.loading.visibility = View.VISIBLE
            val postXacThuc =
                PostXacThuc(binding.username.text.toString(), binding.password.text.toString())
            viewModel.xacThuc(postXacThuc)
        }
        binding.btnDangKy.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun setDataLogin(data: DataLoginCustom) {
        Log.d(myTag, "setData $data")
        val myIntent = Intent()
        val bundle = Bundle()
        bundle.putSerializable("dataLogin", data)
        myIntent.putExtra("bundleLogin", bundle)
        setResult(Activity.RESULT_OK, myIntent)
    }
}

data class DataLoginCustom(
    var id: String,
    var tenTaiKhoan: String,
    var loaiTaiKhoanID: String
) : Serializable