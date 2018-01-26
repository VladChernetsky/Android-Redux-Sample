package com.hack.reduxsample.presentation

import com.hack.reduxsample.core.ReduxDispatcher
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class LoginDispatcher : ReduxDispatcher<LoginState, LoginView>(emptyState, LoginReducer()) {

    private val loginInteractor = LoginInteractor()
    private var subscriptions = CompositeDisposable()

    override fun subscribe(view: LoginView, onNext: (LoginState) -> Unit) {
        System.out.println(store.currentState())

        subscriptions = CompositeDisposable()
        subscriptions.add(
                store.asObservable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onNext))

        subscriptions.add(
                view.emailChange
                        .subscribe { store.dispatch(LoginActions.EmailChangeAction(it)) })

        subscriptions.add(
                view.passwordChange
                        .subscribe { store.dispatch(LoginActions.PasswordChangeAction(it)) })
        subscriptions.add(
                view.loginClick
                        .subscribe { login() })
    }

    private fun login() {
        subscriptions.add(
                loginInteractor.login(store.currentState().email, store.currentState().password)
                        .delay(3, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe { store.dispatch(LoginActions.Loading(true)) }
                        .doOnNext { store.dispatch(LoginActions.Loading(false)) }
                        .doOnError { store.dispatch(LoginActions.Error(it)) }
                        .subscribe {
                            store.dispatch(it)
                            if (it is LoginActions.Error) {
                                Observable
                                        .timer(3, TimeUnit.SECONDS)
                                        .subscribe { store.dispatch(LoginActions.Error(null)) }
                            }
                        })
    }

    override fun dispose() {
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
    }
}