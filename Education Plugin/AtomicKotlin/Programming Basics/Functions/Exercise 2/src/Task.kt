// Functions/Task2.kt
package functionsExercise2

fun getSum(a: Double, b: Double, c: Double): Double = sumDoubles(sumDoubles(a,b),c)
fun sumDoubles(one: Double, two:Double) = one + two
fun main() {
  println(getSum(1.0, 2.2, 3.4))  // 6.6
}