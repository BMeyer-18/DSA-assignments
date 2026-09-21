package org.example.linked_data_structures

/**
 * A class for a queue to store items of type T
 *
 * @param T the type of data to be stored by the stack
 * @constructor Creates an empty queue
 */
class Queue<T> {
    private val queue = LinkedList<T>()

    /**
     * Add [data] to the end of the queue.
     */
    fun enqueue(data: T) {
        queue.pushBack(data)
    }

    /**
     * Remove the element at the front of the queue. If the queue is empty, it remains unchanged.
     * @return the value at the front of the queue or nil if none exists
     */
    fun dequeue(): T? {
        return queue.popFront()
    }

    /**
     * @return the value at the front of the queue or nil if none exists
     */
    fun peek(): T? {
        return queue.peekFront()
    }

    /**
     * @return true if the queue is empty and false otherwise
     */
    fun isEmpty(): Boolean {
        return queue.isEmpty()
    }
}