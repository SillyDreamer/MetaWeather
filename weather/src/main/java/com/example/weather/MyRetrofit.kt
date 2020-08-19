package com.example.weather

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log


@Suppress("UNREACHABLE_CODE")
class MyRetrofit {
    fun getLocation(): Call<List<Location>> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.metaweather.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val locationApi : LocationApi = retrofit.create(LocationApi::class.java)

        val location : Call<List<Location>> = locationApi.location("moscow")

        return location


    }
}