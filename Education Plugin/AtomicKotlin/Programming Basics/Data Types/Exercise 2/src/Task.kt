// DataTypes/Task2.kt
package dataTypesExercise2

fun main() {
    val int: Int = 10
    val double: Double = 1.1
    val boolean: Boolean = false
    val string: String = "abc"
    val character: Char = 'a'

    // Can be combined:
    val s = int + double
    val k = string + int
    val l = string + double
    val w = string + character
    val q = string + boolean
            println(
                "The type that can be combined " +
                        "with every other type using '+':"
            )
    println("String")

    // Can't be combined:
//    val e = int + string
//    val e = int + character
//    val e = int + boolean
//    val e = character + boolean
//    val e = boolean + string
}