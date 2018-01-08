package com.hack.reduxsample.presentation.core

import android.arch.lifecycle.ViewModel

open class DispatcherHolder<out D : ReduxDispatcher>(
        val dispatcher: D
) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        //TODO: implement method
    }
}