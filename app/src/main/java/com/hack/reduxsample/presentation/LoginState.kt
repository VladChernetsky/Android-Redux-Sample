package com.hack.reduxsample.presentation

import com.hack.reduxsample.core.ReduxState

data class LoginState(
        val email: String,
        val password: String,
        val error: Throwable?,
        val loading: Boolean,
        val isLogged: Boolean
) : ReduxState

val emptyState = LoginState("", "", null, false, false)