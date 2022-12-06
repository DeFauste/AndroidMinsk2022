// Summary1/Task10.kt
package summaryIExercise10

fun showSnake(rows: Int, columns: Int) {
    val width = (rows * columns).toString().length + 1
    for (r in 0 until rows) {
        for (c in 0 until columns) {
            val value = if (r % 2 == 0) {
                r * columns + c
            } else {
                r * columns +(columns - 1 - c)
            }
            print("%${width}d".format(value))
        }
        println()
    }
}

fun main() {
    showSnake(2, 3)
    println()
    showSnake(4, 5)
}
/* Output:
 0 1 2
 5 4 3

  0  1  2  3  4
  9  8  7  6  5
 10 11 12 13 14
 19 18 17 16 15
*/