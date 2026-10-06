package org.example

fun main() {
    val A = SquareMatrix(4)
    A[0, 1] = 1
    A[0, 2] = 2
    println(A)
    println(A.getQuarters())
}