package com.hack.reduxsample.presentation

import com.hack.reduxsample.presentation.core.ReduxStore
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class UserStore(private val defaultState: UserState) : ReduxStore<UserState> {

    override val stateObservable: Observable<UserState>
        get() = BehaviorSubject.createDefault(defaultState)
}