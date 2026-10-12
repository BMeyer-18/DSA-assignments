package org.example

fun main() {
    val a = SquareMatrix(4)
    val b = SquareMatrix(4)
    for (r in 0..<4) {
        for (c in 0..<4) {
            a[r, c] = r*4.0+c
            b[r, c] = r*4.0+c+16
        }
    }

    val normalMult = a*b
    val strassenMult = a.strassenMultiply(b)

    println(normalMult)
    println(strassenMult)
    println(normalMult == strassenMult)
}