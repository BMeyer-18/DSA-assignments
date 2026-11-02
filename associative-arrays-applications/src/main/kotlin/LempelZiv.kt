package org.example

import kotlin.math.ceil
import kotlin.math.log2

/**
 * Uses Lempel-Ziv encoding to compress a [string] of alphanumeric characters to a hash table.
 * @return pair containing the compressed bit string and a dictionary
 */
fun encodeLZ(string: String): Pair<String, AssociativeArray<Int, Pair<Int, Char>>> {
    // initialize variables
    val codes = mutableMapOf<Char, String>()
    val sequences = mutableListOf<String>()
    val references = AssociativeArray<Int, Pair<Int, Char>>()
    val chars = string.toList().distinct()
    var bitString = ""

    // assign a binary code of the same length to each unique character
    val codeLength = ceil(log2(chars.size.toDouble())).toInt()
    for (i in chars.indices)
        codes[chars[i]] = padBitString(Integer.toBinaryString(i), codeLength)

    // iterate through string, adding new sequences where they appear to table and bit string
    var currentString = ""
    for (i in string.indices) {
        currentString += string[i]
        if (currentString !in sequences) {
            val reference = if (currentString.length == 1) 0
                else sequences.indexOf(currentString.substring(0,currentString.length-1))+1
            sequences.add(currentString)
            references[sequences.size] = Pair(reference, string[i])
            println("_${codes[string[i]]}")
            bitString += padBitString(Integer.toBinaryString(reference), ceil(log2(sequences.size.toDouble())).toInt())
            bitString += codes[string[i]]
            currentString = ""
        }
    }

    // add to butString if a repeated substring is present in currentString
    bitString += padBitString(Integer.toBinaryString(sequences.indexOf(currentString)), ceil(log2(sequences.size.toDouble())).toInt())

    return Pair(bitString, references)
}

/**
 * Uses Lempel-Ziv decoding to decompress a [bitString] based on a table of [references]
 * @return decompressed string of characters
 */
fun decodeLZ(bitString: String, references: AssociativeArray<Int, Pair<Int, Char>>): String {
    return ""
}

/**
 * @return a bit string of the same value as [string], padded with leading 0s to get to length [length]
 */
fun padBitString(string: String, length: Int): String {
    if (string.length >= length)
        return string.substring(0, length)
    var paddedString = string
    while (paddedString.length < length)
        paddedString = "0$paddedString"
    return paddedString
}