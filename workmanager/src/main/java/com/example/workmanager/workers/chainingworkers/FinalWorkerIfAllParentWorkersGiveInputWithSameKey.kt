package com.example.workmanager.workers.chainingworkers

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class FinalWorkerIfAllParentWorkersGiveInputWithSameKey(
    appContext: Context,
    workerParameters: WorkerParameters,
) :
    Worker(appContext, workerParameters) {
    override fun doWork(): Result {
//if data is coming from all parallel workers in same key "output"
        val inputData1 = inputData.getStringArray("output")?.contentToString() ?: "Empty Data1"
        val data = Data.Builder().put("output", "Final worker Data \n $inputData1   ").build()
        return Result.success(data)
    }


}