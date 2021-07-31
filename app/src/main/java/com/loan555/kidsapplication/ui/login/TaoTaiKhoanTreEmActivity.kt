package com.loan555.kidsapplication.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.loan555.kidsapplication.R
import com.loan555.kidsapplication.ui.viewmodel.ApiTaiKhoanViewModel
import kotlinx.android.synthetic.main.activity_tao_tai_khoan_tre_em.*

class TaoTaiKhoanTreEmActivity : AppCompatActivity() {

    private lateinit var viewModel: ApiTaiKhoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ApiTaiKhoanViewModel::class.java)
        setContentView(R.layout.activity_tao_tai_khoan_tre_em)

        initController()
        initEvent()
    }

    private fun initEvent() {
        btnTaoTaiKhoanTreEm.setOnClickListener {

        }
    }

    private fun initController() {

    }
}