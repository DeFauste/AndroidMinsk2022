package com.example.numbers.observer

interface Observed {
    fun addObserver(observer: Observer)

    fun removeObserver(observer: Observer)

    fun notifyObservers()
}