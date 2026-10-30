package org.example

/**
 * Represents a table containing key/value pairs, grouped into buckets through hashing.
 * @param K the type of the keys
 * @param V the type of the values
 * @property size the number of buckets in the hash table; defaults to 53
 * @constructor creates an empty hash table with [size] buckets all mapped to null
 */
class HashTable<K, V>(val size: Int = 53) {
    /**
     * A data class to represent an individual node in a linked list.
     * @param K the type of the key stored in the node
     * @param V the type of the value stored in the node
     * @property k the key to be stored in the node
     * @property v the value to be stored in the node
     * @property next the next node in the linked list. Null if none.
     * @constructor Creates a node with a key, a value, and a reference to the next element.
     */
    data class Node<K, V>(var k: K, var v: V, var next: Node<K, V>? = null)

    val table = Array<Node<K,V>?>(size) { null }

    /**
     * Adds node with key [k] and value [v] to hash table.
     */
    operator fun set(k: K, v: V) {
        val node = Node(k,v)
        val hash = getHash(k)
        if (table[hash] == null)
            // if there is no value, add it in
            table[hash] = node
        else if(table[hash]!!.k == k) {
            // if the value's key is the same as the one being added, replace it
            table[hash]!!.v = v
        } else {
            // otherwise, keep going until the key is the same or there are no more
            var nextNode = table[hash]!!
            while (nextNode.next != null) {
                if (nextNode.next!!.k == k) {
                    nextNode.next!!.v = v
                    return
                }
                nextNode = nextNode.next!!
            }
            nextNode.next = node
        }
    }

    /**
     * @return the value mapped to the key [k], or none if the key isn't present.
     */
    operator fun get(k: K): V? {
        val hash = getHash(k)
        var node = table[hash] ?: return null

        // If the key matches, return the value.
        if (node.k == k)
            return node.v

        // Otherwise, iterate through linked list until keys match or there are no more
        while (node.next != null) {
            node = node.next!!
            if (node.k == k)
                return node.v
        }
        return null
    }

    /**
     * Remove the key/value pair associated with key [k] from the table.
     * @return true if the element was successfully removed, false if it wasn't found.
     */
    fun remove(k: K): Boolean {
        val hash = getHash(k)
        var node = table[hash] ?: return false

        // if the key matches, remove the element
        if (node.k == k) {
            table[hash] = node.next
            return true
        }

        // Otherwise, iterate through linked list until keys match or there are no more
        while (node.next != null) {
            if (node.next!!.k == k) {
                node.next = node.next!!.next
                return true
            }
            node = node.next!!
        }
        return false
    }

    /**
     * @return the number of key/value pairs in the hash table
     */
    fun size(): Int {
        var count = 0
        for (i in 0..<size) {
            var node = table[i]
            while (node != null) {
                count++
                node = node.next
            }
        }
        return count
    }

    /**
     * @return the full list of key-value pairs for the hash table
     */
    fun keyValuePairs(): List<Pair<K,V>> {
        val pairs = mutableListOf<Pair<K,V>>()
        for (i in 0..<size) {
            var node = table[i]
            while (node != null) {
                pairs.add(Pair(node.k, node.v))
                node = node.next
            }
        }
        return pairs
    }

    /**
     * @return the load factor of the table (number of elements / number of buckets)
     */
    fun getLoadFactor(): Double {
        return size()/size.toDouble()
    }

    /**
     * @return the hash code of the key [k]
     */
    private fun getHash(k: K): Int {
        return k.hashCode() % size
    }
}