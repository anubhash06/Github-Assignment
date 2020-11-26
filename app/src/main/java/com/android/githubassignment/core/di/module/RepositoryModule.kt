package com.android.githubassignment.core.di.module

import com.android.githubassignment.core.repository.RepoRepository
import com.android.githubassignment.core.repository.impl.RepoRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepoRepository(repoRepository:  RepoRepositoryImpl): RepoRepository
}