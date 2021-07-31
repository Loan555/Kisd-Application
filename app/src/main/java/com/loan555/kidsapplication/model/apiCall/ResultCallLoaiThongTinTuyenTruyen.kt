package com.loan555.kidsapplication.model.apiCall

import com.loan555.kidsapplication.model.LoaiThongTinTuyenTruyen

data class ResultCallLoaiThongTinTuyenTruyen(
    val status: Int,
    val data: List<LoaiThongTinTuyenTruyen>
)
