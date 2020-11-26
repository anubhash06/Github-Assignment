package com.android.githubassignment

import android.app.Activity
import android.app.Application
import com.android.githubassignment.core.di.component.DaggerAppComponent
import com.android.githubassignment.core.di.module.BaseModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    private val appComponent by lazy {
        DaggerAppComponent.builder()
            .baseModule(BaseModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}