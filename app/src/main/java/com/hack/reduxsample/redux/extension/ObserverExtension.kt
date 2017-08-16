package com.hack.reduxsample.redux.extension

import io.reactivex.Observer
import io.reactivex.functions.Consumer

fun <T> Observer<T>.asConsumer(): Consumer<T> = Consumer { onNext(it) }