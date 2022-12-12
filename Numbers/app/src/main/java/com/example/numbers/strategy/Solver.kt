package com.example.numbers.strategy

interface Solver {
    fun getResult(numbers: ArrayList<Int>): Int
}

class SumListInt : Solver {
    override fun getResult(numbers: ArrayList<Int>): Int {
        return numbers.sum()
    }
}

class AverageListInt : Solver {
    override fun getResult(numbers: ArrayList<Int>): Int {
        return numbers.average().toInt()
    }
}

class MixedListInt : Solver {
    override fun getResult(numbers: ArrayList<Int>): Int {
        val sum = numbers.sum()
        val difference = difference(numbers)
        //обавить обработку деления на ноль
        return sum / difference
    }

    private fun difference(numbers: ArrayList<Int>): Int {
        var result = 0
        numbers.forEach {
            result -= it
        }
        return result
    }
}