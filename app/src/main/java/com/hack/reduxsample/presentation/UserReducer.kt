package com.hack.reduxsample.presentation

import com.hack.reduxsample.presentation.core.Action
import com.hack.reduxsample.presentation.core.Reducer

class UserReducer : Reducer<UserState> {

    override fun reduce(state: UserState, action: Action): UserState {
        return when (action) {
            is UserActions.ChangeTextAction -> state.copy(userName = action.text)
            is UserActions.Error -> state.copy(error = action.error)
            is UserActions.Loading -> state.copy(loading = action.loading)
            is UserActions.Logged -> state.copy(loading = action.logged)
            else -> {
                state.copy(error = IllegalArgumentException())
            }
        }
    }
}