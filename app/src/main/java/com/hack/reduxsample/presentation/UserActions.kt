package com.hack.reduxsample.presentation

import com.hack.reduxsample.presentation.core.Action

sealed class UserActions : Action {
    class ChangeTextAction(val text: String) : UserActions()
}