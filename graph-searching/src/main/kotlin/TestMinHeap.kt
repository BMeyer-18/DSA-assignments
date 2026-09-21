package org.example

import kotlin.math.floor

/**
 * Runs all tests for the MinHeap class
 * @throws RuntimeException if a test fails
 */
fun testMinHeap() {
    testEmptyHeap()
    testInsertAscending()
    testInsertDescending()
    testPopRoot()
    testUpdateValue()
    println("Passed all tests for MinHeap class\n")
}

/**
 * Runs functions on an empty heap to make sure it doesn't break; uses ints
 * @throws RuntimeException if a test fails
 */
fun testEmptyHeap() {
    val heap = MinHeap<Int>()
    if (heap.getRoot() !== null)
        throw RuntimeException("getRoot returned not-null for empty heap")
    if (heap.popRoot() !== null)
        throw RuntimeException("popRoot returned not-null for empty heap")
    heap.updateValue(3, 5.0)
    if (heap.getSize() != 0)
        throw RuntimeException("getSize returned nonzero value for empty heap")
    if (!heap.isEmpty())
        throw RuntimeException("isEmpty returned false for empty heap")

    println("Passed tests for empty MinHeap")
}

/**
 * Inserts elements of increasing value to the heap to maintain ordering; uses doubles
 * @throws RuntimeException if a test fails
 */
fun testInsertAscending() {
    val heap = MinHeap<Double>()
    heap.insert(0.5, 0.0)
    heap.insert(1.0, 2.0)
    heap.insert(1.5, 3.0)
    heap.insert(2.0, 4.0)
    heap.insert(2.5, 4.0)
    heap.insert(3.0, 5.0)
    heap.insert(3.5, 6.0)
    heap.insert(4.0, 7.0)
    heap.insert(4.5, 8.0)
    heap.insert(5.0, 9.0)
    if (!isHeapOrdered(heap))
        throw RuntimeException("Heap unordered after adding ascending elements")
    if (heap.getRoot() != 0.5)
        throw RuntimeException("getRoot returned wrong root after adding ascending values")
    if (heap.getSize() != 10)
        throw RuntimeException("getSize returned wrong size after adding ascending values")
    if (heap.isEmpty())
        throw RuntimeException("isEmpty returned empty after adding ascending values")
    println("Passed tests for inserting elements of ascending values")
}

/**
 * Inserts elements of descending value to the heap to maintain ordering; uses strings
 * @throws RuntimeException if a test fails
 */
fun testInsertDescending() {
    val heap = MinHeap<String>()
    heap.insert("One", 0.0)
    heap.insert("Two", -2.0)
    heap.insert("Three", -3.0)
    heap.insert("Four", -4.0)
    heap.insert("Five", -4.0)
    heap.insert("Six", -5.0)
    heap.insert("Seven", -6.0)
    heap.insert("Eight", -7.0)
    heap.insert("Nine", -8.0)
    heap.insert("Ten", -9.0)
    if (!isHeapOrdered(heap))
        throw RuntimeException("Heap unordered after adding ascending elements")
    if (heap.getRoot() != "Ten")
        throw RuntimeException("getRoot returned wrong root after adding ascending values")
    if (heap.getSize() != 10)
        throw RuntimeException("getSize returned wrong size after adding ascending values")
    if (heap.isEmpty())
        throw RuntimeException("isEmpty returned empty after adding ascending values")
    println("Passed tests for inserting elements of descending values")
}

/**
 * Tests heap readjustment after popRoot with (siftDown and siftUp); uses chars
 * @throws RuntimeException if a test fails
 */
fun testPopRoot() {
    val heap = MinHeap<Char>()
    heap.insert('A', 0.0)
    heap.insert('B', 10.0)
    heap.insert('C', 2.0)
    heap.insert('D', 8.0)
    heap.insert('E', 4.0)
    heap.insert('F', 6.0)
    heap.insert('G', 4.0)
    heap.insert('H', 5.0)
    heap.insert('I', 9.0)
    heap.insert('J', 1.0)
    if (!isHeapOrdered(heap))
        throw RuntimeException("Heap unordered after adding elements")
    if (heap.getRoot() != 'A')
        throw RuntimeException("getRoot returned wrong root after adding elements")
    if (heap.popRoot() != 'A')
        throw RuntimeException("popRoot returned wrong root after adding elements")
    if (!isHeapOrdered(heap))
        throw RuntimeException("Heap unordered after removing one element")
    if (heap.popRoot() != 'J')
        throw RuntimeException("popRoot returned wrong root after removing one element")
    if (!isHeapOrdered(heap))
        throw RuntimeException("Heap unordered after removing two elements")
    if (heap.getSize() != 8)
        throw RuntimeException("getSize returned wrong size after removing two elements")
    if (heap.getRoot() != 'C')
        throw RuntimeException("getRoot returned wrong root after removing two elements")
    println("Passed tests for popRoot function")
}

/**
 * Tests heap readjustment after updating values (greater and lesser values); uses bytes
 * @throws RuntimeException if a test fails
 */
fun testUpdateValue() {
    val heap = MinHeap<Byte>()
    heap.insert(1, 1.0)
    heap.insert(2, 10.0)
    heap.insert(3, 2.0)
    heap.insert(4, 8.0)
    heap.insert(5, 4.0)
    heap.insert(6, 6.0)
    heap.insert(7, 4.0)
    heap.insert(8, 5.0)
    heap.insert(9, 9.0)
    heap.insert(10, 0.0)
    if (!isHeapOrdered(heap))
        throw RuntimeException("Heap unordered after adding elements")
    if (heap.getRoot() != 10.toByte())
        throw RuntimeException("getRoot returned wrong root after adding elements")
    heap.updateValue(5.toByte(), -5.0)
    if (!isHeapOrdered(heap))
        throw RuntimeException("Heap unordered after decreasing element value")
    if (heap.getRoot() != 5.toByte())
        throw RuntimeException("getRoot returned wrong root after decreasing element value")
    heap.updateValue(10.toByte(), 100.0)
    if (!isHeapOrdered(heap))
        throw RuntimeException("Heap unordered after increasing element value")
    heap.updateValue(2.toByte(), 10.0)
    if (!isHeapOrdered(heap))
        throw RuntimeException("Heap unordered after maintaining element value")
    println("Passed tests for updateValue function")
}

/**
 * Checks a [heap] to make sure elements are ordered correctly.
 * If any element is less than its parent, returns false.
 * @return true if heap is ordered, false otherwise
 */
fun <T>isHeapOrdered(heap: MinHeap<T>): Boolean {
    val values = heap.getValues()
    for (i in values.size-1 downTo 1)
        if (values[i] < values[floor((i-1)/2.0).toInt()])
            return false
    return true
}