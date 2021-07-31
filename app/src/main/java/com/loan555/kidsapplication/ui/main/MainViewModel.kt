package com.loan555.kidsapplication.ui.main

import androidx.lifecycle.ViewModel
import com.loan555.kidsapplication.repository.CallApiRepository

class MainViewModel : ViewModel() {
    val callApiRepository = CallApiRepository()

    fun getLoaiTin() = callApiRepository.listLoaiThongTinTuyenTruyen

    fun loadLoaiTin() = callApiRepository.getLoaiThongTinTuyenTruyen()

    fun getDinhDangThongTinTuyenTruyen() = callApiRepository.listDinhDangThongTinTuyenTruyen

    fun loadDinhDang() = callApiRepository.getDinhDangThongTinTuyenTruyen()

    fun getListThongTinTuyenTruyen() = callApiRepository.listThongTinTuyenTruyen

    fun loadListThongTin(limit: Int) = callApiRepository.getListThongTinTuyenTruyen(limit)

    fun getListThongTinTuyenTruyenTheoTheLoai() = callApiRepository.listThongTinTuyenTruyen

    fun loadListThongTinTheoTheLoai(theLoai: String, limit: Int) =
        callApiRepository.getThongTinTuyenTruyenTheoTheLoai(theLoai, limit)

    fun getDetail() = callApiRepository.detail

    fun loadDetail(id: String) = callApiRepository.getDetail(id)

}