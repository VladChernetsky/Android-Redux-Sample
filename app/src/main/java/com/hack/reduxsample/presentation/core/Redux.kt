package com.hack.reduxsample.presentation.core

import io.reactivex.Observable

interface ReduxStore<State : ReduxState> {
    val stateObservable: Observable<State>
}

interface Reducer<State : ReduxState, in Action : com.hack.reduxsample.presentation.core.Action> {
    fun reduce(state: State, action: Action): State
}

interface Action

interface ReduxState

interface ReduxDispatcher