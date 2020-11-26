package com.android.githubassignment.core.di.module

import android.app.Application
import android.content.Context
import com.android.githubassignment.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule (private val application: App){

    @Singleton
    @Provides
    fun provideApp() : Application = application

    @Singleton
    @Provides
    fun provideAppContext() : Context = application

}