package com.example.workmanager.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class WorkManagerPractice(appContext: Context, workerParameters: WorkerParameters) :
    Worker(appContext, workerParameters) {
    override fun doWork(): Result {

        for (i in 0..10){
            Thread.sleep(2000)
            println("task# $i")

        }

        return Result.success()
    }


}

