package com.hack.reduxsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hack.reduxsample.redux.extension.asConsumer
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val model: UserModel = UserModelImpl(UserState(""))
    private val compositeSubscription = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onDestroy() {
        model.release()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()

        val view = object : UserView {
            override val onModify: Observable<String> = etUserName.textChanges().map { it.toString() }
            override val displayData: PublishSubject<String> = PublishSubject.create()
        }

        intent(view.displayData
                .map { it.toString() }
                .distinctUntilChanged()
                .subscribe({
                    etUserSurName.setText(it)
                }))

        intent(view.onModify
                .map { it.toString() }
                .subscribe(model.actionState.asConsumer())
        )

        model.init(view)
    }

    override fun onStop() {
        compositeSubscription.dispose()
        super.onStop()
    }

    private fun intent(disposable: Disposable) {
        compositeSubscription.add(disposable)
    }
}
