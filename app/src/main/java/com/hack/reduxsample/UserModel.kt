package com.hack.reduxsample

import com.hack.reduxsample.redux.ReduxModel
import io.reactivex.Observer

interface UserModel : ReduxModel<UserState, UserView> {
    val modify: Observer<String>
}