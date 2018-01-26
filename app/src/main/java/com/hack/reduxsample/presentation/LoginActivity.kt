package com.hack.reduxsample.presentation

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.hack.reduxsample.R
import com.hack.reduxsample.extensions.bind
import com.hack.reduxsample.extensions.provideDispatcher
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable

class LoginActivity : AppCompatActivity(), LoginView {

    private val toolbar by bind<Toolbar>(R.id.toolbar)
    private val progress by bind<ProgressBar>(R.id.progress)
    private val etEmail by bind<EditText>(R.id.email)
    private val etPassword by bind<EditText>(R.id.password)
    private val btnLogin by bind<Button>(R.id.btnLogin)
    private var snackbar: Snackbar? = null

    private val mainDispatcher by lazy {
        provideDispatcher(LoginDispatcher::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override val loginClick: Observable<Any>
        get() = RxView.clicks(btnLogin)

    override val emailChange: Observable<String>
        get() = RxTextView.textChanges(etEmail)
                .map { it.toString() }
                .skip(1)

    override val passwordChange: Observable<String>
        get() = RxTextView.textChanges(etPassword)
                .map { it.toString() }
                .skip(1)

    override fun showLoading(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showError(message: String) {
        snackbar = Snackbar.make(btnLogin, message, Snackbar.LENGTH_INDEFINITE)
        snackbar!!.show()
    }

    override fun onStart() {
        super.onStart()
        mainDispatcher.subscribe(this, this::renderState)
    }

    private fun renderState(state: LoginState) {
        showLoading(state.loading)

        if (state.error != null) {
            showError(state.error.localizedMessage)
        } else {
            snackbar?.dismiss()
        }

        if (state.isLogged) {
            Toast.makeText(this, "Logged", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        mainDispatcher.dispose()
    }
}
