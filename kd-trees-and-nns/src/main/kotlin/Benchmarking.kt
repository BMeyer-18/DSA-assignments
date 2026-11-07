package org.example

import kotlin.random.Random
import kotlin.time.measureTime
import kotlin.time.Duration

/**
 * Benchmark [searchKDTree] against brute force nearest neighbor.
 * 1000 test points will be generated to test against the training
 * points.
 *
 * @param k: the dimensionality of the dataset to create
 * @param numPoints: the number of points to use to match against
 *   (1000 test points will be used)
 * @return the triple of [Duration] objects where the first specifies
 * the time to build the tree, the second specifies the time it takes
 * to query the tree with 1,000 points, and the third is the time it
 * takes to find the nearest neighbor to these points using the brute
 * force approach.
 */
fun runExperiment(k: Int, numPoints: Int): Triple<Duration, Duration, Duration> {
    // generate random data
    val data = Array(numPoints) { DoubleArray(k) }
    for (i in data.indices)
        for (j in data[i].indices)
            data[i][j] = Random.nextDouble(-1000.0, 1000.0)

    // generate random query points
    val queryPoints = Array(1000) { DoubleArray(k) }
    for (i in queryPoints.indices)
        for (j in queryPoints[i].indices)
            queryPoints[i][j] = Random.nextDouble(-1000.0, 1000.0)

    // initialize tree variable outside measureTime
    val tree: Node

    // measure durations of building tree
    val treeBuildDur = measureTime { tree = buildKDTree(data) }

    // measure duration of searching for 1000 query points with the KD tree
    val treeSearchDur = measureTime {
        for (query in queryPoints)
            searchKDTree(tree, query)
    }

    // measure duration of searching for 1000 query points with brute force search
    val bfSearchDur = measureTime {
        for (query in queryPoints)
            searchBruteForce(data, query)
    }

    // return triple of durations
    return Triple(treeBuildDur, treeSearchDur, bfSearchDur)
}