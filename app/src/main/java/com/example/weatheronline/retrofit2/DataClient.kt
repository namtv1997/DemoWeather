package com.example.mockproject.retrofit2

import com.example.weatheronline.model.weatherresult.WeatherResult
import com.example.weatheronline.model.cityresult.CityResult
import com.example.weatheronline.model.geoposition.GeoPositionSearch
import com.example.weatheronline.model.weathercurentday.WeatherCurent
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DataClient {
    @GET("forecasts/v1/daily/5day/{keyRegion}")
    fun getWeatherData5Days(
        @Path("keyRegion") keyRegion: String,
        @Query("apikey") apikey: String,
        @Query("details") details: Boolean
    ): Observable<WeatherResult>

    @GET("locations/v1/cities/geoposition/search")
    fun getWeatherDataByGeoPositionSearch(
        @Query("apikey") apikey: String,
        @Query("q") q: String
    ): Observable<GeoPositionSearch>

    @GET("/currentconditions/v1/{keyRegion}")
    fun getWeatherDataCurrent(
        @Path("keyRegion") keyRegion: String,
        @Query("apikey") apikey: String,
        @Query("details") details: Boolean
    ): Observable<List<WeatherCurent>>


    @GET("locations/v1/cities/autocomplete")
    fun getWeatherDatabyCity(
        @Query("apikey") apikey: String,
        @Query("q") q: String
    ): Single<List<CityResult>>

}