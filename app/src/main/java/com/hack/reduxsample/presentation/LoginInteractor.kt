package com.hack.reduxsample.presentation

import io.reactivex.Observable

class LoginInteractor {

    fun login(): Observable<UserActions> {
        return Observable.fromCallable({
            try {
                return@fromCallable UserActions.Logged(true)
            } catch (e: Exception) {
                return@fromCallable UserActions.Error(e)
            }
        })
    }
}