package com.loan555.kidsapplication.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DinhDangThongTinTuyenTruyen(
    @SerializedName("_id")
    val id: String,
    val tenDinhDang: String,
    @SerializedName("__v")
    val v: Long
):Serializable
