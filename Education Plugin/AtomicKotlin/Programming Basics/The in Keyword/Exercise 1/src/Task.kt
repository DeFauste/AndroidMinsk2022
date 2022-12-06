// InKeyword/Task1.kt
package theInKeywordExercise1

fun getAlphabet(): String {
    var result = ""
    ('a'..'z').forEach { char ->
        result += char.toString()
    }
    return result
}

fun main() {
    println(getAlphabet())  // abcdefghijklmnopqrstuvwxyz
}