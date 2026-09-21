package org.example.linked_data_structures

/**
 * A class for a doubly linked list to contain elements of type T.
 *
 * @param T the type of data to be stored by the linked list
 * @property head the first node in the linked list
 * @property tail the last node in the linked list
 * @constructor Creates an empty linked list
 */
class LinkedList<T> {
    /**
     * A data class to represent an individual node in the linked list.
     *
     * @param T the type of data to be stored in the node
     * @property data the data of type T to be stored in the node
     * @property next the next node in the linked list. Null if none.
     * @property last the previous node in the linked list. Null if none.
     * @constructor Creates a node with data and a reference to the next and previous elements.
     */
    data class Node<T>(var data: T, var next: Node<T>?, var last: Node<T>?)

    var head: Node<T>? = null
    var tail: Node<T>? = null

    /**
     * Adds the element [data] to the front of the list
     */
    fun pushFront(data: T) {
        val newNode = Node(data, head, null)
        head?.last = newNode
        head = newNode
        if (tail == null)
            tail = newNode
    }

    /**
     * Adds the element [data] to the back of the linked list
     */
    fun pushBack(data: T) {
        val newNode = Node(data, null, tail)
        tail?.next = newNode
        tail = newNode
        if (head == null)
            head = newNode
    }

    /**
     * Removes an element from the front of the list. If the list is empty, it is unchanged.
     * @return the value at the front of the list or nil if none exists
     */
    fun popFront(): T? {
        val oldHead = head
        head = oldHead?.next
        head?.last = null
        if (head == null)
            tail = null
        return oldHead?.data
    }

    /**
     * Removes an element from the back of the list. If the list is empty, it is unchanged.
     * @return the value at the back of the list or nil if none exists
     */
    fun popBack(): T? {
        val oldTail = tail
        tail = oldTail?.last
        tail?.next = null
        if (tail == null)
            head = null
        return oldTail?.data
    }

    /**
     * @return the value at the front of the list or nil if none exists
     */
    fun peekFront(): T? {
        return head?.data
    }

    /**
     * @return the value at the back of the list or nil if none exists
     */
    fun peekBack(): T? {
        return tail?.data
    }

    /**
     * @return true if the list is empty and false otherwise
     */
    fun isEmpty(): Boolean {
        return head == null
    }
}