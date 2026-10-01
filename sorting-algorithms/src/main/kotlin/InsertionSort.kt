package org.sorting_algorithms

/**
 * Sorts items in [list] by swapping elements from right to left
 * until all elements are in place.
 * @return list of original items, sorted in ascending order
 */
fun insertionSort(list: List<Double>): List<Double> {
    // if list has 0 or 1 elements, it's already sorted
    if (list.size < 2)
        return list

    val sortedList = list.toMutableList()
    // iterate through every element in the list, excluding the first
    for (i in 1..<sortedList.size) {
        // swap down to the first element, if necessary
        for (j in i downTo 1) {
            // break if element is already sorted
            if (sortedList[j] > sortedList[j-1])
                break
            // swap if element is not yet sorted
            val temp = sortedList[j]
            sortedList[j] = sortedList[j-1]
            sortedList[j-1] = temp
        }
    }
    return sortedList
}