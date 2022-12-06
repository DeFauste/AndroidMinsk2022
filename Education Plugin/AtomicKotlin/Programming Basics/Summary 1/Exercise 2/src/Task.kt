// Summary1/Task2.kt
package summaryIExercise2

fun other(s: String): String {
    var result = ""
    for ((index, value) in s.withIndex()) if (index % 2 == 0) result += value
    return result
}

fun main() {
    println(other("cement"))
}
/* Output:
cmn
*/