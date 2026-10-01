package org.sorting_algorithms

import kotlin.time.measureTime
import kotlin.math.pow
import kotlin.time.DurationUnit
import kotlin.random.Random
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets
import com.opencsv.CSVWriter

/**
 * Main function, runs unit tests and writes time complexity data to file
 */
fun main() {
    testSortingAlgorithms()
    writeRuntimeData("src/main/resources/runtimeData.csv")
}

/**
 * Writes runtime data to csv at [filepath]
 */
fun writeRuntimeData(filepath: String) {
    val runtimeData = mutableListOf<List<Double>>()
    for (i in 1..6) {
        runtimeData.add(getRuntimes(10.0.pow(i).toInt()))
        println("Finished round $i")
    }

    val headers = arrayOf("Size","HeapSort","RadixSort","MergeSort","InsertionSort")

    FileOutputStream(filepath).use { fos ->
        OutputStreamWriter(fos, StandardCharsets.UTF_8).use { osw ->
            CSVWriter(osw).use { writer ->
                writer.writeNext(headers)
                for (i in 1..6) {
                    val row = (listOf(10.0.pow(i)) + runtimeData[i-1]).toDoubleArray().map { it.toString() }
                    writer.writeNext(row.toTypedArray())
                }
            }
        }
    }
}

/**
 * Runs through all four sorting algorithms for lists of a particular
 * [size] five times to get the average runtimes of each.
 * @return list of runtimes for each sorting algorithm:
 * heap, radix, merge, then insertion
 */
fun getRuntimes(size: Int): List<Double> {
    val runtimeList = mutableListOf<Double>()

    // get average runtime for heap sort
    var runtimeSum = 0.0
    for (i in 0..<5) {
        val unsortedList = (0 until size).map { Random.nextDouble(100000.0) }
        val runtime = measureTime { heapSort(unsortedList) }
        runtimeSum += runtime.toDouble(DurationUnit.SECONDS)
    }
    runtimeList.add(runtimeSum/5.0)
    println("Finished heapsort")

    // get average runtime for radix sort
    runtimeSum = 0.0
    for (i in 0..<5) {
        val unsortedList = (0 until size).map { Random.nextInt(100000) }
        val runtime = measureTime { radixSort(unsortedList) }
        runtimeSum += runtime.toDouble(DurationUnit.SECONDS)
    }
    runtimeList.add(runtimeSum/5.0)
    println("Finished radixsort")

    // get average runtime for merge sort
    runtimeSum = 0.0
    for (i in 0..<5) {
        val unsortedList = (0 until size).map { Random.nextDouble(100000.0) }
        val runtime = measureTime { mergeSort(unsortedList) }
        runtimeSum += runtime.toDouble(DurationUnit.SECONDS)
    }
    runtimeList.add(runtimeSum/5.0)
    println("Finished mergesort")

    // get average runtime for insertion sort
    runtimeSum = 0.0
    for (i in 0..<5) {
        val unsortedList = (0 until size).map { Random.nextDouble(100000.0) }
        val runtime = measureTime { insertionSort(unsortedList) }
        runtimeSum += runtime.toDouble(DurationUnit.SECONDS)
    }
    runtimeList.add(runtimeSum/5.0)
    println("Finished insertionsort")

    return runtimeList
}