package com.hack.reduxsample

import com.hack.reduxsample.redux.UserStateReducer
import com.hack.reduxsample.redux.extension.asConsumer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class UserModelImpl(private val defaultUser: UserState) : UserModel {

    override val modify: PublishSubject<String> = PublishSubject.create()

    private var actions: List<Observable<UserActions>> = listOf()

    private var reducer: UserStateReducer = UserStateReducer()

    private var stateObservable: BehaviorSubject<UserState> = BehaviorSubject.createDefault(defaultUser)

    private var storeSubscription: Disposable? = null

    override fun init(view: UserView) {
        view.onUserEditTextChange.subscribe(modify.asConsumer())
        actions.plus(modify.map { UserActions.ChangeTextAction(it) })

        Observable.merge(actions)
                .scan(defaultUser) { oldState, action ->
                    reducer.reduce(oldState, action)
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribe(stateObservable::onNext)
    }

    override fun release() {
        storeSubscription?.dispose()
    }
}