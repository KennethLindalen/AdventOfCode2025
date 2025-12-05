package DayFive

import common.cleanInput
import java.io.File


fun parseRanges(rangeLines: List<String>): List<LongRange> = rangeLines.map { line ->
    val (startStr, endStr) = line.split("-")
    val start = startStr.toLong()
    val end = endStr.toLong()
    start..end
}


fun mergeRanges(ranges: List<LongRange>): List<LongRange> {
    if (ranges.isEmpty()) return emptyList()

    val sorted = ranges.sortedBy { it.first }
    val merged = mutableListOf<LongRange>()
    var currentStart = sorted[0].first
    var currentEnd = sorted[0].last

    for (range in sorted.drop(1)) {
        if (range.first <= currentEnd + 1) {
            if (range.last > currentEnd) {
                currentEnd = range.last
            }
        } else {
            merged.add(currentStart..currentEnd)
            currentStart = range.first
            currentEnd = range.last
        }
    }
    merged.add(currentStart..currentEnd)

    return merged
}

fun main() {
    val lines = File("src/DayFive/input.txt").readLines().map { cleanInput(it) }
    val rangeLines = lines.filter { it.isNotEmpty() && "-" in it }
    val ranges = parseRanges(rangeLines)
    val merged = mergeRanges(ranges)

    val totalFresh: Long = merged.fold(0L) { acc, range ->
        acc + (range.last - range.first + 1)
    }

    println(totalFresh)
}
