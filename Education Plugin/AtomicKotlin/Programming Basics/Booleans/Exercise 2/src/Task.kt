// Booleans/Task2.kt
package booleansExercise2

fun showAnd(first: Boolean, second: Boolean) {
    println("$first && $second == ${first && second}")

}

fun showOr(first: Boolean, second: Boolean) {
    println("$first || $second == ${first || second}")
}

fun showTruthTable() {
    val t = true
    val f = false

    showAnd(t, t)
    showAnd(t, f)
    showAnd(f, f)
    showAnd(f, t)

    showOr(t, t)
    showOr(t, f)
    showOr(f, f)
    showOr(f, t)
}

fun main() {
    showTruthTable()
}