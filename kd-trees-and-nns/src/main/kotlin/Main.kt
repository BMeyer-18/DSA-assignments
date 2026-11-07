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

    val tree = buildKDTree(array)
    val closestKD = searchKDTree(tree, doubleArrayOf(1.9, 0.01))
    val closestBFS = searchBruteForce(array, doubleArrayOf(1.9, 0.01))

    println(closestKD.contentToString())
    println(closestBFS.contentToString())
}