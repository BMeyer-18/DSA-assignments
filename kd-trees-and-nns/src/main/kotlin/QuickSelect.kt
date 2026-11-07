package org.example

import kotlin.random.Random

/**
 * @return the [k]th smallest element of dimension [dim] between indices
 * [left] and [right] of a [list] of points
 */
fun quickselect(list: Array<DoubleArray>, dim: Int, left: Int=0, right: Int=list.size-1, k: Int=list.size/2): Double {
    // if there is only one element in the range, return it
    if (left == right)
        return list[left][dim]

    // partition list
    val idx = partition(list, dim, left=left, right=right)

    // if item is in queried position, return item
    if (idx == k)
        return list[idx][dim]

    // recur through portion of partitioned list with queried index
    if (k < idx)
        return quickselect(list, dim, left=left, right=idx-1, k=k)
    return quickselect(list, dim, left=idx+1, right=right, k=k)
}

/**
 * @return a pair of the list with the pivot partitioning the [list] of points between the [left]
 * and [right] indices around the [pivot] in dimension [dim], and the final index of the pivot
 */
fun partition(list: Array<DoubleArray>, dim: Int, left: Int=0, right: Int=list.size-1, pivot: Int=Random.nextInt(right-left+1)+left): Int {
    val pivotVal = list[pivot]

    // swap pivot with last element
    list[pivot] = list[right]
    list[right] = pivotVal

    // swap elements based on relation to pivot
    var idx = left
    for (i in left..<right) {
        if (list[i][dim] <= pivotVal[dim]) {
            // if element <= pivot, swap it to the left
            val temp = list[i]
            list[i] = list[idx]
            list[idx] = temp
            idx++
        }
    }

    // swap pivot into final position
    list[right] = list[idx]
    list[idx] = pivotVal

    return idx
}