package com.hack.reduxsample.core

import android.arch.lifecycle.ViewModel

abstract class ReduxDispatcher<STATE : ReduxState, in VIEW : ReduxView<STATE>>(
        defaultState: STATE,
        reducer: Reducer<STATE>) : ViewModel() {

    protected val store: ReduxStore<STATE> = DefaultReduxStore(defaultState, reducer)

    init {
        System.out.println("Create Dispatcher")
    }

    abstract fun subscribe(view: VIEW, onNext: (STATE) -> Unit)

    abstract fun dispose()

    override fun onCleared() {
        super.onCleared()
        System.out.println("Dispose ViewModel")
        store.tearDown()
    }
}