package org.sorting_algorithms

/**
 * Sorts items in [list] by recursively splitting the list in two
 * roughly equal-sized halves, then re-combining them in order.
 * @return list of original items, sorted in ascending order
 */
fun mergeSort(list: List<Double>): List<Double> {
    // base case, can't split the list any further
    if (list.size <= 1)
        return list
    // splits list in 2, recurs and eventually merges lists together
    val listA = list.subList(0, list.size/2)
    val listB = list.subList(list.size/2, list.size)
    return merge(mergeSort(listA), mergeSort(listB))
}

/**
 * Takes two separate already-sorted lists and combines them
 * into one larger sorted list, with all items from both
 * [listA] and [listB] in order.
 * @return list of items from both lists in ascending order
 */
fun merge(listA: List<Double>, listB: List<Double>): List<Double> {
    // making lists mutable; initializing return list
    val mListA = listA.toMutableList()
    val mListB = listB.toMutableList()
    val mergedList = mutableListOf<Double>()
    // merges lists, making comparisons when neither is empty
    while (mListA.isNotEmpty() && mListB.isNotEmpty()) {
        if (mListA[0] <= mListB[0]) {
            mergedList.add(mListA[0])
            mListA.removeFirst()
        } else {
            mergedList.add(mListB[0])
            mListB.removeFirst()
        }
    }

    // when one list is empty, append the rest of the not-empty list
    mergedList += mListA.ifEmpty { mListB }
    return mergedList
}