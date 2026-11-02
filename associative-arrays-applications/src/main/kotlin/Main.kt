package org.example

/**
 * Runs unit tests on AssociativeArray class
 */
fun main() {
    testAssociativeArray()
    val compressedData = encodeLZ("BAABABBCABCBABC")
    println(compressedData)
}