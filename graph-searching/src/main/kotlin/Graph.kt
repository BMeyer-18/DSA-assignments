package org.example

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
}