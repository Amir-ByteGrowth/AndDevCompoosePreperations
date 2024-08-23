package com.example.workmanager.workers.chainingworkers

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class OutputWorker2(appContext: Context, workerParameters: WorkerParameters) :
    Worker(appContext, workerParameters) {
    override fun doWork(): Result {
        val data = Data.Builder().put("output2", "worker two").build()
//        val data = Data.Builder().put("output", "worker two").build()
        return Result.success(data)
    }
}