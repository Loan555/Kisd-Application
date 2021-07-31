package com.loan555.kidsapplication.model.apiCall

import com.loan555.kidsapplication.model.DinhDangThongTinTuyenTruyen

data class ResultCallDinhDangThongTinTuyenTruyen(
    val status: Int,
    val data: List<DinhDangThongTinTuyenTruyen>
)
