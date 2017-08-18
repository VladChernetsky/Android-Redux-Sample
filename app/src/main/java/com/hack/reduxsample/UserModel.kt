package com.hack.reduxsample

import com.hack.reduxsample.redux.ReduxModel
import io.reactivex.subjects.PublishSubject

interface UserModel : ReduxModel<UserState, UserView> {
    val actionState: PublishSubject<String>
}