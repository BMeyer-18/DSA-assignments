package org.example

/**
 * Class for storing and performing operations on square matrices.
 * @property size the number of rows in the matrix, which is equal to the number of columns.
 * @property fillVal the integer value to set each initial value to. Defaults to 0.
 * @constructor creates a square matrix of the specified size, filled with [fillVal].
 */
class SquareMatrix(val size: Int, val fillVal: Int = 0) {
    val matrix = Array<Array<Int>>(size) { Array<Int>(size) { fillVal } }

    /**
     * Overrides the [i, j] get operator, getting the value at the specified [row] and
     * [col] in the matrix.
     * @return the integer value in that index of the square matrix
     */
    operator fun get(row: Int, col: Int): Int {
        return matrix[row][col]
    }

    /**
     * Overrides the [i, j] set operator, setting the value at the specified [row] and
     * [col] in the matrix to the [value].
     */
    operator fun set(row: Int, col: Int, value: Int) {
        matrix[row][col] = value
    }

    /**
     * @return the sum of this square matrix and a square matrix [mat] of the same size
     * @throws ArithmeticException if the matrices are not the same size
     */
    operator fun plus(mat: SquareMatrix): SquareMatrix {
        val sum = SquareMatrix(size)
        if (size != mat.size)
            throw ArithmeticException("Matrices are not of same size")
        for (r in 0..<size)
            for (c in 0..<size)
                sum[r, c] = matrix[r][c] + mat[r, c]
        return sum
    }

    /**
     * @return the number of rows of the matrix, which is equal to the number of columns
     */
    //fun getSize(): Int {
    //    return matrix.size
    //}

    /**
     * Splits the matrix into quarters as a block matrix.
     * @return a list of the quarters, in quadrant order (top right, top left,
     * bottom left, bottom right)
     */
    fun getQuarters(): List<SquareMatrix> {
        val quarters = (0..3).map { SquareMatrix(size/2) }
        for (r in 0..<size/2) {
            for (c in 0..<size / 2) {
                quarters[0][r, c] = get(r, c+size/2)
                quarters[1][r, c] = get(r, c)
                quarters[2][r, c] = get(r+size/2, c)
                quarters[3][r, c] = get(r+size/2, c+size/2)
            }
        }
        return quarters
    }

    /**
     * @return the matrix as a string, represented as a newline-separated list of lists
     */
    override fun toString(): String {
        return matrix.map { it.contentToString() }.reduce { prev, curr -> "\n$prev\n$curr" }
    }
}