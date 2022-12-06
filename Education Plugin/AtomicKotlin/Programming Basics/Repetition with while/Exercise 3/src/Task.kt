// RepetitionWithWhile/Task3.kt
package repetitionWithWhileExercise3

fun sumOfEven(n: Int): Int {
    var evenNumbSum = 0
    var i = 0
    while (i <= n) {
        evenNumbSum += i
        i += 2
    }
    return evenNumbSum
}

fun main() {
    println(sumOfEven(10))  // 30
}