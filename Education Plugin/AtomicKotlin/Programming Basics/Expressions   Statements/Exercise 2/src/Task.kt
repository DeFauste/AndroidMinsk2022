// ExpressionsStatements/Task2.kt
package expressionsAndStatementsExercise2

fun f(first: Int, second: Int) = first + second

fun g(first: String, second: String) = first + second

fun h() = println("h()")

fun main() {
    val valF = f(1, 2)
    val valG = g("a", "B")
    val valH = h()
    println("Int")
    println("String")
    println("Unit")
}