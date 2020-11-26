package com.android.githubassignment.ui.home.exception

import com.android.githubassignment.core.exception.Failure

class NoRepositoryFoundFailure(override  var message : String = "") : Failure.FeatureFailure()