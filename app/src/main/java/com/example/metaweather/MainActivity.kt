package com.example.metaweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.weather.Location
import com.example.weather.MyRetrofit
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loc = MyRetrofit().getLocation()
        loc.enqueue(object : Callback<List<Location>> {
            override fun onResponse(
                call: Call<List<Location>>,
                response: Response<List<Location>>
            ) {
                text.text = response.body().toString()
            }

            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                Log.e("TAG", "ERROR $t")
            }
        })
    }
}