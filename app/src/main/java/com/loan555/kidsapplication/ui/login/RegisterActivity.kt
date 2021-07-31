package com.loan555.kidsapplication.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.loan555.kidsapplication.R
import com.loan555.kidsapplication.model.TaiKhoanPost
import com.loan555.kidsapplication.ui.viewmodel.ApiTaiKhoanViewModel
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.main_fragment.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var viewModel: ApiTaiKhoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProvider(this).get(ApiTaiKhoanViewModel::class.java)

        initController()
        initEvent()
        initData()
    }

    private fun initController() {
        viewModel.getResultTaoTaiKhoan().observe(this, {
            if (it != null) {
                if (it.status != "failed") {
                    Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    mess.text = it.message
                }
            }
            loadingDangKy.visibility = View.GONE
        })
    }

    private fun initEvent() {
        dangKy.setOnClickListener {
            val acc = TaiKhoanPost(
                tenDangNhap.text.toString(),
                mail.text.toString(),
                matKhau.text.toString(),
                sdt.text.toString(),
                "60cc55d9ffdd4b0015125f8d",
                nhapLaiPass.text.toString()
            )
            viewModel.taoTaiKhoan(acc)
            loadingDangKy.visibility = View.VISIBLE
        }
        btnDangNhap.setOnClickListener {
            finish()
        }
    }

    private fun initData() {

    }

}