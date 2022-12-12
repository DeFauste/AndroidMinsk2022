package com.example.numbers.observer

object Calculator : Observed {
    init {
        println("I am in init of A")
    }

    private var numbers = arrayListOf<Int>()

    var subscriber = arrayListOf<Observer>()

    fun getNumbersToString(): String {
        return numbers.toString()
    }

    fun updateNumbers(numbers: ArrayList<Int>) {
        this.numbers.clear()
        this.numbers = numbers
        notifyObservers()
    }

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