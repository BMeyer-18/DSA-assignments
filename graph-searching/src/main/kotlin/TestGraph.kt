package org.example

/**
 * Runs all tests for the Graph class
 * @throws RuntimeException if a test fails
 */
fun testGraph() {
    testEmptyGraph()
    testAddingVertices()
    testAddingEdges()
    testClearMap()
    println("Passed all tests for Graph class\n")
}

/**
 * Check functionality for a graph with no vertices; uses ints
 * @throws RuntimeException if a test fails
 */
fun testEmptyGraph() {
    val graph = Graph<Int>()

    if (graph.getVertices().isNotEmpty())
        throw RuntimeException("Empty graph returned set with >= 1 vertex")
    if (graph.getEdges(3).isNotEmpty())
        throw RuntimeException("Nonexistent vertex returned with edges")

    println("Passed tests for empty graph")
}

/**
 * Check ability to add and get vertices; uses strings
 * @throws RuntimeException if a test fails
 */
fun testAddingVertices() {
    val graph = Graph<String>()
    graph.addEdge("One", "Two", 1.0)
    graph.addEdge("Two", "Three", 2.0)
    graph.addEdge("Three", "One", 3.0)

    if(graph.getVertices() != setOf("One", "Two", "Three"))
        throw RuntimeException("getVertices returned the wrong string vertices")
    println("Passed tests for graph with multiple vertices")
}

/**
 * Check ability to add and get edges to multiple vertices; uses doubles
 * @throws RuntimeException if a test fails
 */
fun testAddingEdges() {
    val graph = Graph<Double>()
    graph.addEdge(1.0, 1.5, 0.5)
    graph.addEdge(1.0, 2.5, 1.5)
    graph.addEdge(1.0, 3.5, 2.5)
    graph.addEdge(100.5, 112.5, 12.0)
    graph.addEdge(100.5, 115.5, 15.0)
    graph.addEdge(100.5, 120.5, 20.0)

    if(graph.getVertices() != setOf(1.0, 1.5, 2.5, 3.5, 100.5, 112.5, 115.5, 120.5))
        throw RuntimeException("getVertices returned the wrong double vertices")
    if(graph.getEdges(1.0) != mapOf(1.5 to 0.5, 2.5 to 1.5, 3.5 to 2.5))
        throw RuntimeException("getEdges returned the wrong edges for first vertex")
    if(graph.getEdges(100.5) != mapOf(112.5 to 12.0, 115.5 to 15.0, 120.5 to 20.0))
        throw RuntimeException("getEdges returned the wrong edges for second vertex")
    if(graph.getEdges(1.5) != mapOf<Double, Double>())
        throw RuntimeException("getEdges returned the wrong edges for vertex with no edges")
    println("Passed tests for graph with multiple vertices and edges")
}

/**
 * Check ability to clear a filled map; uses chars
 * @throws RuntimeException if a test fails
 */
fun testClearMap() {
    val graph = Graph<Char>()
    graph.addEdge('A', 'B', 1.0)
    graph.addEdge('A', 'C', 2.0)
    graph.addEdge('B', 'D', 3.0)
    graph.addEdge('B', 'E', 4.0)
    graph.addEdge('C', 'F', 5.0)
    graph.addEdge('C', 'G', 6.0)

    graph.clear()

    if (graph.getVertices().isNotEmpty())
        throw RuntimeException("Cleared graph returned set with >= 1 vertex")
    if (graph.getEdges('A').isNotEmpty())
        throw RuntimeException("Cleared vertex returned with edges")

    println("Passed all tests for clearing a filled graph")
}