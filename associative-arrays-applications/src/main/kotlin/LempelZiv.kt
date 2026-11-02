package org.example

import kotlin.math.ceil
import kotlin.math.log2

/**
 * Uses Lempel-Ziv encoding to compress a [string] of alphanumeric characters to a hash table.
 * @return pair containing the compressed bit string and a dictionary
 */
fun encodeLZ(string: String): Pair<String, AssociativeArray<String, Char>> {
    // initialize variables
    val codes = AssociativeArray<Char, String>()
    val reverseCodes = AssociativeArray<String, Char>()
    val sequences = mutableListOf<String>()
    val references = AssociativeArray<Int, Pair<Int, Char>>()
    val chars = string.toList().distinct()
    var bitString = ""

    // assign a binary code of the same length to each unique character
    val codeLength = ceil(log2(chars.size.toDouble())).toInt()
    for (i in chars.indices) {
        codes[chars[i]] = padBitString(Integer.toBinaryString(i), codeLength)
        reverseCodes[codes[chars[i]]!!] = chars[i]
    }

    // iterate through string, adding new sequences where they appear to table and bit string
    var currentString = ""
    for (i in string.indices) {
        currentString += string[i]
        if (currentString !in sequences) {
            val reference = if (currentString.length == 1) 0
                else sequences.indexOf(currentString.substring(0,currentString.length-1))+1
            sequences.add(currentString)
            references[sequences.size] = Pair(reference, string[i])
            bitString += padBitString(Integer.toBinaryString(reference), ceil(log2(sequences.size.toDouble())).toInt())
            bitString += codes[string[i]]
            currentString = ""
        }
    }

    // add to butString if a repeated substring is present in currentString
    bitString += padBitString(Integer.toBinaryString(sequences.indexOf(currentString)), ceil(log2(sequences.size.toDouble())).toInt())

    return Pair(bitString, reverseCodes)
}

/**
 * Uses Lempel-Ziv decoding to decompress a [bitString] based on a dictionary of binary [codes]
 * corresponding to different characters
 * @return decompressed string of characters
 */
fun decodeLZ(bitString: String, codes: AssociativeArray<String, Char>): String {
    // find number of bits for each character
    val charBits = ceil(log2(codes.size().toDouble())).toInt()

    // decode each individual sequence from the bit string
    val sequences = mutableListOf("", codes[bitString.substring(0,charBits)].toString())
    var remainingBitString = bitString.substring(charBits, bitString.length)
    while (remainingBitString.isNotEmpty()) {
        val refBits = ceil(log2(sequences.size.toDouble())).toInt()
        if (remainingBitString.length < refBits + charBits)
            break
        val prevSeq = sequences[remainingBitString.substring(0, refBits).toInt(2)]
        val newChar = codes[remainingBitString.substring(refBits, refBits + charBits)]
        sequences.add(prevSeq + newChar)
        remainingBitString = remainingBitString.substring(refBits + charBits, remainingBitString.length)
    }

    // use sequences to recreate original string
    return sequences.joinToString("") + (codes[remainingBitString] ?: "")
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