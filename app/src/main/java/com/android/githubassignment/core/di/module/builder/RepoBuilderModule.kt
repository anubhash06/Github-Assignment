package com.android.githubassignment.core.di.module.builder


import com.android.githubassignment.ui.home.RepoFragment
import com.android.githubassignment.ui.repodetail.RepoDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class RepoBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): RepoFragment

    @ContributesAndroidInjector
    abstract fun contributeRepoDetailFragment(): RepoDetailFragment

}