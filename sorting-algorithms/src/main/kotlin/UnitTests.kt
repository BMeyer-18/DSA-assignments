package org.sorting_algorithms

import kotlin.random.Random

/**
 * Tests heap sort for randomly generated lists of doubles, ensuring that
 * elements are successfully sorted in ascending order.
 * @throws RuntimeException if test fails
 */
fun testHeapSort() {
    for (i in 0..<5) {
        val unsortedList = (0 until 100).map { Random.nextDouble(1000.0) }
        val sortedList = heapSort(unsortedList)
        if (!isSorted(sortedList))
            throw RuntimeException("heap sort failed to sort list")
    }
    println("Passed test for heap sort")
}

/**
 * Tests radix sort for randomly generated lists of ints, ensuring that
 * elements are successfully sorted in ascending order.
 * @throws RuntimeException if test fails
 */
fun testRadixSort() {
    for (i in 0..<5) {
        val unsortedList = (0 until 100).map { Random.nextInt(1000) }
        val sortedList = radixSort(unsortedList).map { it.toDouble() }
        if (!isSorted(sortedList))
            throw RuntimeException("radix sort failed to sort list")
    }
    println("Passed test for radix sort")
}

/**
 * Tests merge sort for randomly generated lists of doubles, ensuring that
 * elements are successfully sorted in ascending order.
 * @throws RuntimeException if test fails
 */
fun testMergeSort() {
    for (i in 0..<5) {
        val unsortedList = (0 until 100).map { Random.nextDouble(1000.0) }
        val sortedList = mergeSort(unsortedList)
        if (!isSorted(sortedList))
            throw RuntimeException("merge sort failed to sort list")
    }
    println("Passed test for merge sort")
}

/**
 * @return true if [list] is sorted in ascending order, false otherwise
 */
fun isSorted(list: List<Double>): Boolean {
    if (list.size < 2)
        return true

    for (i in 1..<list.size)
        if (list[i] < list[i-1])
            return false
    return true
}