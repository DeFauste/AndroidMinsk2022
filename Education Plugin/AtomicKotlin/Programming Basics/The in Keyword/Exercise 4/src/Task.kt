// InKeyword/Task4.kt
package theInKeywordExercise4

fun isValidCharacter(c: Char) = c in 'A'..'Z' || c in 'a'..'z' || c in '0'..'9' || c == '_'


fun isValidIdentifier(s: String): Boolean {
    if (s.isEmpty() || !(s[0] in 'a'..'z' || s[0] in 'A'..'Z' || s[0] == '_')) return false
    s.forEach { c ->
        if (!isValidCharacter(c)) return false
    }
    return true
}

fun main() {
    println(isValidIdentifier("name"))  // true
    println(isValidIdentifier("0name"))  // false
    println(isValidIdentifier("0nam!e"))  // false
    println(isValidIdentifier("_nam!e"))  // false
    println(isValidIdentifier("_N01"))  // true
    println(isValidIdentifier("&%$"))  // false
}