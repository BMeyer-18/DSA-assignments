package org.sorting_algorithms

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.log

/**
 * Sorts items in [list] by organizing them into groups by digit, starting
 * with the ones place and increasing to the highest-order digit of the
 * largest number.
 * @return list of original items, sorted in ascending order
 */
fun radixSort(list: List<Int>): List<Int> {
    // already in order if list has 0 or 1 items
    if (list.size < 2)
        return list

    // initialize lists / constant variables
    val radixList = mutableListOf<MutableList<Int>>()
    for (i in 0..<10)
        radixList.add(mutableListOf())
    val sortedList = list.toMutableList()
    val maxDigits = ceil(log(list.max().toDouble(), 10.0)).toInt()

    // run one iteration per digit in the largest number
    for (order in 0..<maxDigits) {
        // adding elements to radixList
        while(sortedList.isNotEmpty()) {
            val digit = floor(sortedList[0]/10.0.pow(order.toDouble()))%10
            radixList[digit.toInt()].add(sortedList[0])
            sortedList.removeFirst()
        }

        // take elements out of radixList in order; clearing radixList
        for (digitList in radixList) {
            while (digitList.isNotEmpty()) {
                sortedList.add(digitList[0])
                digitList.removeFirst()
            }
        }
    }

    return sortedList
}