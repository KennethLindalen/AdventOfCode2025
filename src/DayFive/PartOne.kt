package DayFive

import common.cleanInput
import java.io.File
import java.util.Collections.emptyList




fun main() {
    val lines = File("src/DayFive/input.txt")
        .readLines()
        .map { cleanInput(it) }

    val (rangeLines, idLines) = lines
        .filter { it.isNotEmpty() }
        .partition { "-" in it }

    val ranges: List<LongRange> = rangeLines.map { line ->
        val (startStr, endStr) = line.split("-")
        val start = startStr.toLong()
        val end = endStr.toLong()
        start..end
    }

    val ids: List<Long> = idLines.map { it.toLong() }

    var counter = 0
    for (id in ids) {
        if (ranges.any { id in it }) {
            counter++
        }
    }

    println(counter)
}

