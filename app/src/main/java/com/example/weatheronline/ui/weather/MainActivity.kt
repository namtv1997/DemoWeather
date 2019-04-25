package com.example.weatheronline.ui.weather


import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.weatheronline.base.BaseActivity
import com.example.weatheronline.common.Common
import com.example.weatheronline.model.cityresult.CityResult
import com.example.weatheronline.viewmodel.WeatherViewmodel
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mWeatherViewmodel: WeatherViewmodel

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.weatheronline.R.layout.activity_main)



        mWeatherViewmodel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            weatherCurrent.observe(this@MainActivity, android.arch.lifecycle.Observer {
                tvDegree.text = "${Math.round(it!![0].temperature.metric.value!!)}º"
                tvHumidity.text = "${it[0].relativeHumidity.toString()}%"
                tvWindSpeed.text = "${it[0].wind.speed.metric.value}km/h"

                if (it[0].relativeHumidity==100){

                    bg_weather_infor.setBackgroundColor(Color.parseColor("#48aca2"))

                }else{
                    bg_weather_infor.setBackgroundColor(Color.parseColor("#d45e5e"))
                }
//                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+|-hh:mm")
//                val dateInString = sdf.parse(it[0].localObservationDateTime)
//              //  sdf.applyPattern("hh:mm a - dd MMM yy")
//                sdf.applyPattern("yyyy EEE MMM-dd")
//                tvLabelYear.text = sdf.format(dateInString)

            })
        }

        mWeatherViewmodel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            weather.observe(this@MainActivity, android.arch.lifecycle.Observer {
                tvTempDay1.text= "${Math.round(convertFahrenheitToCelcius(it?.DailyForecasts!![1].temperature.minimum.value!!))}º"
                tvTempDay2.text= "${Math.round(convertFahrenheitToCelcius(it.DailyForecasts[2].temperature.minimum.value!!))}º"
                tvTempDay3.text= "${Math.round(convertFahrenheitToCelcius(it.DailyForecasts[3].temperature.minimum.value!!))}º"
                tvTempDay4.text= "${Math.round(convertFahrenheitToCelcius(it.DailyForecasts[4].temperature.minimum.value!!))}º"
            })
        }

        if (intent.extras != null) {
            val data = intent.getParcelableArrayListExtra<CityResult>("dataCity")
            val position = intent.getIntExtra("position", 0)
            mWeatherViewmodel.getDataWeatherCurrent(data[position].key!!, Common.API_Key5, true)
            mWeatherViewmodel.getDataWeather5days(data[position].key!!, Common.API_Key5, true)
            tvLabelLocation.text = data[position].localizedName
        }



        tvLabelSetting.setOnClickListener(this)
        llSearch.setOnClickListener(this)
    }

    private fun convertFahrenheitToCelcius(fahrenheit: Float): Float {
        return (fahrenheit - 32) * 5 / 9
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            com.example.weatheronline.R.id.tvLabelSetting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
            com.example.weatheronline.R.id.llSearch -> {
                val intent = Intent(this, SearchCityActivity::class.java)
                startActivity(intent)
            }
        }
    }


    override fun onStop() {
        super.onStop()
        drawerLayout.closeDrawers()

    }

}
