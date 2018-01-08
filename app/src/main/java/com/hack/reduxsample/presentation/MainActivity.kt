package com.hack.reduxsample.presentation

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.hack.reduxsample.R
import com.hack.reduxsample.presentation.utils.bind
import com.hack.reduxsample.presentation.utils.provideDispatcher
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable

class MainActivity : AppCompatActivity(), LifecycleOwner, UserView {

    private val toolbar by bind<Toolbar>(R.id.toolbar)
    private val progress by bind<ProgressBar>(R.id.progress)
    private val etName by bind<EditText>(R.id.userName)
    private val etSurname by bind<EditText>(R.id.userSurname)
    private val btnLogin by bind<Button>(R.id.btnLogin)
    private val mainDispatcher by lazy {
        provideDispatcher(
                MainDispatcherHolder::class.java,
                DispatcherHolderFactory(lifecycle, this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mainDispatcher.subscribe()
    }

    override val onLoginClick: Observable<Any>
        get() = RxView.clicks(btnLogin)

    override val onNameChanged: Observable<String>
        get() = RxTextView.textChanges(etName)
                .map { it.toString() }
                .skip(1)

    override val onSurnameChanged: Observable<String>
        get() = RxTextView.textChanges(etSurname)
                .map { it.toString() }
                .skip(1)

    override fun showLoading(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }
}
