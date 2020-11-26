package com.android.githubassignment.core.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AppSchedulerImpl  : AppScheduler{

    override fun io(): Scheduler = Schedulers.io()

    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()

    override fun newThread(): Scheduler = Schedulers.io()
}