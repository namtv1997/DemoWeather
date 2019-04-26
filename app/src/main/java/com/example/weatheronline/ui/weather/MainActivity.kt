package com.example.weatheronline.ui.weather


import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.weatheronline.R
import com.example.weatheronline.adapter.IclickItemGetCity
import com.example.weatheronline.adapter.SqliteCityAdapter
import com.example.weatheronline.base.BaseActivity
import com.example.weatheronline.common.Common
import com.example.weatheronline.model.cityresult.CityResult
import com.example.weatheronline.model.sqlite.CitySql
import com.example.weatheronline.sqliteHelper.DBHelper
import com.example.weatheronline.viewmodel.WeatherViewmodel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : BaseActivity(), View.OnClickListener, IclickItemGetCity {

    private lateinit var mWeatherViewmodel: WeatherViewmodel
    private lateinit var adapterSqliteCity: SqliteCityAdapter
    private lateinit var db: DBHelper

    private var listCity: List<CitySql> = ArrayList()
    private var data: CityResult? = null

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.weatheronline.R.layout.activity_main)
        db = DBHelper(this)
        initAdapter()

        mWeatherViewmodel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            weatherCurrent.observe(this@MainActivity, android.arch.lifecycle.Observer {
                tvDegree.text = "${Math.round(it!![0].temperature.metric.value!!)}º"
                tvHumidity.text = "${it[0].relativeHumidity.toString()}%"
                tvWindSpeed.text = "${it[0].wind.speed.metric.value}km/h"

                if (it[0].relativeHumidity == 100) {

                    bg_weather_infor.setBackgroundColor(Color.parseColor("#48aca2"))
                    ivSttWeather.setImageResource(R.drawable.ic_rain)

                } else {
                    bg_weather_infor.setBackgroundColor(Color.parseColor("#d45e5e"))
                }

                val sdfCurrent = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                val dateInStringCurrent = sdfCurrent.parse(it[0].localObservationDateTime)
                sdfCurrent.applyPattern("yyyy EEEE MMMM-dd")
                val splitString = " "
                val part = sdfCurrent.format(dateInStringCurrent).split(splitString)
                tvLabelYear.text = part[0]
                tvDay.text = part[1]
                tvDate.text = part[2]


                val sdfCurrentTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                val dateInStringCurrent1 = sdfCurrentTime.parse(it[0].localObservationDateTime)
                sdfCurrentTime.applyPattern("HH:mm:ss")
                val splitString1 = ":"
                val part1 = sdfCurrentTime.format(dateInStringCurrent1).split(splitString1)
                val compareTime = part1[0].toInt()

                if (compareTime > 19) {
                    bg_weather_infor.setBackgroundColor(Color.parseColor("#343434"))
                }
            })
        }

        mWeatherViewmodel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            weather.observe(this@MainActivity, android.arch.lifecycle.Observer {

                tvTempDay1.text =
                    "${Math.round(convertFahrenheitToCelcius(it?.DailyForecasts!![1].temperature.minimum.value!!))}º"
                tvTempDay2.text =
                    "${Math.round(convertFahrenheitToCelcius(it.DailyForecasts[2].temperature.minimum.value!!))}º"
                tvTempDay3.text =
                    "${Math.round(convertFahrenheitToCelcius(it.DailyForecasts[3].temperature.minimum.value!!))}º"
                tvTempDay4.text =
                    "${Math.round(convertFahrenheitToCelcius(it.DailyForecasts[4].temperature.minimum.value!!))}º"

                val sdf5days = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                val dateInStringDay1 = sdf5days.parse(it.DailyForecasts[1].date)
                val dateInStringDay2 = sdf5days.parse(it.DailyForecasts[2].date)
                val dateInStringDay3 = sdf5days.parse(it.DailyForecasts[3].date)
                val dateInStringDay4 = sdf5days.parse(it.DailyForecasts[4].date)

                sdf5days.applyPattern("EEE")

                tvTueDay.text = sdf5days.format(dateInStringDay1)
                tvWednesday.text = sdf5days.format(dateInStringDay2)
                tvThurday.text = sdf5days.format(dateInStringDay3)
                tvFriDay.text = sdf5days.format(dateInStringDay4)
            })
        }




        ivRefresh.setOnClickListener(this)
        tvLabelSetting.setOnClickListener(this)
        llSearch.setOnClickListener(this)
    }

    private fun convertFahrenheitToCelcius(fahrenheit: Float): Float {
        return (fahrenheit - 32) * 5 / 9
    }

    private fun initAdapter() {
        listCity = db.allCity
        adapterSqliteCity = SqliteCityAdapter(listCity, this)
        rvItemLocation.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = adapterSqliteCity
        }

    }

    override fun onResume() {
        super.onResume()
        if (intent.extras != null) {
            data = intent.getParcelableExtra<CityResult>("dataCity")
            mWeatherViewmodel.getDataWeatherCurrent(data!!.key!!, Common.API_Key9, true)
            mWeatherViewmodel.getDataWeather5days(data!!.key!!, Common.API_Key9, true)
            val citySql = CitySql(
                data!!.key!!,
                data!!.localizedName!!
            )
            initAdapter()
            db.addCity(citySql)
            tvLabelLocation.text = data!!.localizedName
        }
    }

    override fun onItemClickGetCity(city: CitySql) {
        mWeatherViewmodel.getDataWeatherCurrent(city.key!!, Common.API_Key9, true)
        mWeatherViewmodel.getDataWeather5days(city.key!!, Common.API_Key9, true)
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
            com.example.weatheronline.R.id.ivRefresh -> {
                if (data?.key != null) {
                    mWeatherViewmodel.getDataWeatherCurrent(data!!.key!!, Common.API_Key9, true)
                }

            }
        }
    }


    override fun onStop() {
        super.onStop()
        drawerLayout.closeDrawers()

    }

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
}
