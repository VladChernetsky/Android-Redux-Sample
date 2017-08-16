package com.hack.reduxsample.redux

import io.reactivex.Observable

interface ReduxStore<State : ReduxState> {
    val stateObservable: Observable<State>
}