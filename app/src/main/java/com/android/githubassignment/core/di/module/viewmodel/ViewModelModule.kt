package com.android.githubassignment.core.di.module.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.githubassignment.ui.home.RepoViewModel
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
    abstract fun bindsOpenPullRequestViewModel(viewModel: RepoViewModel): ViewModel

}