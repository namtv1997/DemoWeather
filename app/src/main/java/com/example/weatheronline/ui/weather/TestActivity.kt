package com.example.weatheronline.ui.weather


import android.os.Bundle
import android.widget.Toast
import com.example.weatheronline.R
import com.example.weatheronline.base.BaseActivity
import kotlinx.android.synthetic.main.include_toolbar.*

class TestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setTitleActionBar(toolbar, getString(R.string.label_setting))
        toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "clicked !", Toast.LENGTH_SHORT).show()
        }
    }
}
