package com.loan555.kidsapplication.ui.main.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.loan555.kidsapplication.R
import com.loan555.kidsapplication.databinding.MainFragmentBinding
import com.loan555.kidsapplication.model.TaiKhoanPost
import com.loan555.kidsapplication.model.apiCall.PostXacThuc
import com.loan555.kidsapplication.ui.main.MainViewModel
import com.loan555.kidsapplication.ui.viewmodel.ApiTaiKhoanViewModel
import java.lang.Exception

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var apiViewModel: ApiTaiKhoanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        viewModel = activity?.let {
            ViewModelProviders.of(it).get(MainViewModel::class.java)
        } ?: throw Exception("Activity is null")
        apiViewModel = ViewModelProvider(this).get(ApiTaiKhoanViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLoaiTin().observe(viewLifecycleOwner, {
            if (it.size != 0)
                binding.message.text = it.toString()
            else
                binding.message.text = "danh sach rong"
        })

        viewModel.getDinhDangThongTinTuyenTruyen().observe(viewLifecycleOwner, {
            if (it.size != 0)
                binding.message.text = it.toString()
            else
                binding.message.text = "danh sach rong"
        })
        viewModel.getListThongTinTuyenTruyen().observe(viewLifecycleOwner, {
            if (it.size != 0)
                binding.message.text = it.toString()
            else
                binding.message.text = "danh sach rong"
        })
        viewModel.getListThongTinTuyenTruyenTheoTheLoai().observe(viewLifecycleOwner, {
            if (it.size != 0)
                binding.message.text = it.toString()
            else
                binding.message.text = "danh sach rong"
        })
        viewModel.getDetail().observe(viewLifecycleOwner, {
            if (it != null)
                binding.message.text = it.toString()
            else
                binding.message.text = "detail rong"
        })
        apiViewModel.getResultTaoTaiKhoan().observe(viewLifecycleOwner, {
            if (it != null) binding.message.text = it.toString()
        })
        apiViewModel.getResultXacThuc().observe(viewLifecycleOwner, {
            if (it != null) binding.message.text = it.toString()
        })

        initData()
        initEvent()
    }

    private fun initEvent() {
        binding.btnListTheLoai.setOnClickListener {
            viewModel.loadListThongTinTheoTheLoai("60fa187db9a87b00155391dd", 10)
        }
        binding.btnGetDetail.setOnClickListener {
            viewModel.loadDetail("60fa4b1da68e470015708dae")
        }
        binding.btnXacThuc.setOnClickListener {
            apiViewModel.xacThuc(
                PostXacThuc(
                    binding.name.text.toString(),
                    binding.password.text.toString()
                )
            )
        }
        binding.taoTaiKhoan.setOnClickListener {
            apiViewModel.taoTaiKhoan(
                TaiKhoanPost(
                    binding.name.text.toString(),
                    binding.email.text.toString(),
                    binding.password.text.toString(),
                    binding.editTextPhone.text.toString(),
                    "60cc55d9ffdd4b0015125f8d",
                    binding.password.text.toString()
                )
            )
        }
    }

    private fun initData() {
//        viewModel.loadLoaiTin()
    }

}