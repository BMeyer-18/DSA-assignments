package org.example

/**
 * Represents a mapping of keys to values
 * @param K the type of the keys
 * @param V the type of the values
 */
class AssociativeArray<K,V> {
    /**
     * @return true if [k] is a key in the associative array.
     */
    operator fun contains(k: K): Boolean {

        return true
    }

    /**
     * Insert the mapping from the key, [k], to the value, [v].
     * If the key already maps to a value, replace the mapping.
     */
    operator fun set(k: K, v: V) {

    }

    /**
     * @return the value associated with the key [k] or null if it doesn't exist.
     */
    operator fun get(k: K): V? {

        return null
    }

    /**
     * Remove the key, [k], from the associative array
     * @return true if the element was successfully removed, false if it wasn't found.
     */
    fun remove(k: K): Boolean {

        return false
    }

    /**
     * @return the number of elements stored in the hash table/
     */
    fun size(): Int {

        return 0
    }

    /**
     * @return the full list of key-value pairs for the associative array
     */
    fun keyValuePairs(): List<Pair<K,V>> {

        return listOf()
    }
}