package com.hack.reduxsample.presentation

import android.arch.lifecycle.Lifecycle
import com.hack.reduxsample.presentation.core.DispatcherHolder

class MainDispatcherHolder(val view: UserView, lifecycle: Lifecycle) : DispatcherHolder<UserDispatcher>(
        UserDispatcher(view, lifecycle)
)