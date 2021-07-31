package com.loan555.kidsapplication.ui.detailnew.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loan555.kidsapplication.model.Detail

class DetailViewModel : ViewModel() {
    val detail : MutableLiveData<Detail> = MutableLiveData()
}