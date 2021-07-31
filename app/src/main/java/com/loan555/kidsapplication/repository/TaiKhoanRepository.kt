package com.loan555.kidsapplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.loan555.kidsapplication.api.ApiConfig
import com.loan555.kidsapplication.api.Client
import com.loan555.kidsapplication.model.TaiKhoanPost
import com.loan555.kidsapplication.model.apiCall.DataXacThuc
import com.loan555.kidsapplication.model.apiCall.PostXacThuc
import com.loan555.kidsapplication.model.apiCall.ResultCreateAccount
import com.loan555.kidsapplication.model.apiCall.ResultXacThuc
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaiKhoanRepository {

    val resultCreateAccount: MutableLiveData<ResultCreateAccount> = MutableLiveData()
    val resultXacThuc: MutableLiveData<ResultXacThuc> = MutableLiveData()

    private val client: Client = ApiConfig.retrofit.create(Client::class.java)

    fun createAccount(account: TaiKhoanPost) {
        val call = client.creatAccount(
            account.tenTaiKhoan,
            account.matKhau,
            account.email,
            account.sdt,
            account.loaiTaiKhoan,
            account.nhapLaiMatKhau
        )
        call.enqueue(object : Callback<ResultCreateAccount> {
            override fun onResponse(
                call: Call<ResultCreateAccount>?,
                response: Response<ResultCreateAccount>?
            ) {
                if (response?.body() != null) {
                    resultCreateAccount.value = response.body()
                } else {
                    Log.e(myTag, "createAccount error code = ${response?.code()}")
                    when (response?.code()) {
                        400 -> {
                            resultCreateAccount.value =
                                ResultCreateAccount(
                                    "failed",
                                    "",
                                    "Không hợp lệ"
                                )
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResultCreateAccount>?, t: Throwable?) {
                Log.e(myTag, "Error call api createAccount : ${t?.message}")
                if (t != null) {
                    resultCreateAccount.value =
                        ResultCreateAccount("failed", "", t.message!!)
                }
            }
        })
    }

    fun xacThuc(xacThuc: PostXacThuc): MutableLiveData<ResultXacThuc> {
        val call = client.xacThuc(xacThuc)
        call.enqueue(object : Callback<ResultXacThuc> {
            override fun onResponse(
                call: Call<ResultXacThuc>?,
                response: Response<ResultXacThuc>?
            ) {
                if (response?.body() != null) {
                    resultXacThuc.value = response.body()
                } else {
                    Log.e(myTag, "xacThuc error code = ${response?.code()}")
                    resultXacThuc.value =
                        ResultXacThuc("failed", null, null, null)
                }
            }

            override fun onFailure(call: Call<ResultXacThuc>?, t: Throwable?) {
                Log.e(myTag, "Error call api xacThuc : ${t?.message}")
                resultXacThuc.value = ResultXacThuc("failed", null, null, null)
            }

        })
        return resultXacThuc
    }

}