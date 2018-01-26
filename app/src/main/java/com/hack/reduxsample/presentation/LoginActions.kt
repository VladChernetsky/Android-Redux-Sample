package com.hack.reduxsample.presentation

import com.hack.reduxsample.core.Action

sealed class LoginActions : Action {
    data class EmailChangeAction(val email: String) : LoginActions()
    data class PasswordChangeAction(val password: String) : LoginActions()
    data class Logged(val logged: Boolean) : LoginActions()
    data class Error(val error: Throwable?) : LoginActions()
    data class Loading(val loading: Boolean) : LoginActions()
}