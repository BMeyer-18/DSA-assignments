package org.example

/**
 * Runs unit tests on AssociativeArray class
 */
fun main() {
    testAssociativeArray()
    val (bitString, table) = encodeLZ("BAABABBCABCBABC")
    val string = decodeLZ(bitString, table)
    println(bitString)
    println(string)
}