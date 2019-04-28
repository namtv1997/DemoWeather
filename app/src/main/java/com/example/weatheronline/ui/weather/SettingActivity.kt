package com.example.weatheronline.ui.weather


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.weatheronline.R
import com.example.weatheronline.base.BaseActivity
import com.example.weatheronline.common.Common
import com.example.weatheronline.dialog.DialogSettingHumidityFragment
import com.example.weatheronline.dialog.DialogSettingTemperatureFragment
import com.example.weatheronline.dialog.DialogSettingWinSpeedFragment
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.include_toolbar.*
import namhenry.com.vn.projectweek4.utills.SharePrefs

class SettingActivity : BaseActivity(), View.OnClickListener {


    private  var mDialogSettingHumidityFragment:DialogSettingHumidityFragment?= null
    private  var mDialogSettingTemperatureFragment:DialogSettingTemperatureFragment?= null
    private  var mDialogSettingWinSpeedFragment:DialogSettingWinSpeedFragment?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setTitleActionBar(toolbar, getString(R.string.label_setting))
        toolbar.setNavigationOnClickListener {
            finish()
        }
        llHumidity.setOnClickListener(this)
        llTemperature.setOnClickListener(this)
        llWindSpeed.setOnClickListener(this)
        upDateDegree()
        upDateHumidity()
        upDateWind()


    }

    fun upDateDegree(){
        val getDegree=SharePrefs().getInstance()["KEY_TYPE_DEGREE_CUSTOM_SELECTED",Int::class.java]
        when(getDegree){
            Common.Type_Degree_C->{
                tvgetDegreeC.text=getString(R.string.label_radio_c_degree)

            }
            Common.Type_Degree_F->{
                tvgetDegreeC.text=getString(R.string.label_radio_f_degree)

            }
            Common.Type_Degree_K->{
                tvgetDegreeC.text=getString(R.string.label_radio_k_degree)

            }
        }
    }

    fun upDateHumidity(){
        val getHumidity=SharePrefs().getInstance()["KEY_TYPE_HUMIDITY_CUSTOM_SELECTED",Int::class.java]
        when(getHumidity){
            Common.Type_HUMIDITY_PERCENT->{
                tvHumiditySetting.text=getString(R.string.label_radio_relative)
            }
            Common.Type_HUMIDITY_ABSOLUTE->{
                tvHumiditySetting.text=getString(R.string.label_radio_absolute)
            }

        }
    }

    fun upDateWind(){
        val getWind=SharePrefs().getInstance()["KEY_TYPE_WIND_CUSTOM_SELECTED",Int::class.java]
        when(getWind){
            Common.Type_WIND_KM->{
                tvWindSpeedSetting.text=getString(R.string.label_radio_wind_km)
            }
            Common.Type_WIND_MILES->{
                tvWindSpeedSetting.text=getString(R.string.label_radio_wind_mile)
            }

        }
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
