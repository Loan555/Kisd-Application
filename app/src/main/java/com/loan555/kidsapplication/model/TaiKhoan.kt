package com.loan555.kidsapplication.model

import com.google.gson.annotations.SerializedName
import com.loan555.kidsapplication.model.apiCall.LoaiTaiKhoan

data class TaiKhoanPost(
    var tenTaiKhoan: String,
    var email: String,
    var matKhau: String,
    var sdt: String,
    var loaiTaiKhoan: String,
    var nhapLaiMatKhau: String
)