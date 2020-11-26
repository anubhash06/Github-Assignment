package com.android.githubassignment.core.di.module

import com.android.githubassignment.core.scheduler.AppScheduler
import com.android.githubassignment.core.scheduler.AppSchedulerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class SchedulerModule {

     @Singleton
     @Provides
     open fun provideAppScheduler(): AppScheduler = AppSchedulerImpl()
}