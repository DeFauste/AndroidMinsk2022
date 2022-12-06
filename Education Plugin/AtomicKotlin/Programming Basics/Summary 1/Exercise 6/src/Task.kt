// Summary1/Task6.kt
package summaryIExercise6

fun everyFifthNonSpace(s: String) {
    for ((index, value) in s.replace(" ", "").withIndex()) {
        if ((index + 1) % 5 == 0) println(value)
    }
}

fun main() {
    everyFifthNonSpace("abc d e fgh ik")
}
/* Output:
e
k
*/