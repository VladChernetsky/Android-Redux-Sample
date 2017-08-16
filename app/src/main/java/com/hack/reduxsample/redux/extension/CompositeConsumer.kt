package com.hack.reduxsample.redux.extension

import io.reactivex.functions.Consumer

class CompositeConsumer<Value>(private vararg val consumers: Consumer<Value>) : Consumer<Value> {
    override fun accept(value: Value) {
        consumers.forEach { it.accept(value) }
    }
}