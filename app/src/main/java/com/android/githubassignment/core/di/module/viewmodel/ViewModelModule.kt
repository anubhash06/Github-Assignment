package com.android.githubassignment.core.di.module.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.githubassignment.ui.home.RepoViewModel
import com.android.githubassignment.ui.repodetail.RepoDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel::class)
    abstract fun bindsRepoViewModel(viewModel: RepoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoDetailViewModel::class)
    abstract fun bindsRepoDetailsViewModel(viewModel: RepoDetailViewModel): ViewModel

}