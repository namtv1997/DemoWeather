package com.example.weatheronline.ui.weather


import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.mockproject.retrofit2.DataClient
import com.example.mockproject.retrofit2.RetrofitClient
import com.example.weatheronline.R
import com.example.weatheronline.adapter.IClickItemListener
import com.example.weatheronline.adapter.SearchCityAdapter
import com.example.weatheronline.base.BaseActivity
import com.example.weatheronline.common.Common
import com.example.weatheronline.model.cityresult.CityResult
import com.example.weatheronline.model.weathercurentday.WeatherCurent
import com.example.weatheronline.viewmodel.WeatherViewmodel
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_search_city.*
import java.util.concurrent.TimeUnit


class SearchCityActivity : BaseActivity(), View.OnClickListener, IClickItemListener {


    private lateinit var AdapterSearchCity: SearchCityAdapter

    private val publishSubject = PublishSubject.create<String>()
    private var listNameCity = ArrayList<CityResult>()
    private val disposable = CompositeDisposable()

    private var dataClient: DataClient = RetrofitClient.getClient()?.create(DataClient::class.java)!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.weatheronline.R.layout.activity_search_city)

        initAdapter()

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty()) {
                    ivCancel.visibility = View.VISIBLE
                    imgEmpty.visibility = View.GONE
                    tvEmpty.visibility = View.GONE

                } else {
                    ivCancel.visibility = View.GONE
                    imgEmpty.visibility = View.VISIBLE
                    tvEmpty.visibility = View.VISIBLE
                    listNameCity.clear()
                    AdapterSearchCity.notifyDataSetChanged()
                }
            }
        })

        val observer = getSearchObserver()

        disposable.add(
            publishSubject.debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMapSingle {
                    dataClient.getWeatherDatabyCity(Common.API_Key5, it)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                }
                .subscribeWith(observer)
        )

        disposable.add(
            RxTextView.textChangeEvents(edtSearch!!)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchCityTextWatcher())
        )

        disposable.add(observer)


        ivCancel.setOnClickListener(this)
        ivBack.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivBack -> {
                finish()
            }
            R.id.ivCancel -> {
                edtSearch.setText("")
            }
        }
    }

    private fun searchCityTextWatcher(): DisposableObserver<TextViewTextChangeEvent> {
        return object : DisposableObserver<TextViewTextChangeEvent>() {
            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                publishSubject.onNext(textViewTextChangeEvent.text().toString())
            }

            override fun onError(e: Throwable) {}

            override fun onComplete() {}
        }
    }

    private fun initAdapter() {
        AdapterSearchCity = SearchCityAdapter(listNameCity, this)
        rvSearchCity.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = AdapterSearchCity
            AdapterSearchCity.notifyDataSetChanged()
        }
    }

    private fun getSearchObserver(): DisposableObserver<List<CityResult>> {
        return object : DisposableObserver<List<CityResult>>() {
            override fun onNext(contacts: List<CityResult>) {
                listNameCity.clear()
                listNameCity.addAll(contacts)
                AdapterSearchCity.notifyDataSetChanged()
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {

            }
        }
    }

    override fun onItemClick(listCity: ArrayList<CityResult>, position: Int) {
        val intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelableArrayList("dataCity", listCity)
        bundle.putInt("position", position)
        intent.putExtras(bundle)
        startActivity(intent)


    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}


