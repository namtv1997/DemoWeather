package com.example.weatheronline.ui.weather


import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class TimerWorker(appContext: Context, workerParams: WorkerParameters)
    : Worker(appContext, workerParams) {
    override fun doWork(): Result {

        return Result.success()
    }

}