package com.android.githubassignment.core.interactor

import io.reactivex.Observable

abstract class UseCase<Type, Params> {
    abstract  fun run(params: Params): Observable<Type>
}
