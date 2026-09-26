package org.example

import org.example.linked_data_structures.*

/**
 * ``Graph`` represents a directed graph
 * @param VertexType the type that represents a vertex in the graph
 * @constructor creates a graph with no vertices
 */
class Graph<VertexType> {
    /**
     * A data class to represent individual vertices in the graph
     * @param VertexType the type of data a vertex stores in the graph
     * @property data the data of type VertexType to be stored by the vertex
     * @property edges a map in which each connected vertex is mapped to the weight of its edge
     * @constructor creates a vertex with data and a map of edges of variable size
     */
    data class Vertex<VertexType>(val data: VertexType, val edges: MutableMap<VertexType, Double>)

    private val vertices = mutableSetOf<Vertex<VertexType>>()

    /**
     * Return the vertices in the graph
     */
    fun getVertices(): Set<VertexType> {
        val vertexSet: MutableSet<VertexType> = mutableSetOf()
        vertices.forEach {vertex -> vertexSet.add(vertex.data) }
        return vertexSet
    }

    /**
     * Add an edge between [from] and [to] with edge weight [cost]
     */
    fun addEdge(from: VertexType, to: VertexType, cost: Double) {
        val vertex1 = vertices.find {it.data == from}

        if (vertices.find {it.data == to} == null)
            vertices.add(Vertex(to, mutableMapOf()))

        if (vertex1 == null)
            vertices.add(Vertex(from, edges = mutableMapOf(to to cost)))
        else
            vertex1.edges[to] = cost
    }

    /**
     * Get all the edges that begin at [from]
     * @return a map where each key represents a vertex connected to [from]
     * and the value represents the edge weight
     */
    fun getEdges(from: VertexType): Map<VertexType, Double> {
        val vertex = vertices.find { it.data == from }
        return vertex?.edges ?: mapOf()
    }

    /**
     * Remove all edges and vertices from the graph
     */
    fun clear() {
        vertices.clear()
    }

    /**
     * Search through a graph using a breadth-first search
     * @param start the node to start the search
     * @param target the node to search for
     * @return the path from start to target (if one exists) and null otherwise
     */
    fun breadthFirstSearch(start: VertexType, target: VertexType): List<VertexType>? {
        val visited = mutableSetOf<VertexType>()
        val map = mutableMapOf<VertexType, VertexType>()
        val queue = Queue<VertexType>()
        queue.enqueue(start)
        visited.add(start)

        while(!queue.isEmpty()) {
            val vertex = queue.dequeue()!!
            if (vertex == target)
                break
            getEdges(vertex).forEach { (edge, weight) ->
                if (edge !in visited) {
                    queue.enqueue(edge)
                    visited.add(edge)
                    map[edge] = vertex
                }
            }
        }

        if (target !in visited)
            return null
        val path = mutableListOf(target)
        while (start !in path)
            path.add(map[path.last()]!!)
        return path.asReversed()
    }

    /**
     * Search through a graph using a depth-first search
     * @param start the node to start the search
     * @param target the node to search for
     * @return the path from start to target (if one exists) and null otherwise
     */
    fun depthFirstSearch(start: VertexType, target: VertexType): List<VertexType>? {
        val visited = mutableSetOf<VertexType>()
        val map = mutableMapOf<VertexType, VertexType>()
        val stack = Stack<VertexType>()
        stack.push(start)
        visited.add(start)

        while(!stack.isEmpty()) {
            val vertex = stack.pop()!!
            if (vertex == target)
                break
            getEdges(vertex).forEach { (edge, weight) ->
                if (edge !in visited) {
                    stack.push(edge)
                    visited.add(edge)
                    map[edge] = vertex
                }
            }
        }

        if (target !in visited)
            return null
        val path = mutableListOf(target)
        while (start !in path)
            path.add(map[path.last()]!!)
        return path.asReversed()
    }

    /**
     * Searches through a weighted graph using Dijkstra's Algorithm to find
     * the shortest path from the [start] vertex to the [target] vertex,
     * taking edge weighting into account.
     * @return the shortest path from the start to the target, as a list.
     */
    fun dijkstraSearch(start: VertexType, target: VertexType): List<VertexType>? {
        val prev = mutableMapOf<VertexType, VertexType?>()
        val dist = mutableMapOf<VertexType, Double>()
        val queue = MinPriorityQueue<VertexType>()
        getVertices().forEach{ vertex ->
            prev[vertex] = null
            dist[vertex] = Double.POSITIVE_INFINITY
            queue.addWithPriority(vertex, Double.POSITIVE_INFINITY)
        }

        dist[start] = 0.0
        queue.adjustPriority(start, 0.0)
        while (!queue.isEmpty()) {
            val vertex = queue.next()!!
            getEdges(vertex).forEach{ (edge, weight) ->
                val alt = dist[vertex]!! + weight
                if (alt < dist[edge]!!) {
                    dist[edge] = alt
                    queue.adjustPriority(edge, alt)
                    prev[edge] = vertex
                }
            }
        }

        if (prev[target] == null)
            return null
        val path = mutableListOf<VertexType>(target)
        while (start !in path)
            path.add(prev[path[path.size-1]]!!)
        return path.asReversed()
    }
}