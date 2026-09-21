package org.example

import kotlin.math.floor

/**
 * Runs all tests for the MinPriorityQueue class
 * @throws RuntimeException if a test fails
 */
fun testMinPriorityQueue() {
    testEmptyQueue()
    testFullQueue()
    testUpdateQueue()
    println("Passed all tests for MinPriorityQueue class\n")
}

/**
 * Runs functions on an empty queue to make sure it doesn't break; uses ints
 * @throws RuntimeException if a test fails
 */
fun testEmptyQueue() {
    val queue = MinPriorityQueue<Int>()
    if (queue.next() !== null)
        throw RuntimeException("next returned not-null for empty queue")
    queue.adjustPriority(3, 5.0)
    if (!queue.isEmpty())
        throw RuntimeException("isEmpty returned false for empty queue")
    println("Passed tests for empty MinPriorityQueue")
}

/**
 * Inserts elements of increasing value to the queue to maintain ordering; uses doubles
 * @throws RuntimeException if a test fails
 */
fun testFullQueue() {
    val queue = MinPriorityQueue<Double>()
    queue.addWithPriority(1.0, 0.0)
    queue.addWithPriority(2.0, 10.0)
    queue.addWithPriority(3.0, 2.0)
    queue.addWithPriority(4.0, 8.0)
    queue.addWithPriority(5.0, 4.0)
    if (queue.next() != 1.0)
        throw RuntimeException("next returned wrong value after adding values")
    if (queue.next() != 3.0)
        throw RuntimeException("next returned wrong second value after adding values")
    if (queue.next() != 5.0)
        throw RuntimeException("next returned wrong third value after adding values")
    if (queue.next() != 4.0)
        throw RuntimeException("next returned wrong fourth value after adding values")
    if (queue.next() != 2.0)
        throw RuntimeException("next returned wrong fifth value after adding values")
    if (queue.next() != null)
        throw RuntimeException("next returned non-null after emptying queue")
    println("Passed tests for inserting and retrieving elements from queue")
}

/**
 * Tests queue readjustment after updating values (greater and lesser values); uses chars
 * @throws RuntimeException if a test fails
 */
fun testUpdateQueue() {
    val queue = MinPriorityQueue<Char>()
    queue.addWithPriority('A', 0.0)
    queue.addWithPriority('B', 10.0)
    queue.addWithPriority('C', 2.0)
    queue.addWithPriority('D', 8.0)
    queue.addWithPriority('E', 4.0)

    queue.adjustPriority('A', 5.0)
    queue.adjustPriority('E', 100.0)
    if (queue.next() != 'C')
        throw RuntimeException("next returned wrong value after adding values")
    if (queue.next() != 'A')
        throw RuntimeException("next returned wrong second value after adding values")
    if (queue.next() != 'D')
        throw RuntimeException("next returned wrong third value after adding values")
    if (queue.next() != 'B')
        throw RuntimeException("next returned wrong fourth value after adding values")
    if (queue.next() != 'E')
        throw RuntimeException("next returned wrong fifth value after adding values")
    if (queue.next() != null)
        throw RuntimeException("next returned non-null after emptying queue")
    println("Passed tests for adjustPriority function")
}