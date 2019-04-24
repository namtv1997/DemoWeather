package com.example.weatheronline.ui.weather

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.weatheronline.adapter.SearchCityAdapter
import com.example.weatheronline.base.BaseActivity
import com.example.weatheronline.common.Common
import com.example.weatheronline.model.cityresult.CityResult
import com.example.weatheronline.viewmodel.WeatherViewmodel
import kotlinx.android.synthetic.main.activity_search_city.*


class SearchCityActivity : BaseActivity() {


    private lateinit var weatherViewModel: WeatherViewmodel
    private var listNameCity = ArrayList<CityResult>()
    var listCities: List<String>? = null
    private lateinit var AdapterSearchCity: SearchCityAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.weatheronline.R.layout.activity_search_city)


        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty()) {
                    ivCancel.visibility = View.VISIBLE
                    imgEmpty.visibility = View.GONE
                    tvEmpty.visibility = View.GONE
                } else {
                    ivCancel.visibility = View.GONE
                    imgEmpty.visibility = View.VISIBLE
                    tvEmpty.visibility = View.VISIBLE
                }
            }
        })

        ivCancel.setOnClickListener {
            edtSearch.setText("")
        }

    }




    private fun getDataByCity(nameOfCity: String) {
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            city.observe(this@SearchCityActivity, android.arch.lifecycle.Observer {
                if (it != null) {
                    Log.d("dd", "ketqua")
                    listNameCity = it as ArrayList<CityResult>
                    AdapterSearchCity = SearchCityAdapter(listNameCity)
                    rvSearchCity.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        adapter = AdapterSearchCity
                        AdapterSearchCity.notifyDataSetChanged()
                    }
                }
            })
        }

        weatherViewModel.getDataWeatherByCity(Common.API_Key, nameOfCity)
    }


}


