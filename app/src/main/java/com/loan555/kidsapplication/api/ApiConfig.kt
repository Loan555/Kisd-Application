package com.loan555.kidsapplication.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private const val BaseUrl = "https://murmuring-beyond-51639.herokuapp.com/"
    private val builder = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
    val retrofit = builder.build()!!
}