package com.hack.reduxsample.extensions

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.hack.reduxsample.core.ReduxDispatcher
import com.hack.reduxsample.core.ReduxState
import com.hack.reduxsample.core.ReduxView

fun <STATE : ReduxState, VIEW : ReduxView<STATE>, T : ReduxDispatcher<STATE, VIEW>> FragmentActivity.provideDispatcher(clazz: Class<T>): T {
    return ViewModelProviders.of(this).get(clazz)
}

fun <STATE : ReduxState, VIEW : ReduxView<STATE>, T : ReduxDispatcher<STATE, VIEW>> Fragment.provideDispatcher(clazz: Class<T>): T {
    return ViewModelProviders.of(this).get(clazz)
}