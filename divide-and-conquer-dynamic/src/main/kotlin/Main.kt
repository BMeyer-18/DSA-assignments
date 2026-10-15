package org.example

import kotlin.time.measureTime
import kotlin.time.DurationUnit
import kotlin.math.pow
import kotlin.random.Random
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets
import com.opencsv.CSVWriter

/**
 * Main function runs unit tests and saves multiplication time data
 */
fun main() {
    testSquareMatrix()
    testAlignSequences()

    //writeRuntimeData("src/main/resources/runtimeData.csv")

    alignSampleGenomes()
}

/**
 * Runs global alignment algorithm on sample genomes, as described in the assignment
 */
fun alignSampleGenomes() {
    val genomeSnippet = "TGGCGACAACCGTAGCGGAATATTTTCGCGACCAGGGAAAACGGGTCGTGCTTTTTATCGATTCCATGACCCGTTATGCGCGTGCTTTGCGAGACGTGGCACTGGCGTCGGGAGAGCGTCCGGCTCGTCGAGGTTATCCCGCCTCCGTATTCGATAATTTGCCCCGCTTGCTGGAACGCCCAGGGGCGACCAGCGAGGGAAGCATTACTGCCTTTTATACGGTACTGCTGGAAAGCGAGGAAGAGGCGGACCCGATGGCGGATGAAATTCGCTCTATCCTTGACGGTCACCTGTATCTGAGCAGAAAGCTGGCCGGGCAGGGACATTACCCGGCAATCGATGTACTGAAAAGCGTAAGCCGCGTTTTT"
    val testAgainst = "TGGCCACCACGATAGCAGAATTTTTTCGCGATAATGGAAAGCGAGTCGTCTTGCTTGCCGACTCACTGACGCGTTATGCCAGGGCCGCACGGGAAATCGCTCTGGCCGCCGGAGAGACCGCGGTTTCTGGAGAATATCCGCCAGGCGTATTTAGTGCATTGCCACGACTTTTAGAACGTACGGGAATGGGAGAAAAAGGCAGTATTACCGCATTTTATACGGTACTGGTGGAAGGCGATGATATGAATGAGCCGTTGGCGGATGAAGTCCGTTCACTGCTTGATGGACATATTGTACTATCCCGACGGCTTGCAGAGAGGGGGCATTATCCTGCCATTGACGTGTTGGCAACGCTCAGCCGCGTTTTT"

    val (score, alignments) = alignGlobally(genomeSnippet, testAgainst)
    println(score)
    println(alignments)
}

/**
 * Writes runtime data to csv at [filepath]
 */
fun writeRuntimeData(filepath: String) {
    val runtimeData = mutableListOf<DoubleArray>()
    for (i in 0..11) {
        runtimeData.add(getRuntimes(2.0.pow(i).toInt()))
        println("Finished round ${i+1}")
    }

    val headers = arrayOf("Size","Normal","Strassen","Hybrid")

    FileOutputStream(filepath).use { fos ->
        OutputStreamWriter(fos, StandardCharsets.UTF_8).use { osw ->
            CSVWriter(osw).use { writer ->
                writer.writeNext(headers)
                for (i in 0..11) {
                    val row = DoubleArray(runtimeData[i].size+1) { 2.0.pow(i) }
                    for (j in runtimeData[i].indices)
                        row[j+1] = runtimeData[i][j]
                    writer.writeNext(row.map { it.toString() }.toTypedArray())
                }
            }
        }
    }
}

/**
 * Runs through the regular and Strassen multiplication algorithms for square matrices of a
 * particular [size] five times to get the average runtimes of each.
 * @return array of runtimes for both multiplication algorithms (regular than Strassen)
 */
fun getRuntimes(size: Int): DoubleArray {
    val runtimes = DoubleArray(3) { 0.0 }

    for (i in 0..<5) {
        val matA = SquareMatrix(size)
        val matB = SquareMatrix(size)
        for (r in 0..<size) {
            for (c in 0..<size) {
                matA[r, c] = Random.nextDouble(100000.0)
                matB[r, c] = Random.nextDouble(100000.0)
            }
        }
        runtimes[0] += measureTime { matA*matB }.toDouble(DurationUnit.SECONDS)
        runtimes[1] += measureTime { matA.strassenMultiply(matB) }.toDouble(DurationUnit.SECONDS)
        runtimes[2] += measureTime { matA.hybridMultiply(matB) }.toDouble(DurationUnit.SECONDS)
    }
    for (i in runtimes.indices)
        runtimes[i] /= 5.0

    return runtimes
}