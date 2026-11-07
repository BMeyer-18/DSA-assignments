package org.example

/**
 *
 */
fun main() {
    val array = arrayOf(doubleArrayOf(1.0, 5.0),
                        doubleArrayOf(-1.0, 2.0),
                        doubleArrayOf(4.0, 4.0),
                        doubleArrayOf(2.0, 0.0),
                        doubleArrayOf(-3.0, -3.0),
                        doubleArrayOf(9.0, 1.0),
                        doubleArrayOf(4.0, 1.0),
                        doubleArrayOf(3.0, 8.0))

    val xmedian = quickselect(array, 0)
    val ymedian = quickselect(array, 1)
    println("($xmedian, $ymedian)")
}