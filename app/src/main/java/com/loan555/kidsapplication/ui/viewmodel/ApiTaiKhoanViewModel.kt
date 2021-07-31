package com.loan555.kidsapplication.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loan555.kidsapplication.model.TaiKhoanPost
import com.loan555.kidsapplication.model.apiCall.DataXacThuc
import com.loan555.kidsapplication.model.apiCall.PostXacThuc
import com.loan555.kidsapplication.model.apiCall.ResultXacThuc
import com.loan555.kidsapplication.repository.TaiKhoanRepository
import com.loan555.kidsapplication.ui.login.DataLoginCustom

class ApiTaiKhoanViewModel : ViewModel() {
    val messLogin: MutableLiveData<String> = MutableLiveData("")

    val accLogin: MutableLiveData<DataLoginCustom> = MutableLiveData()

    private val callApiRepository = TaiKhoanRepository()

    fun getResultTaoTaiKhoan() = callApiRepository.resultCreateAccount
    fun getResultXacThuc() = callApiRepository.resultXacThuc

    fun taoTaiKhoan(acc: TaiKhoanPost) = callApiRepository.createAccount(acc)

    fun xacThuc(xt: PostXacThuc) = callApiRepository.xacThuc(xt)

}