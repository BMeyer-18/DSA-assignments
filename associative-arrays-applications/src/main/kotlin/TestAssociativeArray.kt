package org.example

/**
 * Runs all unit tests on AssociativeArray class
 * @throws RuntimeException if a test fails
 */
fun testAssociativeArray() {
    testAddValues()
    testUpdateValues()
    testGetValues()
    testContains()
    testRemoveValues()
    testRehash()
    println("Passed all tests for AssociativeArrays class\n")
}

/**
 * Checks behavior, size, and key/value pairs when a key/value pair is added
 * @throws RuntimeException if a test fails
 */
fun testAddValues() {
    val array = AssociativeArray<Int, Int>(5)
    val pairs = mutableListOf<Pair<Int, Int>>()
    for (i in 1..11) {
        pairs.add(Pair(i, i*2))
        array[i] = i*2
        if (array.size() != i)
            throw RuntimeException("Array size incorrect when adding item $i")
        if (!arePairsEquivalent(pairs, array.keyValuePairs()))
            throw RuntimeException("key/value pairs incorrect when adding item $i")
    }
    println("Passed tests for adding values to array")
}

/**
 * Checks behavior, size, and key/value pairs when key/value pairs are updated
 * @throws RuntimeException if a test fails
 */
fun testUpdateValues() {
    val array = AssociativeArray<Int, Int>(5)
    val pairs = mutableListOf<Pair<Int, Int>>()
    for (i in 1..11) {
        pairs.add(Pair(i, i*2))
        array[i] = i*2
    }
    for (i in 11 downTo 1) {
        pairs[i-1] = Pair(i, i*3)
        array[i] = i*3
        if (array.size() != 11)
            throw RuntimeException("Array size incorrect when updating item $i")
        if (!arePairsEquivalent(pairs, array.keyValuePairs()))
            throw RuntimeException("key/value pairs incorrect when updating item $i")
    }
    println("Passed tests for updating values in array")
}

/**
 * Checks that get function can successfully fetch inputted values and handle calls for elements
 * that do not exist
 * @throws RuntimeException if a test fails
 */
fun testGetValues() {
    val array = AssociativeArray<Int, Int>(5)
    for (i in 1..11)
        array[i] = i*2
    for (i in 11 downTo 1)
        if (array[i] != i*2)
            throw RuntimeException("Returned incorrect value for key $i")
    if (array[-2] != null)
        throw RuntimeException("Returned non-null value for key not in array")
    println("Passed tests for getting values from array")
}

/**
 * Checks that "in" calls can successfully determine if a key is present
 * @throws RuntimeException if a test fails
 */
fun testContains() {
    val array = AssociativeArray<Int, Int>(5)
    for (i in 1..11)
        array[i] = i*2
    for (i in 11 downTo 1)
        if (i !in array)
            throw RuntimeException("Failed to find value for key $i")
    if (-2 in array)
        throw RuntimeException("Returned true value for key not in array")
    println("Passed tests for contains function")
}

/**
 * Checks behavior, size, and key/value pairs when a value is removed
 * @throws RuntimeException if a test fails
 */
fun testRemoveValues() {
    val array = AssociativeArray<Int, Int>(5)
    val pairs = mutableListOf<Pair<Int, Int>>()
    for (i in 1..11) {
        pairs.add(Pair(i, i*2))
        array[i] = i*2
    }
    for (i in 11 downTo 1) {
        pairs.removeAt(i-1)
        array.remove(i)
        if (array.size() != i-1)
            throw RuntimeException("Array size incorrect when removing item $i")
        if (!arePairsEquivalent(pairs, array.keyValuePairs()))
            throw RuntimeException("key/value pairs incorrect when removing item $i")
    }
    println("Passed tests for removing values from array")
}

/**
 * Checks that all key/value pairs are carried over when the table is rehashed
 * @throws RuntimeException if a test fails
 */
fun testRehash() {
    val array = AssociativeArray<Int, Int>(5)
    val pairs = mutableListOf<Pair<Int, Int>>()
    for (i in 1..20) {
        pairs.add(Pair(i, i*2))
        array[i] = i*2
    }
    if (!arePairsEquivalent(pairs, array.keyValuePairs()))
        throw RuntimeException("key/value pairs incorrect after rehashing table")
    println("Passed test for rehashing table")
}

/**
 * @return true if lists of key-value pairs [a] and [b] contain the same items
 */
fun <K, V>arePairsEquivalent(a: List<Pair<K,V>>, b: List<Pair<K,V>>): Boolean {
    if (a.size != b.size)
        return false
    for (pair in a)
        if (pair !in b)
            return false
    for (pair in b)
        if (pair !in a)
            return false
    return true
}