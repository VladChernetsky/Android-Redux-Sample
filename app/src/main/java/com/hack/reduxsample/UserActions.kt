package com.hack.reduxsample

import com.hack.reduxsample.redux.Action

sealed class UserActions : Action {
    class ChangeTextAction(val text: String) : UserActions()
}