package org.example

import kotlin.math.max

/**
 * Aligns protein sequences [seq1] and [seq2] globally according to the Needleman-Wunsch
 * algorithm. The scoring system gives 1 point for a match, -1 for a mismatch, and -2 for a gap.
 * @return pair of the alignment score and the list of strings with the two alignments
 */
fun alignGlobally(seq1: String, seq2: String): Pair<Int, List<String>> {
    val table = Array(seq1.length+1) { IntArray(seq2.length+1) { 0 } }

    // setting first row and column to defaults (0 in top left, gap penalty for each step out)
    for (i in 1..seq1.length)
        table[i][0] = table[i-1][0] - 2
    for (i in 1..seq2.length)
        table[0][i] = table[0][i-1] - 2

    // iterating through the rest of the table, scoring alignment for each match/mismatch/gap
    for (r in 1..seq1.length) {
        for (c in 1..seq2.length) {
            // find the best score between the top and left values (with gap penalty)
            val maxGap = max(table[r][c-1], table[r-1][c]) - 2

            // find the best score between the diagonal and side values (with match/mismatch score)
            if (seq1[r-1] == seq2[c-1])
                table[r][c] = max(table[r-1][c-1] + 1, maxGap)
            else
                table[r][c] = max(table[r-1][c-1] - 1, maxGap)
        }
    }

    println(table.contentDeepToString())

    // backtracking through table to show sequences
    var seq1Alignment = ""
    var seq2Alignment = ""
    var r = seq1.length; var c = seq2.length
    // keep backtracking until we get to the start of one of the sequences
    while(r > 0 && c > 0) {
        if (seq1[r-1] == seq2[c-1] || table[r-1][c-1] >= max(table[r-1][c], table[r][c-1])) {
            // if the nucleotides match, move diagonally back and add it to both alignment strings
            // or if the diagonal value is greater than the top or left values
            seq1Alignment = seq1[r-1].toString() + seq1Alignment
            seq2Alignment = seq2[c-1].toString() + seq2Alignment
            r--; c--
        } else if(table[r-1][c] >= table[r][c-1]) {
            // if moving up is better than left, move up and add "-" to the first alignment string
            seq1Alignment = seq1[r-1].toString() + seq1Alignment
            seq2Alignment = "-$seq2Alignment"
            r--
        } else {
            // if moving left is best, move left and add "-" to the second alignment string
            seq1Alignment = "-$seq1Alignment"
            seq2Alignment = seq2[c-1].toString() + seq2Alignment
            c--
        }
    }

    // for any leftover values of r or c, add dashes to the beginning of their alignment strings
    for (i in 0..<r)
        seq2Alignment = "-$seq2Alignment"
    for (i in 0..<c)
        seq1Alignment = "-$seq1Alignment"

    return Pair(table[seq1.length][seq2.length], listOf(seq1Alignment, seq2Alignment))
}