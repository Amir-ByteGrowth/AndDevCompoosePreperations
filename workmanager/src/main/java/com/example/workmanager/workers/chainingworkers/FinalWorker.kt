package com.example.workmanager.workers.chainingworkers

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class FinalWorker(appContext: Context, workerParameters: WorkerParameters) :
    Worker(appContext, workerParameters) {
    override fun doWork(): Result {

        val inputData1 = inputData.getString("output1") ?: "Empty Data1"
        val inputData2 = inputData.getString("output2") ?: "Empty Data2"
        val inputData3 = inputData.getString("output3") ?: "Empty Data3"

        val data = Data.Builder().put("output", "Final worker Data \n $inputData1  \n $inputData2   \n $inputData3 ").build()
        return Result.success(data)
    }
}