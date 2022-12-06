// RepetitionWithWhile/Task1.kt
package repetitionWithWhileExercise1

fun displayContent(str: String) {
    var i = 0
    while (i < str.length) {
        println(str[i])
        i++
    }
}

fun main() {
    displayContent("abc")
}
/* Expected output:
a
b
c
*/