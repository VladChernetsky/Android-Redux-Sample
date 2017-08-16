package com.hack.reduxsample.redux

interface ReduxModel<in State : ReduxState, in View : ReduxView<State>> {
    fun init(view: View)

    fun release()
}