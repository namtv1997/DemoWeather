package com.example.weatheronline.dialog


import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import android.widget.RadioButton

import com.example.weatheronline.R
import com.example.weatheronline.common.Common
import com.example.weatheronline.ui.weather.SettingActivity
import kotlinx.android.synthetic.main.fragment_dialog_setting_humidity.*
import namhenry.com.vn.projectweek4.utills.SharePrefs

class DialogSettingHumidityFragment : DialogFragment() {

    var index = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_dialog_setting_humidity, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        when (Common.stateDegree) {
            Common.Type_HUMIDITY_PERCENT -> {
                rdGroupHumidity.check(R.id.rdPercent)
            }
            Common.Type_HUMIDITY_ABSOLUTE -> {
                rdGroupHumidity.check(R.id.rdAbsolute)
            }

        }

        tvLabelCancel.setOnClickListener {
            Common.stateDegree = index
            val radioButtonID = rdGroupHumidity.checkedRadioButtonId
            val radioButton = rdGroupHumidity.findViewById<RadioButton>(radioButtonID)
            index = rdGroupHumidity.indexOfChild(radioButton)
            Common.stateDegree = index
            SharePrefs().getInstance().put("KEY_TYPE_HUMIDITY_CUSTOM_SELECTED", index)
            (activity as SettingActivity).upDateHumidity()
            dismiss()
        }
    }

    override fun onResume() {
        val window = dialog.window
        val size = Point()
        val display = window?.windowManager?.defaultDisplay
        display?.getSize(size)
        window?.setLayout((size.x * 1), WindowManager.LayoutParams.WRAP_CONTENT)
        window?.setGravity(Gravity.CENTER)
        super.onResume()
    }
}
