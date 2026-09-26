package org.example

import java.io.File
import kotlin.math.ceil
import kotlin.math.log
import kotlin.math.max

/**
 * Runs unit tests for all graph classes;
 * solution to Project Euler problem 81
 */
fun main() {
    testGraph()
    testMinHeap()
    testMinPriorityQueue()

    val path = pathSum("src/main/resources/euler_matrix.txt")
    println(path?.size.toString() + ": " + path.toString())
}