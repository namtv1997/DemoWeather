package com.example.weatheronline.adapter


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.weatheronline.model.ListWeatherInfor
import com.example.weatheronline.model.WeatherResult
import com.example.weatheronline.ui.weather.WeatherFragment

class WeatherPagerAdapter(manage: FragmentManager, val listWeather : ArrayList<ArrayList<ListWeatherInfor>>, var nameOfCity:WeatherResult) : FragmentPagerAdapter(manage)  {

    override fun getItem(position: Int): Fragment {

        val fragmentWeather = WeatherFragment()

        fragmentWeather.arguments = Bundle().apply {
            putSerializable("dataOneDay",listWeather[position])
            putSerializable("dataNameOfCity",nameOfCity)
        }
        return fragmentWeather
    }

    override fun getCount(): Int = listWeather.size
}