package com.example.workmanager.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.supervisorScope

class MyCoroutineWorker(appContext: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(appContext, workerParameters) {
    override suspend fun doWork(): Result {
        supervisorScope {

        }
        launch
        return Result.success()
    }
}