package org.example

import kotlin.math.pow
import kotlin.time.DurationUnit
import java.io.File

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

    writeBenchmarkData()
}

/**
 * Runs [runExperiment] over different numbers of points and dimensionalities
 * and writes data to csv file.
 */
fun writeBenchmarkData() {
    // create 2D lists to store data
    val inputs = (1..2).map { mutableListOf<Int>() }
    val times = (1..3).map { mutableListOf<Double>() }

    // iterate through different point counts and dimensionalities to get durations, and add to data (in seconds)
    for (i in 1..5) {
        val numPoints = 10.0.pow(i).toInt()
        for (j in 1..6) {
            val k = 2.0.pow(j).toInt()
            val durations = runExperiment(k, numPoints)
            inputs[0].add(numPoints)
            inputs[1].add(k)
            times[0].add(durations.component1().toDouble(DurationUnit.SECONDS))
            times[1].add(durations.component2().toDouble(DurationUnit.SECONDS))
            times[2].add(durations.component3().toDouble(DurationUnit.SECONDS))
        }
    }

    // write data to csv
    File("src/main/resources/NNS_Benchmarks.csv").printWriter().use { out ->
        out.println("KDBuild,KDSearch,BFSearch")
        for (i in inputs[0].indices)
            out.println("${inputs[0][i]},${inputs[1][i]},${times[0][i]},${times[1][i]},${times[2][i]}")
    }
}