package com.example.metaweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather.ConsolidatedWeather
import com.example.weather.MetaWeather
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MetaWeather.ResultCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myRetrofit = MetaWeather()

        myRetrofit.setResult(this)
        button.setOnClickListener {
            if (editText.text.isNotEmpty())
                myRetrofit.getCurrentWeather(editText.text.toString())
        }

    }

    override fun onSuccess(consolidatedWeather: List<ConsolidatedWeather>) {
        val myDialogFragment = MyDialogFragment().newInstance(consolidatedWeather[0])
        val manager = supportFragmentManager
        myDialogFragment.show(manager, "myDialog")
    }

    override fun onFailed(msg : String) {
        val myDialogFragment = MyDialogFragment().newInstance(null)
        val manager = supportFragmentManager
        myDialogFragment.show(manager, "myDialog")
    }


}