package com.example.weatheronline.dialog


import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.*
import android.widget.RadioButton
import android.widget.RadioGroup

import com.example.weatheronline.R
import com.example.weatheronline.common.Common
import com.example.weatheronline.ui.weather.MainActivity
import com.example.weatheronline.ui.weather.SettingActivity
import kotlinx.android.synthetic.main.fragment_dialog_setting_temperature.*
import namhenry.com.vn.projectweek4.utills.SharePrefs

class DialogSettingTemperatureFragment : DialogFragment() {

    var index = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_dialog_setting_temperature, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        when (Common.stateDegree) {
            Common.Type_Degree_C -> {
                rdGroup.check(R.id.rbCdegree)
            }
            Common.Type_Degree_F -> {
                rdGroup.check(R.id.rbFdegree)
            }
            Common.Type_Degree_K -> {
                rdGroup.check(R.id.rbKdegree)
            }
        }

        rdGroup.setOnCheckedChangeListener { group, checkedId ->
            Common.stateDegree = index
            val radioButtonID = rdGroup.checkedRadioButtonId
            val radioButton = rdGroup.findViewById<RadioButton>(radioButtonID)
            index = rdGroup.indexOfChild(radioButton)
            Common.stateDegree = index
            SharePrefs().getInstance().put(Common.KEY_TYPE_DEGREE_CUSTOM_SELECTED, index)
            (activity as SettingActivity).upDateDegree()

        }

        tvLabelCancel.setOnClickListener {

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
