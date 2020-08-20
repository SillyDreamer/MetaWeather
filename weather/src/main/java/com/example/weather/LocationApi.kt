package com.example.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface LocationApi {

    @GET("location/search/")
    fun location(@Query("query") query : String) : Call<List<Location>>

    @GET("location/{woeid}")
    fun weather(@Path("woeid") woeid : Int): Call<Weather>
}