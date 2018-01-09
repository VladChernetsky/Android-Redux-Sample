package com.hack.reduxsample.presentation

import com.hack.reduxsample.presentation.core.Action
import com.hack.reduxsample.presentation.core.Reducer

class UserReducer : Reducer<UserState> {

    override fun reduce(state: UserState, action: Action): UserState {
        when (action) {
            is UserActions.ChangeTextAction -> {
                return state.copy(userName = action.text)
            }
        }
    }
}