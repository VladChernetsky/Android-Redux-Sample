package com.hack.reduxsample.extensions

import java.util.*

fun <S : B, B> Collections.castToSubclass(list: ArrayList<B>): ArrayList<S> {
    val arrayList = arrayListOf<S>()
    arrayList.addAll(list.map { it as S })
    return arrayList
}