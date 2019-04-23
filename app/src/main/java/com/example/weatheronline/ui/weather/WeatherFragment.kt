package com.example.weatheronline.ui.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatheronline.model.ListWeatherInfor
import com.example.weatheronline.model.WeatherResult
import kotlinx.android.synthetic.main.fragment_weather_temp.*
import java.text.SimpleDateFormat

class WeatherFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.example.weatheronline.R.layout.fragment_weather_temp, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        val weatherResult = arguments?.getSerializable("dataOneDay") as ArrayList<ListWeatherInfor>
//        val cityResult = arguments?.getSerializable("dataNameOfCity") as WeatherResult
//
//        tvCity.text = cityResult.city.name
//
//        showDataWeather(weatherResult)
//        pbHumidity.max = 100
//        pbWind.max = 13
//
//        when {
//            tvsttWeather.text == "Clouds" -> {
//                imageView.setImageResource(com.example.weatheronline.R.drawable.bg_cloudy)
//                imgWeather.setImageResource(com.example.weatheronline.R.drawable.ic_cloudy)
//            }
//            tvsttWeather.text == "Rain" -> {
//                imageView.setImageResource(com.example.weatheronline.R.drawable.bg_rain)
//                imgWeather.setImageResource(com.example.weatheronline.R.drawable.ic_rain)
//            }
//            tvsttWeather.text == "Clear" -> {
//                imageView.setImageResource(com.example.weatheronline.R.drawable.bg_snow)
//                imgWeather.setImageResource(com.example.weatheronline.R.drawable.ic_cloudy)
//            }
//        }


    }

//    @SuppressLint("SetTextI18n", "SimpleDateFormat")
//    fun showDataWeather(weather: ArrayList<ListWeatherInfor>) {
//        for (i in 0 until weather.size) {
//            val kelvin = weather[i].main?.temp
//            val celsius = kelvin?.minus(273.15)
//            val result = Math.round(celsius!!).toString()
//            val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//            val dateInString = sdf.parse(weather[i].dtTxt)
//            sdf.applyPattern("hh:mm a - dd MMM yy")
//
//            tvTemperature.text = "$result\u00B0"
//            tvTime.text = sdf.format(dateInString)
//            tvsttWeather.text = weather[i].weather!![0].main
//            tvWindSpeed.text = weather[i].wind?.speed.toString()
//            pbWind.progress = weather[i].wind?.speed!!.toInt()
//            tvHumidityAmount.text = weather[i].main?.humidity.toString()
//            pbHumidity.progress = weather[i].main?.humidity!!.toInt()
//        }
//
//    }
}
