package com.android.githubassignment.ui.repodetail

import com.android.githubassignment.ui.home.RepoDisplayData

interface RepositoryContract {
    fun openDetail(displayData: RepoDisplayData)
}