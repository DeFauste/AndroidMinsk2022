package com.example.numbers.usecase

import kotlin.random.Random

class RandomNumbers {
    fun getNumbers(): ArrayList<Int> {
        val numbersList = hashSetOf<Int>()
        while (numbersList.size < 4) {
            numbersList.add(Random.nextInt(-100,100))
        }
        return arrayListOf(numbersList)
    }

    private fun <T> arrayListOf(elements: HashSet<T>): ArrayList<T> {
        val result = arrayListOf<T>()
        elements.forEach {
            result.add(it)
        }
        return result
    }
}