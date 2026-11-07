package org.example

import kotlin.math.pow

/**
 *
 */
data class Node(val median: Double?, var left: Node? = null, var right: Node? = null, val value: DoubleArray? = null)

/**
 *
 */
fun buildKDTree(data: Array<DoubleArray>, dim: Int = 0): Node {
    // base case: if the list has just one element, return it as a leaf
    if (data.size == 1)
        return Node(null, value=data[0])

    // find the median along specified dimension; create root node
    val median = quickselect(data, dim)
    val root = Node(median, null, null)

    // continue iterating through dims, or reset to 0
    val nextDim = if(dim == data[0].size-1) 0
        else dim + 1

    // add to left subtree
    val leftData = data.filter { it[dim] < median }.toTypedArray()
    root.left = buildKDTree(leftData, nextDim)

    // add to right subtree
    val rightData = data.filter { it[dim] >= median }.toTypedArray()
    root.right = buildKDTree(rightData, nextDim)

    // return tree, or subtree
    return root
}

/**
 *
 */
fun searchKDTree(tree: Node, query: DoubleArray, dim: Int = 0): DoubleArray {
    // if the node is a leaf, return its value
    if (tree.median == null)
        return tree.value!!

    // continue iterating through dims, or reset to 0
    val nextDim = if(dim == query.size-1) 0
        else dim + 1

    // recurse through subtrees until reaching a leaf
    val leaf = if (query[dim] < tree.median) searchKDTree(tree.left!!, query, nextDim)
        else searchKDTree(tree.right!!, query, nextDim)

    // if query is closer to median than point, check alternate branch, and return resulting leaf if it's closer
    if (kotlin.math.abs(query[dim] - tree.median) < calculateDist(query, leaf)) {
        val altLeaf = if (query[dim] < tree.median) searchKDTree(tree.right!!, query, nextDim)
            else searchKDTree(tree.left!!, query, nextDim)
        return if (calculateDist(query, leaf) <= calculateDist(query, altLeaf)) leaf
            else altLeaf
    }

    // if the located leaf is closer than the median, return it
    return leaf
}

/**
 *
 */
fun searchBruteForce(data: Array<DoubleArray>, query: DoubleArray): DoubleArray {
    // set initial closest point to first point
    var closestPoint = data[0]

    // iteratively check distance to every data point, keeping track of closest
    for (i in 1..<data.size)
        if (calculateDist(query, data[i]) < calculateDist(query, closestPoint))
            closestPoint = data[i]

    // return closest point
    return closestPoint
}

/**
 *
 */
fun calculateDist(point1: DoubleArray, point2: DoubleArray): Double {
    if (point1.size != point2.size)
        throw IllegalArgumentException("Points do not have same number of coordinates")
    var sqSum = 0.0
    for (i in point1.indices)
        sqSum += (point1[i] - point2[i]).pow(2)
    return kotlin.math.sqrt(sqSum)
}