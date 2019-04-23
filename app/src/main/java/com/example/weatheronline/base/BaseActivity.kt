package com.example.weatheronline.base

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.example.weatheronline.R
import kotlinx.android.synthetic.main.include_toolbar.*

abstract class BaseActivity:AppCompatActivity(){
    fun setTitleActionBar(toolbar: Toolbar, title: String) {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.let {
            tvTitleToolbar.text = title
            it.title = ""
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_back)
        }
    }
}