package org.example

/**
 * ``MinPriorityQueue`` maintains a priority queue where the lower
 *  the priority value, the sooner the element will be removed from
 *  the queue.
 *  @param T the representation of the items in the queue
 */
class MinPriorityQueue<T> {
    private val minHeap = MinHeap<T>()

    /**
     * @return true if the queue is empty, false otherwise
     */
    fun isEmpty(): Boolean {
        return minHeap.isEmpty()
    }

    /**
     * Add [elem] with level [priority]
     */
    fun addWithPriority(elem: T, priority: Double) {
        minHeap.insert(elem, priority)
    }

    /**
     * Get the next (highest priority) element and remove this element from the queue.
     * @return the next element in terms of priority. If empty, return null.
     */
    fun next(): T? {
        return minHeap.popRoot()
    }

    /**
     * Adjust the priority of the given element
     * @param elem whose priority should change
     * @param newPriority the priority to use for the element
     *   the lower the priority the earlier the element int
     *   the order.
     */
    fun adjustPriority(elem: T, newPriority: Double) {
        minHeap.updateValue(elem, newPriority)
    }
}