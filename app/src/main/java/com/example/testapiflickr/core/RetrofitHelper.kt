package com.example.testapiflickr.core

import com.example.testapiflickr.constants.NetworkConstants.HOSTNAME
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HOSTNAME)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}