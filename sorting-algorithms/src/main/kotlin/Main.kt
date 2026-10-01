package org.sorting_algorithms

import kotlin.time.measureTime
import kotlin.math.pow
import kotlin.time.DurationUnit
import kotlin.random.Random

/**
 * Main function, runs unit tests and finds time complexity
 */
fun main() {
    testSortingAlgorithms()
    getRuntimes()
}

fun getRuntimes() {
    val heapRuntimes = mutableListOf<Double>()
    for (i in 1..6) {
        val runtimeList = mutableListOf<Double>()
        val unsortedList = (0 until 10.0.pow(i).toInt()).map { Random.nextDouble(100000.0) }
        val runtime = measureTime { heapSort(unsortedList) }
        heapRuntimes.add(runtime.toDouble(DurationUnit.SECONDS))
    }
    println(heapRuntimes)
}