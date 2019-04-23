package com.example.mockproject.retrofit2

import com.example.weatheronline.model.WeatherResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface DataClient {
    @GET("forecast")
    fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): Observable<WeatherResult>

    @GET("forecast")
    fun getWeatherDatabyCity(
        @Query("q") lat: String,
        @Query("appid") appid: String
    ): Observable<WeatherResult>

}