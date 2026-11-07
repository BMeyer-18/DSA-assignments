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
fun runExperiment(k: Int, numPoints: Int = 1000): Triple<Duration, Duration, Duration> {
    // generate random data
    val data = Array(numPoints) { DoubleArray(k) }
    for (i in data.indices)
        for (j in data[i].indices)
            data[i][j] = Random.nextDouble(-1000.0, 1000.0)

    // generate random query point
    val query = DoubleArray(k)
    for (i in query.indices)
        query[i] = Random.nextDouble(-1000.0, 1000.0)

    // initialize tree variable outside measureTime
    val tree: Node

    // measure durations for each task
    val treeBuildDur = measureTime { tree = buildKDTree(data) }
    val treeSearchDur = measureTime { searchKDTree(tree, query) }
    val bfSearchDur = measureTime { searchBruteForce(data, query) }

    // return triple of durations
    return Triple(treeBuildDur, treeSearchDur, bfSearchDur)
}