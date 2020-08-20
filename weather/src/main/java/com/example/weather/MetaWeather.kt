package com.example.weather

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MetaWeather {

    lateinit var locationApi : LocationApi

    interface ResultCallback {
        fun onSuccess(consolidatedWeather: List<ConsolidatedWeather>)
        fun onFailed(msg : String)
    }

    lateinit var mResultCallback: ResultCallback

    fun setResult(resultCallback: ResultCallback) {
        mResultCallback = resultCallback
    }

    fun getCurrentWeather(location : String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.metaweather.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        locationApi = retrofit.create(LocationApi::class.java)

        val loco : Call<List<Location>> = locationApi.location(location)

        loco.enqueue(object : Callback<List<Location>> {
            override fun onResponse(
                call: Call<List<Location>>,
                response: Response<List<Location>>
            ) {

                if (response.isSuccessful) {
                    if (!response.body().isNullOrEmpty())
                        getWeather(response.body()?.get(0)?.woeid)
                    else
                        mResultCallback.onFailed("body is empty")
                }
                else {
                    mResultCallback.onFailed(response.errorBody().toString())
                }

            }

            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                mResultCallback.onFailed("${t.message}")
            }
        })

    }

    fun getWeather(woeid : Int?) {
        val weather = woeid?.let { locationApi.weather(it) }
        weather?.enqueue(object : Callback<Weather> {
            override fun onFailure(call: Call<Weather>, t: Throwable) {
                mResultCallback.onFailed("${t.message}")
            }

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if (response.isSuccessful) {
                    response.body()?.consolidated_weather?.let { mResultCallback.onSuccess(it) }
                }
                else {
                    mResultCallback.onFailed(response.errorBody().toString())
                }
            }
        })

    }
}