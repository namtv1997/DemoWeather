package com.example.weatheronline.ui.weather.searchcity

import com.example.weatheronline.model.cityresult.CityResult
import com.example.weatheronline.model.weathercurentday.WeatherCurent

interface
IClickItemListener {
    fun onItemClick(city: CityResult)
}