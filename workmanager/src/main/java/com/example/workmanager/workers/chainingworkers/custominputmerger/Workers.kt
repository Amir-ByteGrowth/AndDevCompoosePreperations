package com.example.workmanager.workers.chainingworkers.custominputmerger

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class Work1(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val outputData = workDataOf("key1" to "hello", "key2" to "world")
        return Result.success(outputData)
    }
}

class Work2(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val outputData = workDataOf("key1" to "world", "key3" to "android")
        return Result.success(outputData)
    }
}

class FinalWorkerCustomManager(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val key1 = inputData.getString("key1")
        val key2 = inputData.getString("key2")
        val key3 = inputData.getString("key3")

        Log.d("FinalWorker", "Merged key1: $key1")
        Log.d("FinalWorker", "Merged key2: $key2")
        Log.d("FinalWorker", "Merged key3: $key3")

        return Result.success()
    }
}