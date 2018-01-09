package com.hack.reduxsample.presentation

import com.hack.reduxsample.presentation.core.Action
import com.hack.reduxsample.presentation.core.ReduxStore
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class UserStore(
        private val defaultState: UserState,
        private val reducer: UserReducer
) : ReduxStore<UserState> {

    private val stateSubject = BehaviorSubject.create<UserState>()
    private val actionSubject = PublishSubject.create<Action>()
    private val subscriptions = CompositeDisposable()

    init {
        subscriptions.add(
                actionSubject
                        .scan(defaultState, reducer::reduce)
                        .distinctUntilChanged()
                        .subscribe { stateSubject.onNext(it) }
        )
    }

    override fun dispatch(action: Action) {
        actionSubject.onNext(action)
    }

    override fun dispatch(actions: Observable<Action>) {
        subscriptions.add(actions.subscribe { dispatch(it) })
    }

    override fun asObservable(): Observable<UserState> = stateSubject

    override fun currentState(): UserState {
        return if (stateSubject.hasValue()) stateSubject.value else defaultState
    }

    override fun tearDown() {
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
    }
}