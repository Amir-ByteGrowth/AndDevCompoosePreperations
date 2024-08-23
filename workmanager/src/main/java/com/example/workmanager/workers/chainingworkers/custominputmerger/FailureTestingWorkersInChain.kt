package com.example.workmanager.workers.chainingworkers.custominputmerger

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf



class FailureWork1(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val outputData = workDataOf("key1" to "hello", "key2" to "world")
        return Result.success(outputData)
    }
}

class FailureWork2(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val outputData = workDataOf("key1" to "world", "key3" to "android")
        return Result.Failure(outputData)
    }
}