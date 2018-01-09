package com.hack.reduxsample.presentation

import com.hack.reduxsample.presentation.core.ReduxState

data class UserState(val userName: String) : ReduxState

public val emptyState = UserState("")