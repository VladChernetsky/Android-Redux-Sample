package com.hack.reduxsample.presentation.core

import io.reactivex.Observable

interface ReduxStore<State : ReduxState> {
    fun dispatch(action: Action)
    fun dispatch(actions: Observable<Action>)
    fun asObservable(): Observable<State>
    fun currentState(): State
    fun tearDown()

}

interface Reducer<State : ReduxState> {
    fun reduce(state: State, action: Action): State
}

interface Action

interface ReduxState

interface ReduxDispatcher