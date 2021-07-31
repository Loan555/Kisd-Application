package com.loan555.kidsapplication.model.apiCall

import com.google.gson.annotations.SerializedName

data class LoaiTaiKhoan(
    @SerializedName("_id")
    val id: String,
    val tenLoaiTaiKhoan: String
)
