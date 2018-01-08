package com.hack.reduxsample.presentation

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class DispatcherHolderFactory(
        private val lifecycle: Lifecycle,
        private val view: UserView
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == MainDispatcherHolder::class.java) {
            @Suppress("UNCHECKED_CAST")
            return (MainDispatcherHolder(view, lifecycle) as T)
        }
        return super.create(modelClass)
    }
}