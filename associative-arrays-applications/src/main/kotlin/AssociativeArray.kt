package org.example

import java.io.File

/**
 * Represents a mapping of keys to values with a hash table that grows with the number of elements.
 * @param K the type of the keys
 * @param V the type of the values
 * @constructor creates an empty associative array with no key/value pairs
 */
class AssociativeArray<K,V> {
    private var table = HashTable<K,V>()

    /**
     * Insert the mapping from the key, [k], to the value, [v].
     * If the key already maps to a value, replace the mapping.
     * If this increases the load factor to 3 or greater, rehash.
     */
    operator fun set(k: K, v: V) {
        table[k] = v

        if (table.getLoadFactor() >= 3.0)
            rehash()
    }

    /**
     * @return true if [k] is a key in the associative array.
     */
    operator fun contains(k: K): Boolean {
        return table[k] != null
    }

    /**
     * @return the value associated with the key [k] or null if it doesn't exist.
     */
    operator fun get(k: K): V? {
        return table[k]
    }

    /**
     * Remove the key, [k], from the associative array
     * @return true if the element was successfully removed, false if it wasn't found.
     */
    fun remove(k: K): Boolean {
        return table.remove(k)
    }

    /**
     * @return the number of elements stored in the associative array.
     */
    fun size(): Int {
        return table.size()
    }

    /**
     * @return the full list of key-value pairs for the associative array
     */
    fun keyValuePairs(): List<Pair<K,V>> {
        return table.keyValuePairs()
    }

    /**
     * Replace the table with a new table based on a new hashing function
     * with a larger number of buckets. Works for up to 4,831,838,222 items.
     */
    private fun rehash() {
        // load recommended hash primes from file
        val primeList: List<Int>
        File("src/main/resources/hash_primes.csv").useLines { lines ->
            primeList = lines.toList().map { it.toInt() }
        }

        // find next large hash prime to use as size
        var nextSize = -1
        for (prime in primeList) {
            if (prime > table.size) {
                nextSize = prime
                break
            }
        }
        if (nextSize == -1)
            throw RuntimeException("Number of key/value pairs exceeds 4,831,838,222")
        val newTable = HashTable<K,V>(nextSize)

        // add all existing key/value pairs to the new table
        val pairs = keyValuePairs()
        for ((k, v) in pairs)
            newTable[k] = v

        // reassign the table to the new, larger table
        table = newTable
    }
}