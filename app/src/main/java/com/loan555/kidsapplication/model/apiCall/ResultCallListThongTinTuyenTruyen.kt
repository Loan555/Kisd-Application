package com.loan555.kidsapplication.model.apiCall

import com.loan555.kidsapplication.model.ThongTinTuyenTruyen

data class ResultCallListThongTinTuyenTruyen(
    val status: String,
    val totalResults: Long,
    val thongTinTuyenTruyen: List<ThongTinTuyenTruyen>
)
