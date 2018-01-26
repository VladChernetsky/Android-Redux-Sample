package com.hack.reduxsample.presentation

import com.hack.reduxsample.core.Action
import com.hack.reduxsample.core.Reducer

class LoginReducer : Reducer<LoginState> {

    override fun reduce(oldState: LoginState, action: Action): LoginState {
        return when (action) {
            is LoginActions.EmailChangeAction -> oldState.copy(email = action.email)
            is LoginActions.PasswordChangeAction -> oldState.copy(password = action.password)
            is LoginActions.Error -> oldState.copy(error = action.error)
            is LoginActions.Loading -> oldState.copy(loading = action.loading)
            is LoginActions.Logged -> oldState.copy(isLogged = action.logged)
            else -> {
                oldState.copy(error = IllegalArgumentException())
            }
        }
    }
}