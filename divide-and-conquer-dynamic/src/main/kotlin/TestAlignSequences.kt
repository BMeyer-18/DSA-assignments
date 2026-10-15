package org.example

/**
 * Runs all unit tests for my implementation of the Needleman-Wunsch algorithm
 * @throws RuntimeException if a test fails
 */
fun testAlignSequences() {
    testEmptySequences()
    testDifferentSizeSequences()
    testNoAlignment()
    testSequenceOrders()
    println("Passed all tests for global sequence alignment\n")
}

/**
 * Tests function for unexpected behavior when handling empty sequences
 * @throws RuntimeException if a test fails
 */
fun testEmptySequences() {
    val seq1Input = listOf("", "AATCG", "")
    val seq2Input = listOf("", "", "ACATG")

    val expectedScores = listOf(0, -10, -10)
    val expectedAlignments = listOf(listOf("", ""),
                                    listOf("AATCG", "-----"),
                                    listOf("-----", "ACATG"))

    for (i in expectedScores.indices) {
        val (score, alignments) = alignGlobally(seq1Input[i], seq2Input[i])
        if (score != expectedScores[i])
            throw RuntimeException("Failed to determine score with inputs ${seq1Input[i]} and ${seq2Input[i]}")
        if (alignments[0] != expectedAlignments[i][0] || alignments[1] != expectedAlignments[i][1])
            throw RuntimeException("Failed to write alignments with inputs ${seq1Input[i]} and ${seq2Input[i]}")
    }

    println("Passed tests for empty sequences")
}

/**
 * Tests function when sequences passed in have different sizes
 * @throws RuntimeException if a test fails
 */
fun testDifferentSizeSequences() {
    val seq1Input = listOf("A", "GCATCC", "TGGCGACAACCGTAGCG")
        val seq2Input = listOf("TATCG", "CAC", "GGCCACCACGATAGCAGAATTTTTTCGCGATAATGGAAAGCGAGTCGT")

    val expectedScores = listOf(-7, -3, -49)
    val expectedAlignments = listOf(listOf("-A---", "TATCG"),
                                    listOf("GCATCC", "-CA--C"),
                                    listOf("TGG-----CG--A-C--AA------C-CG----T--------AG-CG-", "GGCCACCACGATAGCAGAATTTTTTCGCGATAATGGAAAGCGAGTCGT"))

    for (i in expectedScores.indices) {
        val (score, alignments) = alignGlobally(seq1Input[i], seq2Input[i])
        if (score != expectedScores[i])
            throw RuntimeException("Failed to determine score with inputs ${seq1Input[i]} and ${seq2Input[i]}")
        if (alignments[0] != expectedAlignments[i][0] || alignments[1] != expectedAlignments[i][1])
            throw RuntimeException("Failed to write alignments with inputs ${seq1Input[i]} and ${seq2Input[i]}")
    }

    println("Passed tests for sequences of different sizes")
}

/**
 * Tests function for very poorly aligned sequences with nothing in common
 * @throws RuntimeException if a test fails
 */
fun testNoAlignment() {
    val seq1Input = listOf("AAAAA", "TATAT")
    val seq2Input = listOf("TTTTT", "GCGCG")

    val expectedScores = listOf(-5, -5)
    val expectedAlignments = listOf(listOf("AAAAA", "TTTTT"),
                                    listOf("TATAT", "GCGCG"))

    for (i in expectedScores.indices) {
        val (score, alignments) = alignGlobally(seq1Input[i], seq2Input[i])
        if (score != expectedScores[i])
            throw RuntimeException("Failed to determine score with inputs ${seq1Input[i]} and ${seq2Input[i]}")
        if (alignments[0] != expectedAlignments[i][0] || alignments[1] != expectedAlignments[i][1])
            throw RuntimeException("Failed to write alignments with inputs ${seq1Input[i]} and ${seq2Input[i]}")
    }

    println("Passed tests for sequences with nothing in common")
}

/**
 * Tests to make sure sequence order doesn't impact the resulting score
 * @throws RuntimeException if a test fails
 */
fun testSequenceOrders() {
    val seq1Input = listOf("AATCG", "AACG", "CGACCG", "TGCATTGA")
    val seq2Input = listOf("AACG", "AATCG", "TGCATTGA", "CGACCG")

    val expectedScores = listOf(2, 2, -4, -4)
    val expectedAlignments = listOf(listOf("AATCG", "AA-CG"),
                            listOf("AA-CG", "AATCG"),
                            listOf("CG-ACCG-", "TGCATTGA"),
                            listOf("TGCATTGA", "CG-ACCG-"))

    for (i in expectedScores.indices) {
        val (score, alignments) = alignGlobally(seq1Input[i], seq2Input[i])
        if (score != expectedScores[i])
            throw RuntimeException("Failed to determine score with inputs ${seq1Input[i]} and ${seq2Input[i]}")
        if (alignments[0] != expectedAlignments[i][0] || alignments[1] != expectedAlignments[i][1])
            throw RuntimeException("Failed to write alignments with inputs ${seq1Input[i]} and ${seq2Input[i]}")
    }

    println("Passed tests for longer sequences")
}