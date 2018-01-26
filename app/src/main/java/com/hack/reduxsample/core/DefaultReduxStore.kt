package com.hack.reduxsample.core

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

open class DefaultReduxStore<STATE : ReduxState>(
        private val defaultState: STATE,
        reducer: Reducer<STATE>
) : ReduxStore<STATE> {

    protected val stateSubject = BehaviorSubject.create<STATE>()
    protected val actionSubject = PublishSubject.create<Action>()
    protected val subscriptions = CompositeDisposable()

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

    override fun asObservable(): Observable<STATE> = stateSubject

    override fun currentState(): STATE {
        return if (stateSubject.hasValue()) stateSubject.value else defaultState
    }

    override fun tearDown() {
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
    }
}