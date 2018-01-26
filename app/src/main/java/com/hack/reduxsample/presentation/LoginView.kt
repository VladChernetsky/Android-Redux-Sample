package com.hack.reduxsample.presentation

import com.hack.reduxsample.core.ReduxView
import io.reactivex.Observable

interface LoginView : ReduxView<LoginState> {
    val loginClick: Observable<Any>
    val emailChange: Observable<String>
    val passwordChange: Observable<String>
    fun showLoading(show: Boolean)
    fun showError(message: String)
}