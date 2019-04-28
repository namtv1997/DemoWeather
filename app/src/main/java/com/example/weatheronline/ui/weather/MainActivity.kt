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
import kotlinx.android.synthetic.main.activity_setting.*
import namhenry.com.vn.projectweek4.utills.SharePrefs
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

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun innitViewModelCurrent() {
        mWeatherViewmodel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            weatherCurrent.observe(this@MainActivity, android.arch.lifecycle.Observer {


                if (it!![0].relativeHumidity == 100) {

                    bg_weather_infor.setBackgroundColor(Color.parseColor("#48aca2"))
                    ivSttWeather.setImageResource(R.drawable.ic_rain)

                } else {
                    ivSttWeather.setImageResource(R.drawable.ic_sun)
                    bg_weather_infor.setBackgroundColor(Color.parseColor("#d45e5e"))
                }

                val getDegree = SharePrefs().getInstance()["KEY_TYPE_DEGREE_CUSTOM_SELECTED", Int::class.java]
                when (getDegree) {
                    Common.Type_Degree_C -> {
                        tvDegree.text = "${Math.round(it[0].temperature.metric.value!!)}º"
                    }
                    Common.Type_Degree_F -> {
                        tvDegree.text = "${Math.round(it[0].temperature.imperial.value!!)}ºF"


                    }
                    Common.Type_Degree_K -> {
                        val celsius = it[0].temperature.metric.value!!
                        val kelvin = celsius.plus(273.15)
                        tvDegree.text = "${Math.round(kelvin)}ºK"

                    }
                }
                val getHumidity = SharePrefs().getInstance()["KEY_TYPE_HUMIDITY_CUSTOM_SELECTED", Int::class.java]
                when (getHumidity) {
                    Common.Type_HUMIDITY_PERCENT -> {
                        tvHumidity.text = "${it!![0].relativeHumidity.toString()}%"
                    }
                    Common.Type_HUMIDITY_ABSOLUTE -> {
                        tvHumidity.text = "${Math.round(
                            calculateAbsoluteHumidity(
                                it!![0].temperature.metric.value!!,
                                it[0].relativeHumidity!!
                            )
                        )}g/m3"
                    }
                }
                val getWind = SharePrefs().getInstance()["KEY_TYPE_WIND_CUSTOM_SELECTED", Int::class.java]
                when (getWind) {
                    Common.Type_WIND_KM -> {
                        tvWindSpeed.text = "${it[0].wind.speed.metric.value}km/h"
                    }
                    Common.Type_WIND_MILES -> {
                        tvWindSpeed.text = "${it[0].wind.speed.imperial.value}miles/h"
                    }

                }

                val sdfCurrent = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                val dateInStringCurrent = sdfCurrent.parse(it[0].localObservationDateTime)
                sdfCurrent.applyPattern("yyyy EEEE MMMM-dd")
                val splitString = " "
                val part = sdfCurrent.format(dateInStringCurrent).split(splitString)
                tvLabelYear.text = part[0]
                tvDay.text = part[1]
                tvDate.text = part[2]

                sdfCurrent.applyPattern("HH:mm:ss")
                val splitString1 = ":"
                val part1 = sdfCurrent.format(dateInStringCurrent).split(splitString1)
                val compareTime = part1[0].toInt()

                if (compareTime > 19) {
                    bg_weather_infor.setBackgroundColor(Color.parseColor("#343434"))
                }
            })
        }
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun innitViewModel5days() {
        mWeatherViewmodel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            weather.observe(this@MainActivity, android.arch.lifecycle.Observer {


                val getDegree = SharePrefs().getInstance()["KEY_TYPE_DEGREE_CUSTOM_SELECTED", Int::class.java]

                when (getDegree) {
                    Common.Type_Degree_C -> {
                        tvTempDay1.text =
                            "${Math.round(convertFahrenheitToCelcius(it?.DailyForecasts!![1].temperature.minimum.value!!))}º"
                        tvTempDay2.text =
                            "${Math.round(convertFahrenheitToCelcius(it.DailyForecasts[2].temperature.minimum.value!!))}º"
                        tvTempDay3.text =
                            "${Math.round(convertFahrenheitToCelcius(it.DailyForecasts[3].temperature.minimum.value!!))}º"
                        tvTempDay4.text =
                            "${Math.round(convertFahrenheitToCelcius(it.DailyForecasts[4].temperature.minimum.value!!))}º"


                    }
                    Common.Type_Degree_F -> {
                        tvTempDay1.text =
                            "${it?.DailyForecasts!![1].temperature.minimum.value!!}ºF"
                        tvTempDay2.text =
                            "${it.DailyForecasts[2].temperature.minimum.value!!}ºF"
                        tvTempDay3.text =
                            "${it.DailyForecasts[3].temperature.minimum.value!!}ºF"
                        tvTempDay4.text =
                            "${it.DailyForecasts[4].temperature.minimum.value!!}ºF"


                    }
                    Common.Type_Degree_K -> {

                        tvTempDay1.text =
                            "${Math.round(convertFahrenheitToKelvin(it?.DailyForecasts!![1].temperature.minimum.value!!))}ºK"
                        tvTempDay2.text =
                            "${Math.round(convertFahrenheitToKelvin(it.DailyForecasts[2].temperature.minimum.value!!))}ºK"
                        tvTempDay3.text =
                            "${Math.round(convertFahrenheitToKelvin(it.DailyForecasts[3].temperature.minimum.value!!))}ºK"
                        tvTempDay4.text =
                            "${Math.round(convertFahrenheitToKelvin(it.DailyForecasts[4].temperature.minimum.value!!))}ºK"

                    }
                }


                val sdf5days = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                val dateInStringDay1 = sdf5days.parse(it?.DailyForecasts!![1].date)
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
    }

    private fun convertFahrenheitToKelvin(fahrenheit: Float): Double {
        return (5.0 / 9 * (fahrenheit - 32) + 273)
    }

    /* Meaning of the constants
 Dv: Absolute humidity in grams/meter3
 m: Mass constant
 Tn: Temperature constant
 Ta: Temperature constant
 Rh: Actual relative humidity in percent (%) from phone’s sensor
 Tc: Current temperature in degrees C from phone’ sensor
 A: Pressure constant in hP
 K: Temperature constant for converting to kelvin
 */
    fun calculateAbsoluteHumidity(temperature: Double, relativeHumidity: Int): Float {
        var Dv = 0f
        val m = 17.62f
        val Tn = 243.12f
        val Ta = 216.7f
        val A = 6.112f
        val K = 273.15f

        Dv = (Ta.toDouble()
                * (relativeHumidity / 50).toDouble()
                * A.toDouble()
                * Math.exp((m * temperature / (Tn + temperature)).toDouble()) / (K + temperature)).toFloat()

        return Dv
    }


    override fun onResume() {
        super.onResume()
        innitViewModelCurrent()
        innitViewModel5days()
        if (intent.extras != null) {
            data = intent.getParcelableExtra<CityResult>("dataCity")
            mWeatherViewmodel.getDataWeatherCurrent(data!!.key!!, Common.API_Key4, true)
            mWeatherViewmodel.getDataWeather5days(data!!.key!!, Common.API_Key4, true)
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
        mWeatherViewmodel.getDataWeatherCurrent(city.key!!, Common.API_Key4, true)
        mWeatherViewmodel.getDataWeather5days(city.key!!, Common.API_Key4, true)
        tvLabelLocation.text = city!!.localizedName
        drawerLayout.closeDrawers()
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
                    mWeatherViewmodel.getDataWeatherCurrent(data!!.key!!, Common.API_Key4, true)
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
