package com.example.numbers.strategy

interface Solver {
    fun getResult(numbers: ArrayList<Int>): String
}

class SumListInt : Solver {
    override fun getResult(numbers: ArrayList<Int>): String {
        return numbers.sum().toString()
    }
}

class AverageListInt : Solver {
    override fun getResult(numbers: ArrayList<Int>): String {
        return numbers.average().toInt().toString()
    }
}

class MixedListInt : Solver {
    override fun getResult(numbers: ArrayList<Int>): String {
        val sum = numbers.sum()
        val difference = difference(numbers)
        if(difference == 0) return "The divisor is less than zero! \n Here's a cookie."
        return (sum / difference).toString()
    }

    private fun difference(numbers: ArrayList<Int>): Int {
        var result = numbers[0]
        for (n in 1..numbers.size) {
            result -= n
        }
        return result
    }
}