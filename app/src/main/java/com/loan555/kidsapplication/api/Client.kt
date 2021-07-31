package com.loan555.kidsapplication.api

import com.loan555.kidsapplication.model.TaiKhoanPost
import com.loan555.kidsapplication.model.apiCall.*
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface Client {
    @GET("loaithongtintuyentruyen")
    fun getLoaiThongTinTuyenTruyen(): Call<ResultCallLoaiThongTinTuyenTruyen>

    @GET("dinhdangthongtintuyentruyen")
    fun getDinhDangThongTinTuyenTruyen(): Call<ResultCallDinhDangThongTinTuyenTruyen>

    //https://murmuring-beyond-51639.herokuapp.com/thongtintuyentruyen/?limit=20
    @GET("thongtintuyentruyen/")
    fun getListThongTinTuyenTruyen(
        @Query("limit") limit: Int
    ): Call<ResultCallListThongTinTuyenTruyen>

    @GET("thongtintuyentruyen")
    fun getThongTinTuyenTruyenTheoTheLoai(
        @Query("theloai") theloai: String,
        @Query("limit") limit: Int
    ): Call<ResultCallListThongTinTuyenTruyen>

    @GET("thongtintuyentruyen/{id}")
    fun getDetail(
        @Path("id") id: String
    ): Call<ResultCallDetail>

    @FormUrlEncoded
    @POST("taikhoan/dangki")
    fun creatAccount(
        @Field("tenTaiKhoan") tenTaiKhoan: String,
        @Field("matKhau") matKhau: String,
        @Field("email") email: String,
        @Field("sdt") sdt: String,
        @Field("loaiTaiKhoan") loaiTaiKhoan: String,
        @Field("nhapLaiMatKhau") nhapLaiMatKhau: String
    ): Call<ResultCreateAccount>

    @POST("taikhoan/xacthuc")
    fun xacThuc(@Body xacThuc: PostXacThuc): Call<ResultXacThuc>

    @POST("taikhoan/taotaikhoantreem")
    fun taoTaiKhoanTreEm(
        @Header("x-auth-token") header: String,
        @Field("tenTaiKhoan") tenTaiKhoan: String,
        @Field("matKhau") matKhau: String,
        @Field("ten") ten: String,
        @Field("ngaySinh") ngaySinh: String,
        @Field("xa") xa: String,
        @Field("huyen") huyen: String,
        @Field("tinh_ThanhPho") tinh_ThanhPho: String,
        @Field("diaChiCuThe") diaChiCuThe: String,
        @Field("files") files: File,
        @Field("nhapLaiMatKhau") nhapLaiMatKhau: String
    )
}