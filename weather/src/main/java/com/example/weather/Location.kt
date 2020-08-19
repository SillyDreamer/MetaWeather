package com.example.weather

data class Location(private val title : String,
                    private val location_type : String,
                    private val woeid : Int,
                    private val latt_long: String)