package com.hack.reduxsample.presentation

import com.hack.reduxsample.presentation.core.ReduxState

data class UserState(
        val userName: String,
        val password: String,
        val error: Throwable?,
        val loading: Boolean,
        val isLogged: Boolean
) : ReduxState

val emptyState = UserState("", "", null, false, false)