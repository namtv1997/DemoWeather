package com.example.weatheronline.ui.weather


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.mockproject.retrofit2.DataClient
import com.example.mockproject.retrofit2.RetrofitClient
import com.example.weatheronline.R
import com.example.weatheronline.adapter.IClickItemListener
import com.example.weatheronline.adapter.SearchCityAdapter
import com.example.weatheronline.base.BaseActivity
import com.example.weatheronline.common.Common
import com.example.weatheronline.model.cityresult.CityResult
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
                if (s!!.length > 0) {
                    ivCancel.visibility = View.VISIBLE
                    imgEmpty.visibility = View.GONE
                    tvEmpty.visibility = View.GONE
                    initObserver()
                } else {
                    ivCancel.visibility = View.GONE
                    imgEmpty.visibility = View.VISIBLE
                    tvEmpty.visibility = View.VISIBLE
                    listNameCity.clear()
                    AdapterSearchCity.notifyDataSetChanged()
                }
            }
        })



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


    private fun initObserver(){
        disposable.add(
            publishSubject.debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMapSingle {
                    dataClient.getWeatherDatabyCity(Common.API_Key6, it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getSearchObserver())
        )

        disposable.add(
            RxTextView.textChangeEvents(edtSearch!!)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchCityTextWatcher())
        )
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

            override fun onError(e: Throwable) {}

            override fun onComplete() {}
        }
    }

    override fun onItemClick(city: CityResult) {
        val intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("dataCity", city)
        intent.putExtras(bundle)
        startActivity(intent)


    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}


