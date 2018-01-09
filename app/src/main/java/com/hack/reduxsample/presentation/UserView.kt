package com.hack.reduxsample.presentation

import com.hack.reduxsample.redux.ReduxView
import io.reactivex.Observable

interface UserView : ReduxView<UserState> {
    val onLoginClick: Observable<Any>
    val onNameChanged: Observable<String>
    val onSurnameChanged: Observable<String>
    fun showLoading(show: Boolean)
    fun showError(message: String)
}