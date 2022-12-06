// Summary1/Task5.kt
package summaryIExercise5

fun everyFifth(start: Int, end: Int) {
    var count = 1
    for (i in start..end) {
        if (count == 5) {
            println(i)
            count = 1
        } else {
            count++
        }
    }
}

fun main() {
    everyFifth(11, 30)
    everyFifth(3, 14)
}
/* Output:
15
20
25
30
*/