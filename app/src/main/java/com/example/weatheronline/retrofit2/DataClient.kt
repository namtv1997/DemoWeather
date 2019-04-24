package com.example.mockproject.retrofit2

import com.example.weatheronline.model.weatherresult.WeatherResult
import com.example.weatheronline.model.cityresult.CityResult
import com.example.weatheronline.model.weathercurentday.WeatherCurent
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DataClient {
    @GET("forecasts/v1/daily/5day/{key}")
    fun getWeatherData5Days(
        @Path("lat") lat: Double,
        @Query("apikey") lon: String,
        @Query("details") details: Boolean
    ): Observable<WeatherResult>

    @GET("/currentconditions/v1/353412")
    fun getWeatherDataCurrent(
        @Path("lat") lat: Double,
        @Query("apikey") lon: String,
        @Query("details") details: Boolean
    ): Observable<WeatherCurent>

    @GET("locations/v1/cities/autocomplete")
    fun getWeatherDatabyCity(
        @Query("apikey") apikey: String,
        @Query("q") q: String
    ): Single<List<CityResult>>

}