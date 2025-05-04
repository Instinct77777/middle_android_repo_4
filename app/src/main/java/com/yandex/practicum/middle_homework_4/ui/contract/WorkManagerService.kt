package com.yandex.practicum.middle_homework_4.ui.contract

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import com.yandex.practicum.middle_homework_4.data.work_manager.RefreshWorker
import java.util.concurrent.TimeUnit

interface WorkManagerService {
    fun launchRefreshWork()
    fun cancelRefreshWork()
}

// These extension functions should probably be in the implementation class rather than here
internal fun createConstraints(): Constraints {
    return Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()
}

internal fun createRequest(repeat: Long, delayed: Long): PeriodicWorkRequest {
    val networkConstraints = createConstraints()
    return PeriodicWorkRequestBuilder<RefreshWorker>(
        repeat.toLong(),
        TimeUnit.MINUTES
    )
        .setInitialDelay(delayed, TimeUnit.SECONDS)
        .setConstraints(networkConstraints)
        .build()
}