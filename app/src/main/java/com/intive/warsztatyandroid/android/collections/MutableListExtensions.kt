package com.intive.warsztatyandroid.android.collections

fun <T> MutableList<T>.replaceAllItems(newList: List<T>) {
    clear()
    addAll(newList)
}