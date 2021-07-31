package com.loan555.kidsapplication.model.apiCall

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PostXacThuc(val nguoiDung: String, val matKhau: String)

data class ResultXacThuc(
    val status: String,
    val data: DataXacThuc?,
    val token: String?,
    val loaiTaiKhoan: LoaiTaiKhoan?
): Serializable

data class DataXacThuc(
    @SerializedName("_id")
    val id: String,

    val email: String,
    val tenTaiKhoan: String,
    val sdt: String,
    val ngayDangKi: String,
    val tinhTrang: String,
    val loaiTaiKhoan : LoaiTaiKhoan?
): Serializable