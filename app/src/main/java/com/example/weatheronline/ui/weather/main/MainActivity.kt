package com.example.weatheronline.ui.weather.main


import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.weatheronline.R
import com.example.weatheronline.base.BaseActivity
import com.example.weatheronline.common.Common
import com.example.weatheronline.model.cityresult.CityResult
import com.example.weatheronline.model.sqlite.CitySql
import com.example.weatheronline.sqliteHelper.DBHelper
import com.example.weatheronline.ui.weather.searchcity.SearchCityActivity
import com.example.weatheronline.ui.weather.setting.SettingActivity
import kotlinx.android.synthetic.main.activity_main.*
import namhenry.com.vn.projectweek4.utills.SharePrefs
import pub.devrel.easypermissions.EasyPermissions
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : BaseActivity(), View.OnClickListener,
    IclickItemGetCity {

    private lateinit var mWeatherViewmodel: WeatherViewmodel
    private lateinit var adapterSqliteCity: SqliteCityAdapter
    private lateinit var db: DBHelper

    private var listCity: List<CitySql> = ArrayList()
    private var citysql: CitySql? = null
    private var data: CityResult? = null
    private var isSearchCity: Boolean = false
    private var compareTime: Int? = null
    private var locationManager: LocationManager? = null
    private var latitude: String? = null
    private var longitude: String? = null

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivRefresh.setOnClickListener(this)
        tvLabelSetting.setOnClickListener(this)
        llSearch.setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        initViewmodelGeopositionSearch()
        innitViewModelCurrent()
        innitViewModel5days()
        requestPermission()

        db = DBHelper(this)
        if (intent.extras != null) {
            data = intent.getParcelableExtra("dataCity")
            mWeatherViewmodel.getDataWeatherCurrent(data!!.key!!, Key, true)
            mWeatherViewmodel.getDataWeather5days(data!!.key!!, Key, true)
            isSearchCity = true
            val citySql = CitySql(
                data!!.key!!,
                data!!.localizedName!!
            )
            db.addCity(citySql)
            tvLabelLocation.text = data!!.localizedName

        }
        initAdapter()

    }

    override fun onItemClickGetCity(city: CitySql) {
        isSearchCity = false
        citysql = city
        mWeatherViewmodel.getDataWeatherCurrent(city.key!!, Key, true)
        mWeatherViewmodel.getDataWeather5days(city.key!!, Key, true)
        tvLabelLocation.text = city.localizedName
        drawerLayout.closeDrawers()
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
            R.id.ivRefresh -> {
                if (isSearchCity) {
                    if (data?.key != null) {
                        mWeatherViewmodel.getDataWeatherCurrent(data?.key!!, Key, true)
                        mWeatherViewmodel.getDataWeather5days(data!!.key!!, Key, true)
                        Toast.makeText(this, R.string.alert_refresh_success, Toast.LENGTH_SHORT)
                            .show()
                    }

                } else {
                    if (citysql?.key != null) {
                        mWeatherViewmodel.getDataWeatherCurrent(citysql?.key!!, Key, true)
                        mWeatherViewmodel.getDataWeather5days(citysql?.key!!, Key, true)
                        Toast.makeText(this, R.string.alert_refresh_success, Toast.LENGTH_SHORT)
                            .show()
                    }
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

    private fun convertFahrenheitToCelcius(fahrenheit: Float): Float {
        return (fahrenheit - 32) * 5 / 9
    }

    private fun initAdapter() {
        listCity = db.allCity
        adapterSqliteCity =
            SqliteCityAdapter(listCity, this)
        rvItemLocation.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = adapterSqliteCity
        }

    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun innitViewModelCurrent() {
        mWeatherViewmodel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            weatherCurrent.observe(this@MainActivity, android.arch.lifecycle.Observer {

                val getDegree =
                    SharePrefs().getInstance()[Common.KEY_TYPE_DEGREE_CUSTOM_SELECTED, Int::class.java]
                when (getDegree) {
                    Common.Type_Degree_C -> {
                        tvDegree.text = "${Math.round(it!![0].temperature.metric.value!!)}º"
                    }
                    Common.Type_Degree_F -> {
                        tvDegree.text = "${Math.round(it!![0].temperature.imperial.value!!)}ºF"

                    }
                    Common.Type_Degree_K -> {
                        val celsius = it!![0].temperature.metric.value!!
                        val kelvin = celsius.plus(273.15)
                        tvDegree.text = "${Math.round(kelvin)}ºK"

                    }
                }

                val getHumidity =
                    SharePrefs().getInstance()[Common.KEY_TYPE_HUMIDITY_CUSTOM_SELECTED, Int::class.java]
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

                val getWind =
                    SharePrefs().getInstance()[Common.KEY_TYPE_WIND_CUSTOM_SELECTED, Int::class.java]
                when (getWind) {
                    Common.Type_WIND_KM -> {
                        tvWindSpeed.text = "${it!![0].wind.speed.metric.value}km/h"
                    }
                    Common.Type_WIND_MILES -> {
                        tvWindSpeed.text = "${it!![0].wind.speed.imperial.value}miles/h"
                    }

                }

                val dateInStringCurrent = it!![0].localObservationDateTime
                val sdfCurrent = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val shortdateInStringCurrent = dateInStringCurrent?.substring(0, 19)
                val date = sdfCurrent.parse(shortdateInStringCurrent)

                val sdfCurrentDate = SimpleDateFormat("yyyy EEEE MMMM-dd")

                val splitString = " "
                val part = sdfCurrentDate.format(date).split(splitString)
                tvLabelYear.text = part[0]
                tvDay.text = part[1]
                tvDate.text = part[2]

                val sdfCurrentTime = SimpleDateFormat("HH:mm:ss")
                val splitStringTime = ":"
                val part1 = sdfCurrentTime.format(date).split(splitStringTime)
                compareTime = part1[0].toInt()


            })
        }
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun innitViewModel5days() {
        mWeatherViewmodel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            weather.observe(this@MainActivity, android.arch.lifecycle.Observer {


                val getDegree =
                    SharePrefs().getInstance()[Common.KEY_TYPE_DEGREE_CUSTOM_SELECTED, Int::class.java]

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

                val dateInStringDay1 = it?.DailyForecasts!![1].date
                val dateInStringDay2 = it.DailyForecasts[2].date
                val dateInStringDay3 = it.DailyForecasts[3].date
                val dateInStringDay4 = it.DailyForecasts[4].date
                val timeSunset = it.DailyForecasts[0].sun.set
                val timeSunrise = it.DailyForecasts[0].sun.rise

                val sdf5days = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val shortdateInStringDay1 = dateInStringDay1?.substring(0, 19)
                val shortdateInStringDay2 = dateInStringDay2?.substring(0, 19)
                val shortdateInStringDay3 = dateInStringDay3?.substring(0, 19)
                val shortdateInStringDay4 = dateInStringDay4?.substring(0, 19)
                val shorttimeSunset = timeSunset?.substring(0, 19)
                val shorttimeSunrise = timeSunrise?.substring(0, 19)
                val sunset = sdf5days.parse(shorttimeSunset)
                val sunrise = sdf5days.parse(shorttimeSunrise)

                val sdf5days1 = SimpleDateFormat("EEE")
                val day1 = sdf5days.parse(shortdateInStringDay1)
                val day2 = sdf5days.parse(shortdateInStringDay2)
                val day3 = sdf5days.parse(shortdateInStringDay3)
                val day4 = sdf5days.parse(shortdateInStringDay4)

                val resultday1 = sdf5days1.format(day1)
                val resultday2 = sdf5days1.format(day2)
                val resultday3 = sdf5days1.format(day3)
                val resultday4 = sdf5days1.format(day4)

                tvTueDay.text = resultday1
                tvWednesday.text = resultday2
                tvThurday.text = resultday3
                tvFriDay.text = resultday4

                val sdf5days2 = SimpleDateFormat("HH:mm:ss")
                val splitStringtimeSun = ":"
                val parttimeSunset = sdf5days2.format(sunset).split(splitStringtimeSun)
                val parttimeSunrise = sdf5days2.format(sunrise).split(splitStringtimeSun)

                val compareTimeSunset = parttimeSunset[0].toInt()


                val compareTimeSunrise = parttimeSunrise[0].toInt()

                if (compareTime != null) {
                    if (compareTime!! >= compareTimeSunset || compareTime!! < compareTimeSunrise) {
                        bg_weather_infor.setBackgroundColor(Color.parseColor("#343434"))

                    } else {
                        bg_weather_infor.setBackgroundColor(Color.parseColor("#d45e5e"))

                    }
                }

            })
        }
    }

    private fun initViewmodelGeopositionSearch() {
        mWeatherViewmodel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            resultGeoPositionSearch.observe(this@MainActivity, android.arch.lifecycle.Observer {
                mWeatherViewmodel.getDataWeatherCurrent(it?.key!!, Key, true)
                mWeatherViewmodel.getDataWeather5days(it.key, Key, true)
                tvLabelLocation.text = it.englishName
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
    private fun calculateAbsoluteHumidity(temperature: Double, relativeHumidity: Int): Float {
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

    private fun requestPermission() {
        if (EasyPermissions.hasPermissions(
                this,
                *ACCESS_FINE_LOCATION
            )
        ) {
            // Have permissions, do the thing
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

            //Check gps is enable or not

            if (!locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                //Write Function To enable gps

                onGPS()
            } else {
                //GPS is already On then

                getLocation()
            }

        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.access_fine_location),
                RC_ACCESS_FINE_LOCATION,
                *ACCESS_FINE_LOCATION
            )
        }
    }

    private fun onGPS() {

        val builder = AlertDialog.Builder(this)

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton(
            "YES"
        ) { dialog, which -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) }
            .setNegativeButton(
                "NO"
            ) { dialog, _ -> dialog.cancel() }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {

        val locationGps = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val locationNetwork =
            locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        val locationPassive =
            locationManager?.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)

        if (locationGps != null) {
            latitude = locationGps.latitude.toString()
            longitude = locationGps.longitude.toString()
            mWeatherViewmodel.getDataGeoPositionSearch(Key, "${latitude},${longitude}")

        } else if (locationNetwork != null) {
            latitude = locationNetwork.latitude.toString()
            longitude = locationNetwork.longitude.toString()
            mWeatherViewmodel.getDataGeoPositionSearch(Key, "${latitude},${longitude}")

        } else if (locationPassive != null) {
            latitude = locationPassive.latitude.toString()
            longitude = locationPassive.longitude.toString()
            mWeatherViewmodel.getDataGeoPositionSearch(Key, "${latitude},${longitude}")
        } else {
            Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        val ACCESS_FINE_LOCATION = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        const val RC_ACCESS_FINE_LOCATION = 4466

    }
}
