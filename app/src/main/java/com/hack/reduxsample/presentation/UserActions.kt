package com.hack.reduxsample.presentation

import com.hack.reduxsample.presentation.core.Action

sealed class UserActions : Action {
    data class ChangeTextAction(val text: String) : UserActions()
    data class Logged(val logged: Boolean) : UserActions()
    data class Error(val error: Throwable) : UserActions()
    data class Loading(val loading: Boolean) : UserActions()
}