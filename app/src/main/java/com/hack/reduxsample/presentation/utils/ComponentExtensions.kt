package com.hack.reduxsample.presentation.utils

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.hack.reduxsample.presentation.core.DispatcherHolder
import com.hack.reduxsample.presentation.core.ReduxDispatcher

fun <T : ReduxDispatcher, D : DispatcherHolder<T>> AppCompatActivity.provideDispatcher(clazz: Class<D>): T {
    return ViewModelProviders.of(this).get(clazz).dispatcher
}

fun <T : ReduxDispatcher, D : DispatcherHolder<T>, VF : ViewModelProvider.NewInstanceFactory> AppCompatActivity.provideDispatcher(
        clazz: Class<D>, factory: VF): T {
    return ViewModelProviders.of(this, factory).get(clazz).dispatcher
}