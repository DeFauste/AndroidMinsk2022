package com.example.numbers.observer

import com.example.numbers.strategy.Solver
import com.example.numbers.strategy.StrategySolver
import com.example.numbers.usecase.RandomNumbers

object Calculator : Observed {
    init {
        println("I am in init")
    }

    private var numbers = arrayListOf<Int>(0, 0, 0, 0, 0, 0)
    private lateinit var strategySolver: StrategySolver
    private var subscriber = arrayListOf<Observer>()
    private var result = "0"

    fun getResult(): String {
        return result
    }

    fun getNumbersToString(): String {
        return numbers.toString()
    }

    fun updateNumbers() {
        this.numbers.clear()
        this.numbers = generateNumbers()
        notifyObservers()
    }

    fun selectOperation(action: Solver) {
        strategySolver = StrategySolver(action)
        result = strategySolver.executed(numbers)
    }

    private fun generateNumbers(): ArrayList<Int> = RandomNumbers().getNumbers()

    override fun addObserver(observer: Observer) {
        this.subscriber.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        this.subscriber.remove(observer)
    }

    override fun notifyObservers() {
        subscriber.forEach { observer ->
            observer.handleEvent(numbers)
        }
    }
}