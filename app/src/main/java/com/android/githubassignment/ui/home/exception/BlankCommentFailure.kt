package com.android.githubassignment.ui.home.exception

import com.android.githubassignment.core.exception.Failure
import com.android.githubassignment.ui.home.RepoDisplayData

class BlankCommentFailure(override  var message : String = "", val displayData: RepoDisplayData) : Failure.FeatureFailure()