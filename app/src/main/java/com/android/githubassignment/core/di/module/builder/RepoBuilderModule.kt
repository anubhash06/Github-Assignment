package com.android.githubassignment.core.di.module.builder


import com.android.githubassignment.ui.home.RepoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class RepoBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): RepoFragment

}