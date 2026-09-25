package org.example

import kotlin.math.floor

/**
 * ``MinHeap`` represents a heap where the root node has the
 * lowest value, child nodes have greater values. Elements can
 * be added anywhere but only removed from the top of the heap.
 * @param T the type of item stored in the heap
 * @constructor creates an empty heap with no nodes
 */
class MinHeap<T> {
    /**
     * A data class containing both a node's data and its priority value
     * @param T the type of item stored in a node
     * @property data the data stored in a node
     * @property value the priority value of the node
     * @constructor creates a node with data of type T and a priority value
     */
    data class Node<T>(val data: T, val value: Double)

    private val heap = mutableListOf<Node<T>>()

    /**
     * @return the data in the root node (element with the smallest value)
     */
    fun getRoot(): T? {
        if (heap.isEmpty())
            return null
        return heap[0].data
    }

    /**
     * Adds a new element to the heap storing data [data] and with the
     * priority value [value]
     */
    fun insert(data: T, value: Double) {
        heap.add(Node(data, value))
        siftUp(heap.size-1)
    }

    /**
     * Removes and returns the root node (element with the smallest value)
     * @return the data in the root node
     */
    fun popRoot(): T? {
        if (heap.isEmpty())
            return null
        val data = heap[0].data
        heap[0] = heap[heap.size-1]
        heap.removeAt(heap.size-1)
        val newIdx = siftDown(0)
        return data
    }

    /**
     * Updates the value for an element in the heap with data [data]
     * and a new priority value [newValue]
     */
    fun updateValue(data: T, newValue: Double) {
        val idx = heap.indexOf(heap.find { it.data == data })
        if (idx == -1)
            return
        else if (newValue < heap[idx].value) {
            heap[idx] = Node(data, newValue)
            siftUp(idx)
        } else if (newValue > heap[idx].value) {
            heap[idx] = Node(data, newValue)
            siftDown(idx)
        }
    }

    /**
     * @return the number of elements in the heap
     */
    fun getSize(): Int {
        return heap.size
    }

    /**
     * @return true if the heap has no elements, false otherwise
     */
    fun isEmpty(): Boolean {
        return heap.isEmpty()
    }

    /**
     * Reorganizes the list from the index [nodeIdx] up to maintain heap ordering
     * @return the final index at which the node landed
     */
    private fun siftUp(nodeIdx: Int): Int {
        var idx = nodeIdx
        while (idx > 0) {
            val parentIdx = floor((idx-1)/2.0).toInt()
            // if the node's value is smaller than that of its parent,
            // swap them. Otherwise, break the loop.
            if(heap[idx].value < heap[parentIdx].value) {
                val temp = heap[idx]
                heap[idx] = heap[parentIdx]
                heap[parentIdx] = temp
                idx = parentIdx
            } else break
        }
        return idx
    }

    /**
     * Reorganizes the list from the index [nodeIdx] down to maintain heap ordering
     * @return the final index at which the node landed
     */
    private fun siftDown(nodeIdx: Int): Int {
        var idx = nodeIdx
        // only goes while the element has at least one child element
        while (idx <= (heap.size-2)/2.0) {
            val childrenIdx: List<Int> = when {
                2*idx+2 >= heap.size -> listOf(2*idx+1)
                heap[2*idx+1].value <= heap[2*idx+2].value -> listOf(2*idx+1, 2*idx+2)
                else -> listOf(2*idx+2, 2*idx+1)
            }
            // if the node's value is larger than that of its smallest child,
            // swap them. Else, if its value is larger than that of its largest
            // child, swap them. Otherwise, break the loop
            if (heap[idx].value > heap[childrenIdx[0]].value) {
                val temp = heap[idx]
                heap[idx] = heap[childrenIdx[0]]
                heap[childrenIdx[0]] = temp
                idx = childrenIdx[0]
            }  else if (childrenIdx.size > 1 && heap[idx].value > heap[childrenIdx[1]].value) {
                val temp = heap[idx]
                heap[idx] = heap[childrenIdx[1]]
                heap[childrenIdx[1]] = temp
                idx = childrenIdx[1]
            } else break
        }
        return idx
    }

    /**
     * @return a list of the priority values of heap items in order.
     * For debugging.
     */
    fun getValues(): List<Double> {
        return heap.map { it.value }
    }
}