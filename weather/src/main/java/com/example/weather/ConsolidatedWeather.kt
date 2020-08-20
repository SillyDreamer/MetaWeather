package com.example.weather

data class ConsolidatedWeather(val weather_state_name : String,
                               val min_temp : Float,
                               val max_temp : Float,
                               val the_temp : Float,
                               val applicable_date : String)