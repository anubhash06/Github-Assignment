package com.android.githubassignment.core.scheduler

import io.reactivex.Scheduler


interface AppScheduler {

    fun io() : Scheduler

    fun mainThread() : Scheduler

    fun newThread() : Scheduler

}