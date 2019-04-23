package com.example.weatheronline.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.mockproject.retrofit2.DataClient
import com.example.mockproject.retrofit2.RetrofitClient
import com.example.weatheronline.R
import com.example.weatheronline.model.WeatherResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewmodel : ViewModel() {
    private val disposables = CompositeDisposable()
    val weather = MutableLiveData<WeatherResult>()
    val errorMsg: MutableLiveData<Int> = MutableLiveData()
    private var dataClient: DataClient = RetrofitClient.getClient()?.create(DataClient::class.java)!!

    fun getDataWeather(lat: Double, lon: Double, appid: String) {
        disposables.add(
            dataClient.getWeatherData(lat, lon, appid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    weather.postValue(it)
                }, {

                   errorMsg.postValue(R.string.error_message_lost_internet_connection)
                })
        )
    }
    fun getDataWeatherByCity( q:String,appid: String){
        disposables.add(
            dataClient.getWeatherDatabyCity(q,appid)
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