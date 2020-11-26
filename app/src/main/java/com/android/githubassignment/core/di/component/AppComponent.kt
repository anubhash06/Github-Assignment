package com.android.githubassignment.core.di.component


import com.android.githubassignment.App
import com.android.githubassignment.core.di.module.SchedulerModule
import com.android.githubassignment.core.di.module.BaseModule
import com.android.githubassignment.core.di.module.BuilderModule
import com.android.githubassignment.core.di.module.NetworkModule
import com.android.githubassignment.core.di.module.RepositoryModule
import com.android.githubassignment.core.di.module.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    BaseModule::class,
    NetworkModule::class,
    BuilderModule::class,
    RepositoryModule::class,
    SchedulerModule::class,
    ViewModelModule::class])
interface AppComponent {
    fun inject(app: App)
}