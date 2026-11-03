package org.example

import kotlin.math.pow
import java.io.File

/**
 * Runs unit tests on AssociativeArray class, runs Lempel-Ziv encoding and decoding,
 * tests compression ratio of Lempel-Ziv at various lengths and saves to file
 */
fun main() {
    // run unit tests
    testAssociativeArray()

    // test Lempel-Ziv on example I worked through in class
    val initialString = "BAABABBCABCBABC"
    val (bitString, table) = encodeLZ(initialString)
    val string = decodeLZ(bitString, table)
    println(bitString)
    println(string)
    print(initialString == string)

    // run Lempel-Ziv on strings of increasing length to get compression ratio
    val chars = 'A'..'Z'
    val compressions = DoubleArray(200)
    for (i in compressions.indices) {
        for (rep in 0..<10) {
            var string = ""
            for (char in 0..<i+1)
                string += chars.random().toString()
            compressions[i] += calculateCompressionRatio(string)
        }
        compressions[i] /= 10
    }

    // write Lempel-Ziv compression ratios to csv
    File("src/main/resources/compressionRatios.csv").printWriter().use { out ->
        out.println("size,ratio")
        for (idx in compressions.indices)
            out.println("${idx+1},${compressions[idx]}")
    }
}

/**
 * Runs Lempel-Ziv compression on a [string] and calculates the compression ratio,
 * assuming each character in the string is a byte and each byte in the compressed
 * string is composed of 8 binary digits.
 * @return compression ratio (original size / compressed size)
 */
fun calculateCompressionRatio(string: String): Double {
    val strBytes = string.length
    val (bitString, _) = encodeLZ(string)
    val compressedBytes = kotlin.math.ceil(bitString.length/8.0)
    return strBytes.toDouble() / compressedBytes
}