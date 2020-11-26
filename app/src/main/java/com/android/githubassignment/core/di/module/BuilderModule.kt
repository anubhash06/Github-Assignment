package com.android.githubassignment.core.di.module


import com.android.githubassignment.MainActivity
import com.android.githubassignment.core.di.module.builder.RepoBuilderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BuilderModule {


    @ContributesAndroidInjector(modules = [RepoBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity

}