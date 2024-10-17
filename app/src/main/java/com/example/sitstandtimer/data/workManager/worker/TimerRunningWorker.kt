package com.example.sitstandtimer.data.workManager.worker

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.sitstandtimer.utils.TimerNotificationHelper
import kotlinx.coroutines.CancellationException

class TimerRunningWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val timerNotificationHelper: TimerNotificationHelper
) : CoroutineWorker(context, workerParameters) {

    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {
        return try {

            val type = inputData.getString(typeKey)

            timerNotificationHelper.showTimerRunningNotification(type)

            Result.success()
        } catch (e: CancellationException) {
            timerNotificationHelper.removeTimerRunningNotification()
            Result.failure()
        }
    }

    companion object {
        const val typeKey = "TYPE"
    }
}

const val TIMER_RUNNING_TAG = "timerRunningTag"