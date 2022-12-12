package com.example.numbers.observer

import android.util.Log
import java.io.Console

class Subscriber(val name: String) : Observer {
    override fun handleEvent(number: ArrayList<Int>) {
       println("12345")
    }
}