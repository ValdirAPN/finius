package com.finius.core.domain

fun Double.toMoney(): String {
    return (this / 100).toString()
}