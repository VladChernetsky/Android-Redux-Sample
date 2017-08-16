package com.hack.reduxsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.hack.reduxsample.redux.ReduxModel
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val model: ReduxModel<UserState, UserView> = UserModelImpl(UserState(""))
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
            override val onUserEditTextChange: Observable<String> = etUserName.textChanges().map { it.toString() }
        }

        intent(view.onUserEditTextChange
                .map { it.toString() }
                .distinctUntilChanged()
                .subscribe({
                    if (etUserName.text.toString() != it)
                        etUserName.setText(it)

                    Log.i("MainActivity", it)
                }))

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
