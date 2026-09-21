package org.example.linked_data_structures

/**
 * A class for a stack to store items of type T
 *
 * @param T the type of data to be stored by the stack
 * @constructor Creates an empty stack
 */
class Stack<T> {
    private val stack = LinkedList<T>()

    /**
     * Add [data] to the top of the stack
     */
    fun push(data: T) {
        stack.pushFront(data)
    }

    /**
     * Remove the element at the top of the stack. If the stack is empty, it remains unchanged.
     * @return the value at the top of the stack or nil if none exists
     */
    fun pop(): T? {
        return stack.popFront()
    }

    /**
     * @return the value on top of the list or nil if none exists
     */
    fun peek(): T? {
        return stack.peekFront()
    }

    /**
     * @return true if the stack is empty and false otherwise
     */
    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }
}