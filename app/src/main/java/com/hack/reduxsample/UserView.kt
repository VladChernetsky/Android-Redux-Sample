package com.hack.reduxsample

import com.hack.reduxsample.redux.ReduxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface UserView : ReduxView<UserState> {
    val onModify: Observable<String>

    val displayData: PublishSubject<String>
}