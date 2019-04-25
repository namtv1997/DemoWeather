package com.example.weatheronline.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.mockproject.retrofit2.DataClient
import com.example.mockproject.retrofit2.RetrofitClient
import com.example.weatheronline.R
import com.example.weatheronline.model.weatherresult.WeatherResult
import com.example.weatheronline.model.cityresult.CityResult
import com.example.weatheronline.model.weathercurentday.WeatherCurent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewmodel : ViewModel() {
    private val disposables = CompositeDisposable()
    val weather = MutableLiveData<WeatherResult>()
    val weatherCurrent = MutableLiveData<List<WeatherCurent>>()
    val city = MutableLiveData<List<CityResult>>()
    val errorMsg: MutableLiveData<Int> = MutableLiveData()
    private var dataClient: DataClient = RetrofitClient.getClient()?.create(DataClient::class.java)!!

    fun getDataWeatherCurrent(keyRegion: String, apikey: String, details: Boolean) {
        disposables.add(
            dataClient.getWeatherDataCurrent(keyRegion, apikey, details)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    weatherCurrent.postValue(it)
                }, {
                   errorMsg.postValue(R.string.error_message_lost_internet_connection)
                })
        )
    }

    fun getDataWeather5days(keyRegion: String, apikey: String, details: Boolean) {
        disposables.add(
            dataClient.getWeatherData5Days(keyRegion, apikey, details)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    weather.postValue(it)
                }, {
                    errorMsg.postValue(R.string.error_message_lost_internet_connection)
                })
        )
    }


    override fun onCleared() {
        disposables.clear()
    }

}