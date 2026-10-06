package org.example

fun main() {
    val A = SquareMatrix(4)
    A[0, 1] = 1.0
    A[0, 2] = 2.0
    println(A)
    println(A.getQuarters())
}