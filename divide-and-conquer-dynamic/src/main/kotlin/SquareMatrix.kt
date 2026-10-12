package org.example

/**
 * Class for storing and performing operations on square matrices.
 * @property size the number of rows in the matrix, which is equal to the number of columns.
 * @property fillVal the double value to set each initial value to. Defaults to 0.
 * @constructor creates a square matrix of the specified size, filled with [fillVal].
 */
class SquareMatrix(val size: Int, val fillVal: Double = 0.0) {
    val matrix = Array(size) { DoubleArray(size) { fillVal } }

    /**
     * Overrides the [i, j] get operator, getting the value at the specified [row] and
     * [col] in the matrix.
     * @return the double value in that index of the square matrix
     */
    operator fun get(row: Int, col: Int): Double {
        return matrix[row][col]
    }

    /**
     * Overrides the [i, j] set operator, setting the value at the specified [row] and
     * [col] in the matrix to the [value].
     */
    operator fun set(row: Int, col: Int, value: Double) {
        matrix[row][col] = value
    }

    /**
     * @return the sum of this square matrix and a square matrix [other] of the same size,
     * or null if they have different sizes.
     * @throws ArithmeticException if arrays have different sizes
     */
    operator fun plus(other: SquareMatrix): SquareMatrix {
        if (size != other.size)
            throw ArithmeticException("Arrays have different sizes")
        val sum = SquareMatrix(size)
        for (r in 0..<size)
            for (c in 0..<size)
                sum[r, c] = matrix[r][c] + other[r, c]
        return sum
    }

    /**
     * @return the difference between this square matrix and a square matrix [other] of the
     * same size, or null if they have different sizes.
     * @throws ArithmeticException if arrays have different sizes
     */
    operator fun minus(other: SquareMatrix): SquareMatrix {
        if (size != other.size)
            throw ArithmeticException("Arrays have different sizes")
        val difference = SquareMatrix(size)
        for (r in 0..<size)
            for (c in 0..<size)
                difference[r, c] = matrix[r][c] - other[r, c]
        return difference
    }

    /**
     * Multiply this matrix by [other].
     * @return this*[other] if the dimensions are compatible and null otherwise
     * @throws ArithmeticException if arrays have different sizes
     */
    operator fun times(other: SquareMatrix): SquareMatrix {
        if (size != other.size)
            throw ArithmeticException("Arrays have different sizes")
        val product = SquareMatrix(size, 0.0)
        for (r in 0..<size)
            for (c in 0..<size)
                for (i in 0..<size)
                    product[r, c] += matrix[r][i] * other[i, c]
        return product
    }

    /**
     * Multiply this matrix by [other], following Strassen's algorithm.
     * @return this*[other] if the dimensions are compatible and null otherwise
     * @throws ArithmeticException if arrays have different sizes
     */
    fun strassenMultiply(other: SquareMatrix): SquareMatrix {
        if (size != other.size)
            throw ArithmeticException("Arrays have different sizes")
        if (size == 1)
            return SquareMatrix(1, get(0,0)*other[0,0])

        val blocksA = getQuarters()
        val blocksB = other.getQuarters()
        val mults = Array(7) { SquareMatrix(size/2) }
        mults[0] = (blocksA[1] + blocksA[3]).strassenMultiply(blocksB[1] + blocksB[3])
        mults[1] = (blocksA[2] + blocksA[3]).strassenMultiply(blocksB[1])
        mults[2] = blocksA[1].strassenMultiply(blocksB[0] - blocksB[3])
        mults[3] = blocksA[3].strassenMultiply(blocksB[2] - blocksB[1])
        mults[4] = (blocksA[1] + blocksA[0]).strassenMultiply(blocksB[3])
        mults[5] = (blocksA[2] - blocksA[1]).strassenMultiply(blocksB[1] + blocksB[0])
        mults[6] = (blocksA[0] - blocksA[3]).strassenMultiply(blocksB[2] + blocksB[3])

        val product00 = mults[0]+mults[3]-mults[4]+mults[6]
        val product01 = mults[2]+mults[4]
        val product10 = mults[1]+mults[3]
        val product11 = mults[0]-mults[1]+mults[2]+mults[5]

        val product = SquareMatrix(size, 0.0)
        for (r in 0..<size/2) {
            for (c in 0..<size/2) {
                product[r, c] = product00[r, c]
                product[r, c+size/2] = product01[r, c]
                product[r+size/2, c] = product10[r, c]
                product[r+size/2, c+size/2] = product11[r, c]
            }
        }

        return product
    }

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
     * @return true if matrices have same size and elements, false otherwise
     */
    override fun equals(other: Any?): Boolean {
        if (other is SquareMatrix && size == other.size) {
            for (r in 0..<size)
                for (c in 0..<size)
                    if (get(r, c) != other[r, c])
                        return false
            return true
        }
        return false
    }

    /**
     * @return hash code based on size and contents of matrix
     */
    override fun hashCode(): Int {
        var result = size
        result = 31 * result + matrix.contentDeepHashCode()
        return result
    }

    /**
     * @return the matrix as a string, represented as a newline-separated list of lists
     */
    override fun toString(): String {
        return matrix.map { it.contentToString() }.reduce { prev, curr -> "\n$prev\n$curr" }
    }
}