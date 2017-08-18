package com.hack.reduxsample.redux

import com.hack.reduxsample.UserActions
import com.hack.reduxsample.UserState

class UserStateReducer : Reducer<UserState, UserActions> {

    override fun reduce(state: UserState, action: UserActions): UserState {
        when (action) {
            is UserActions.ChangeTextAction -> {
                return state.copy(userName = action.text)
            }
        }
    }
}