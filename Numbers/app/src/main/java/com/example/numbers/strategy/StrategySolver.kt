package com.example.numbers.strategy

class StrategySolver(curSolver: Solver) {
    var solver: Solver = curSolver

    fun executed(numbers: ArrayList<Int>): Int {
        return solver.getResult(numbers)
    }
}