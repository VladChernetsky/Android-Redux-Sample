package com.hack.reduxsample.presentation

import io.reactivex.Observable

class LoginInteractor {

    private val dummyEmail = "demo@test.com"
    private val dummyPassword = "demo"

    fun login(email: String, password: String): Observable<LoginActions> {
        return Observable.fromCallable({
            try {
                if (dummyEmail == email && dummyPassword == password) {
                    return@fromCallable LoginActions.Logged(true)
                } else {
                    return@fromCallable LoginActions.Error(Exception("Email or password is not correct"))
                }
            } catch (e: Exception) {
                return@fromCallable LoginActions.Error(e)
            }
        })
    }
}