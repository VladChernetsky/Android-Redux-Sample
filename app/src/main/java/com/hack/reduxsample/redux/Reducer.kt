package com.hack.reduxsample.redux

interface Reducer<State : ReduxState, in Action : com.hack.reduxsample.redux.Action> {
    fun reduce(state: State, action: Action): State
}