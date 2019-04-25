package com.example.weatheronline.adapter

import com.example.weatheronline.model.cityresult.CityResult
import com.example.weatheronline.model.weathercurentday.WeatherCurent

interface IClickItemListener {
    fun onItemClick(listCity: ArrayList<CityResult>, position: Int)
}