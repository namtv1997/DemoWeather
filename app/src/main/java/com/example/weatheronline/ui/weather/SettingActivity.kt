package com.example.weatheronline.ui.weather


import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.weatheronline.R
import com.example.weatheronline.base.BaseActivity
import com.example.weatheronline.dialog.DialogSettingHumidityFragment
import com.example.weatheronline.dialog.DialogSettingTemperatureFragment
import com.example.weatheronline.dialog.DialogSettingWinSpeedFragment
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.include_toolbar.*

class SettingActivity : BaseActivity(), View.OnClickListener {


    private  var mDialogSettingHumidityFragment:DialogSettingHumidityFragment?= null
    private  var mDialogSettingTemperatureFragment:DialogSettingTemperatureFragment?= null
    private  var mDialogSettingWinSpeedFragment:DialogSettingWinSpeedFragment?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setTitleActionBar(toolbar, getString(R.string.label_setting))
        toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "clicked !", Toast.LENGTH_SHORT).show()
        }
        toolbar.setNavigationOnClickListener {
            finish()
        }
        llHumidity.setOnClickListener(this)
        llTemperature.setOnClickListener(this)
        llWindSpeed.setOnClickListener(this)

    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.llTemperature->{
                mDialogSettingTemperatureFragment=DialogSettingTemperatureFragment()
                mDialogSettingTemperatureFragment?.show(this.supportFragmentManager, "")
            }
            R.id.llHumidity->{
                mDialogSettingHumidityFragment=DialogSettingHumidityFragment()
                mDialogSettingHumidityFragment?.show(this.supportFragmentManager, "")
            }
            R.id.llWindSpeed->{
                mDialogSettingWinSpeedFragment=DialogSettingWinSpeedFragment()
                mDialogSettingWinSpeedFragment?.show(this.supportFragmentManager, "")
            }
        }

    }
}
