package com.hack.reduxsample.presentation

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.hack.reduxsample.presentation.core.ReduxDispatcher
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserDispatcher(
        private val userView: UserView,
        private val lifecycle: Lifecycle)
    : ReduxDispatcher, LifecycleObserver {

    private val loginInteractor = LoginInteractor()

    private val store: UserStore = UserStore(emptyState, UserReducer())

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun bindIntents() {
        userView.onNameChanged.subscribe { store.dispatch(UserActions.ChangeTextAction(it)) }
        userView.onSurnameChanged.subscribe { System.out.println("User Surname: $it") }
        userView.onLoginClick.subscribe { login() }
    }

    private fun login() {

        loginInteractor.login()
                .doOnSubscribe { store.dispatch(UserActions.Loading(true)) }
                .doOnNext { store.dispatch(UserActions.Loading(false)) }
                .doOnError { store.dispatch(UserActions.Error(it)) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { store.dispatch(it) }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun dispose() {
        store.tearDown()
    }

    fun subscribe(onNext: (UserState) -> Unit) {
        store.asObservable().subscribe(onNext)
    }
}