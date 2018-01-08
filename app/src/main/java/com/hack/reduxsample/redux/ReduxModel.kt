package com.hack.reduxsample.redux

import com.hack.reduxsample.presentation.core.ReduxState

interface ReduxModel<in State : ReduxState, in View : ReduxView<State>> {
    fun init(view: View)

    fun release()
}