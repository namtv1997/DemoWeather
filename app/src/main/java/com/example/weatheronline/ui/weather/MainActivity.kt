package com.example.weatheronline.ui.weather


import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.example.weatheronline.R
import com.example.weatheronline.base.BaseActivity
import com.example.weatheronline.common.Common
import com.example.weatheronline.model.cityresult.CityResult
import com.example.weatheronline.viewmodel.WeatherViewmodel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mWeatherViewmodel: WeatherViewmodel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        mWeatherViewmodel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            weatherCurrent.observe(this@MainActivity, android.arch.lifecycle.Observer {
                tvDegree.text="${Math.round(it!![0].temperature.metric.value!!)}º"
                tvHumidity.text = "${it!![0].relativeHumidity.toString()}%"
                tvWindSpeed.text="${it!![0].wind.speed.metric.value}km/h"

            })
        }

        mWeatherViewmodel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            weather.observe(this@MainActivity, android.arch.lifecycle.Observer {

            })
        }

        if (intent.extras != null) {
            val data = intent.getParcelableArrayListExtra<CityResult>("dataCity")
            val position = intent.getIntExtra("position", 0)
            mWeatherViewmodel.getDataWeatherCurrent(data[position].key!!, Common.API_Key4, true)
            mWeatherViewmodel.getDataWeather5days(data[position].key!!, Common.API_Key4, true)
            tvLabelLocation.text = data[position].localizedName
        }



        tvLabelSetting.setOnClickListener(this)
        llSearch.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvLabelSetting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
            R.id.llSearch -> {
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
