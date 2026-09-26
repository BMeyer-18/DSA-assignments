package org.example

import java.io.File
import kotlin.math.ceil
import kotlin.math.log
import kotlin.math.max

/**
 * Runs unit tests for all graph classes;
 * solution to Project Euler problem 81
 */
fun main() {
    testGraph()
    testMinHeap()
    testMinPriorityQueue()

    val path = pathSum("src/main/resources/euler_matrix.txt")
    println(path?.size.toString() + ": " + path.toString())
}

/**
 * Solution to project Euler problem 81.
 * Finds minimal sum path in a matrix when movement can only be right or down,
 * from the top left to the bottom right. Matrix data is taken from [filepath] to
 * a txt file containing the data.
 * @return the shortest weighted path from top left to the bottom right as a list.
 */
fun pathSum(filepath: String): List<String>? {
    val numMatrix = mutableListOf<List<Int>>()
    File(filepath).forEachLine { line ->
        val strList = line.split(',')
        val numList = strList.map { it.toInt() }
        numMatrix.add(numList)
    }
    val numDigits = ceil(log(max(numMatrix.size, numMatrix[numMatrix.size-1].size).toDouble(), 10.0)).toInt()

    val graph = Graph<String>()
    for (row in 0..<numMatrix.size-1) {
        for (col in 0..<numMatrix[row].size-1) {
            graph.addEdge(formatGridItem(row,col,numDigits), formatGridItem(row,col+1,numDigits), numMatrix[row][col+1].toDouble())
            graph.addEdge(formatGridItem(row,col,numDigits), formatGridItem(row+1,col,numDigits), numMatrix[row+1][col].toDouble())
        }
    }
    for (row in 0..<numMatrix.size-1)
        graph.addEdge(formatGridItem(row,numMatrix[row].size-1,numDigits), formatGridItem(row+1,numMatrix[row].size-1,numDigits), numMatrix[row+1][numMatrix[row].size-1].toDouble())
    for (col in 0..<numMatrix[numMatrix.size-1].size-1)
        graph.addEdge(formatGridItem(numMatrix.size-1,col,numDigits), formatGridItem(numMatrix.size-1,col+1,numDigits), numMatrix[numMatrix.size-1][col+1].toDouble())

    return graph.dijkstraSearch(formatGridItem(0,0,numDigits), formatGridItem(numMatrix.size-1,numMatrix[numMatrix.size-1].size-1,numDigits))
}

/**
 * Helper function for giving unique names to each value in a grid.
 * Formats each string as the [row] number followed by the [col] number,
 * each of which is padded with 0s as necessary to match [numDigits].
 * @return a string formatted to contain a unique name
 */
fun formatGridItem(row: Int, col: Int, numDigits: Int): String {
    val formatStr = "%0" + numDigits + "d"
    var name = ""
    name += formatStr.format(row)
    name += formatStr.format(col)
    return name
}