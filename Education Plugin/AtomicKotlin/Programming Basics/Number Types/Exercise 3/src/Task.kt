// NumberTypes/Task3.kt
package numberTypesExercise3

fun convertFahrenheitToCelsius(f: Int): Double = (5.0 / 9.0) * (f - 32)


fun convertCelsiusToFahrenheit(c: Int): Double = c / (5.0 / 9.0) + 32


fun main() {
    println(convertFahrenheitToCelsius(68)) // 20.0
    println(convertCelsiusToFahrenheit(20)) // 68.0
}