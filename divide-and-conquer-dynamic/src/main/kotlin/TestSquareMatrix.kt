package org.example

import kotlin.math.pow
import kotlin.math.abs
import kotlin.random.Random

/**
 * Runs all unit tests for Square Matrix class
 * @throws RuntimeException if a test fails
 */
fun testSquareMatrix() {
    testAddition()
    testSubtraction()
    testMultiplication()
    testQuartering()
    println("Passed all tests for SquareMatrix class\n")
}

/**
 * Tests that matrices of various sizes can be added together correctly
 * @throws RuntimeException if a test fails
 */
fun testAddition() {
    val sizes = (0..10).map { (2.0.pow(it)).toInt() }
    for (size in sizes) {
        val matA = SquareMatrix(size)
        val matB = SquareMatrix(size)
        val expectedProduct = SquareMatrix(size)

        for (r in 0..<size) {
            for (c in 0..<size) {
                val a = Random.nextDouble(1000.0)
                val b = Random.nextDouble(1000.0)
                matA[r, c] = a
                matB[r, c] = b
                expectedProduct[r, c] = a+b
            }
        }

        if (matA+matB != expectedProduct)
            throw RuntimeException("Addition at size $size failed")
    }
    println("Passed test for SquareMatrix addition")
}

/**
 * Tests that matrices of various sizes can be subtracted from one another correctly
 * @throws RuntimeException if a test fails
 */
fun testSubtraction() {
    val sizes = (0..10).map { (2.0.pow(it)).toInt() }
    for (size in sizes) {
        val matA = SquareMatrix(size)
        val matB = SquareMatrix(size)
        val expectedProduct = SquareMatrix(size)

        for (r in 0..<size) {
            for (c in 0..<size) {
                val a = Random.nextDouble(1000.0)
                val b = Random.nextDouble(1000.0)
                matA[r, c] = a
                matB[r, c] = b
                expectedProduct[r, c] = a-b
            }
        }

        if (matA-matB != expectedProduct)
            throw RuntimeException("Subtraction at size $size failed")
    }
    println("Passed test for SquareMatrix subtraction")
}

/**
 * Tests that matrices of various 2^n sizes can be multiplied against one another correctly,
 * and that Strassen multiplication returns the same result as regular multiplication.
 * May take some time to run.
 * @throws RuntimeException if a test fails
 */
fun testMultiplication() {
    val sizes = (0..10).map { (2.0.pow(it)).toInt() }
    for (size in sizes) {
        val matA = SquareMatrix(size)
        val matB = SquareMatrix(size)

        for (r in 0..<size) {
            for (c in 0..<size) {
                val a = Random.nextDouble(1000.0)
                val b = Random.nextDouble(1000.0)
                matA[r, c] = a
                matB[r, c] = b
            }
        }

        if (!compareMatrices(matA*matB, matA.strassenMultiply(matB)) || !compareMatrices(matB*matA, matB.strassenMultiply(matA)))
            throw RuntimeException("Multiplication at size $size failed")
    }
    println("Passed test for SquareMatrix multiplication")
}

/**
 * Tests that matrices of various even sizes can be split into quarters correctly
 * @throws RuntimeException if a test fails
 */
fun testQuartering() {
    val sizes = (0..10).map { (2.0.pow(it)).toInt() }
    for (size in sizes) {
        val mat = SquareMatrix(size)
        val quarters = mat.getQuarters()
        for (r in 0..<size/2) {
            for (c in 0..<size/2) {
                if (quarters[0][r, c] != mat[r, c+size/2] ||
                    quarters[1][r, c] != mat[r, c] ||
                    quarters[2][r, c] != mat[r+size/2, c] ||
                    quarters[3][r, c] != mat[r+size/2, c+size/2])
                    throw RuntimeException("Quartering at size $size failed")
            }
        }
    }
    println("Passed test for SquareMatrix quartering")
}

/**
 * @return true if all matrix values are within [threshold] of one another, false otherwise
 */
private fun compareMatrices(matA: SquareMatrix, matB: SquareMatrix, threshold: Double = 0.001): Boolean {
    val difference = matA - matB
    for (r in 0..<matA.size) {
        for (c in 0..<matB.size) {
            if (abs(difference[r, c]) > threshold)
                return false
        }
    }
    return true
}