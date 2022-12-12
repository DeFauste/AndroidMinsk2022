package com.example.numbers.observer

interface Observer {
    fun handleEvent(number: ArrayList<Int>)
}