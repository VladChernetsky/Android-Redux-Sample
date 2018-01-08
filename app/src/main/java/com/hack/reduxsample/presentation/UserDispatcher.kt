package com.hack.reduxsample.presentation

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import com.hack.reduxsample.presentation.core.ReduxDispatcher

class UserDispatcher(
        private val userView: UserView,
        private val lifecycle: Lifecycle)
    : ReduxDispatcher, LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun bindIntents() {
        userView.onNameChanged.subscribe({ System.out.println("User Name: $it") })
        userView.onSurnameChanged.subscribe({ System.out.println("User Surname: $it") })
        userView.onLoginClick.subscribe({ System.out.println("Login Clicked") })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun dispose() {
        Log.i("UserDispatcher", "dispose")
    }

    fun subscribe() {

    }
}