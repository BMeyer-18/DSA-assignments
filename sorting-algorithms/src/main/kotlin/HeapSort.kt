package org.sorting_algorithms

import org.sorting_algorithms.data_structures.MinHeap

/**
 * Sorts items in [list] by restructuring them into a min heap and
 * pulling them out one by one, adding them to a new list in ascending order.
 * @return list of original items, sorted in ascending order
 */
fun heapSort(list: List<Double>): List<Double> {
    // already in order if list has 0 or 1 items
    if (list.size < 2)
        return list

    // initialize data structures
    val heap = MinHeap<Int>()
    val sortedList = mutableListOf<Double>()

    // reorganize elements to heap
    for (i in 1..<list.size)
        heap.insert(i, list[i])

    // remove elements from heap
    while (!heap.isEmpty())
        sortedList.add(list[heap.popRoot()!!])

    return sortedList
}