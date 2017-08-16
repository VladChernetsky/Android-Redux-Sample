package com.hack.reduxsample

import com.hack.reduxsample.redux.ReduxView
import io.reactivex.Observable

interface UserView : ReduxView<UserState> {
    val onUserEditTextChange: Observable<String>
}