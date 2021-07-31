package com.loan555.kidsapplication.ui.main.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val title: MutableLiveData<String> = MutableLiveData("Trang chủ")
    val danhMucHienTai : MutableLiveData<String> = MutableLiveData("trangchu")
    val isPageLoading : MutableLiveData<Boolean> = MutableLiveData(true)
}