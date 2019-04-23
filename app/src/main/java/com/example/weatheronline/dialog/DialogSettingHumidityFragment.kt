package com.example.weatheronline.dialog



import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*

import com.example.weatheronline.R
import kotlinx.android.synthetic.main.fragment_dialog_setting_humidity.*

class DialogSettingHumidityFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_dialog_setting_humidity, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
