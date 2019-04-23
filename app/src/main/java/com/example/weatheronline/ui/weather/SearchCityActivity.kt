package com.example.weatheronline.ui.weather

import android.arch.lifecycle.ViewModelProviders
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.weatheronline.R
import com.example.weatheronline.adapter.SearchCityAdapter
import com.example.weatheronline.adapter.WeatherPagerAdapter
import com.example.weatheronline.base.BaseActivity
import com.example.weatheronline.common.Common
import com.example.weatheronline.model.WeatherResult
import com.example.weatheronline.viewmodel.WeatherViewmodel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mancj.materialsearchbar.MaterialSearchBar
import kotlinx.android.synthetic.main.activity_search_city.*
import java.io.*
import java.lang.StringBuilder
import java.util.zip.GZIPInputStream


class SearchCityActivity : BaseActivity(), SearchCityAdapter.ContactsAdapterListener {


    private lateinit var weatherViewModel: WeatherViewmodel
    private  var listCity= ArrayList<WeatherResult>()
    var listCities: List<String>? = null
    private val listNameCity = ArrayList<String>()
    private lateinit var mAdapter: SearchCityAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.weatheronline.R.layout.activity_search_city)
        search_bar.isEnabled = false

        LoadCities().execute()//cais na
        listCity = ArrayList()
        mAdapter = SearchCityAdapter(this, listCity, this)
        rvSearchCity.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            mAdapter.notifyDataSetChanged()
        }
    }

    inner class LoadCities : AsyncTask<Void, Void, List<String>>(), MaterialSearchBar.OnSearchActionListener {
        override fun onButtonClicked(buttonCode: Int) {

        }

        override fun onSearchStateChanged(enabled: Boolean) {
        }

        override fun onSearchConfirmed(text: CharSequence?) {
            getDataByCity(text.toString())
            search_bar.lastSuggestions = listCities
        }


        override fun doInBackground(vararg params: Void?): List<String> {
            listCities = ArrayList()
            try {
                val builder = StringBuilder()
                val inputValue: InputStream = resources.openRawResource(R.raw.city_list)
                val gzipInputStream = GZIPInputStream(inputValue)
                val reader = InputStreamReader(gzipInputStream)
                val readBuffer = BufferedReader(reader)
                val readed: String? = null
                while (readed == readBuffer.readLine() != null) {
                    builder.append(readed)
                    val turnsType = object : TypeToken<List<String>>() {}.type
                    listCities = Gson().fromJson(builder.toString(), turnsType)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return listCities as ArrayList<String>
        }

        override fun onPostExecute(result: List<String>) {
            super.onPostExecute(result)
            search_bar.isEnabled = true
            search_bar.addTextChangeListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s!!.isNotEmpty()) {
                        imgEmpty.visibility = View.GONE
                        tvEmpty.visibility = View.GONE
                    } else {
                        imgEmpty.visibility = View.VISIBLE
                        tvEmpty.visibility = View.VISIBLE
                    }

                    val suggest = ArrayList<String>()
                    for (search: String in result) {
                        if (search.toLowerCase().contains(search_bar.text.toLowerCase()))
                            suggest.add(search)
                    }
                    search_bar.lastSuggestions = suggest
                }
            })
            search_bar.setOnSearchActionListener(this)


        }


    }

    private fun getDataByCity(nameOfCity: String) {
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            weather.observe(this@SearchCityActivity, android.arch.lifecycle.Observer {
                if (it != null) {


                }
            })
        }

        weatherViewModel.getDataWeatherByCity(nameOfCity, Common.API_Key)
    }

    override fun onContactSelected(weather: WeatherResult) {

    }
}


