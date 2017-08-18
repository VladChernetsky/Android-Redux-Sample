package com.hack.reduxsample


import com.hack.reduxsample.redux.Reducer
import com.hack.reduxsample.redux.UserStateReducer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class UserModelImpl(private val defaultUser: UserState) : UserModel {

    private var actions: List<Observable<UserActions>> = listOf()

    override val actionState: PublishSubject<String> = PublishSubject.create()

    private var reducer: Reducer<UserState, UserActions> = UserStateReducer()

    private var stateObservable: BehaviorSubject<UserState> = BehaviorSubject.createDefault(defaultUser)

    private var storeSubscription: Disposable? = null

    override fun init(view: UserView) {
        storeSubscription = stateObservable
                .map(UserState::userName)
                .map { it.toString() }
                .subscribe(view.displayData::onNext)

        addAction(actionState.map { UserActions.ChangeTextAction(it) })

        Observable.merge(actions)
                .scan(defaultUser) { oldState, action ->
                    reducer.reduce(oldState, action)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stateObservable::onNext)

    }

    private fun addAction(action: Observable<UserActions>) {
        actions += action
    }

    override fun release() {
        storeSubscription?.dispose()
    }
}