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

    writeRuntimeData("src/main/resources/runtimeData.csv")
}

/**
 * Writes runtime data to csv at [filepath]
 */
fun writeRuntimeData(filepath: String) {
    val runtimeData = mutableListOf<List<Double>>()
    for (i in 0..20) {
        runtimeData.add(getRuntimes(2.0.pow(i).toInt()))
        println("Finished round ${i+1}")
    }

    val headers = arrayOf("Size","Normal","Strassen")

    FileOutputStream(filepath).use { fos ->
        OutputStreamWriter(fos, StandardCharsets.UTF_8).use { osw ->
            CSVWriter(osw).use { writer ->
                writer.writeNext(headers)
                for (i in 0..20) {
                    val row = (listOf(2.0.pow(i)) + runtimeData[i]).toDoubleArray().map { it.toString() }
                    writer.writeNext(row.toTypedArray())
                }
            }
        }
    }
}

/**
 * Runs through the regular and Strassen multiplication algorithms for square matrices of a
 * particular [size] five times to get the average runtimes of each.
 * @return list of runtimes for both multiplication algorithms (regular than Strassen)
 */
fun getRuntimes(size: Int): List<Double> {
    val runtimeList = mutableListOf<Double>()

    // get average runtime for regular matrix multiplication
    var runtimeSum = 0.0
    for (i in 0..<5) {
        val matA = SquareMatrix(size)
        val matB = SquareMatrix(size)
        for (r in 0..<size) {
            for (c in 0..<size) {
                matA[r, c] = Random.nextDouble(100000.0)
                matB[r, c] = Random.nextDouble(100000.0)
            }
        }
        val runtime = measureTime { matA*matB }
        runtimeSum += runtime.toDouble(DurationUnit.SECONDS)
    }
    runtimeList.add(runtimeSum/5.0)

    // get average runtime for the Strassen multiplication algorithm
    runtimeSum = 0.0
    for (i in 0..<5) {
        val matA = SquareMatrix(size)
        val matB = SquareMatrix(size)
        for (r in 0..<size) {
            for (c in 0..<size) {
                matA[r, c] = Random.nextDouble(100000.0)
                matB[r, c] = Random.nextDouble(100000.0)
            }
        }
        val runtime = measureTime { matA.strassenMultiply(matB) }
        runtimeSum += runtime.toDouble(DurationUnit.SECONDS)
    }
    runtimeList.add(runtimeSum/5.0)

    return runtimeList
}