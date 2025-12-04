package DayThree

import java.io.File

fun maxBankJoltage(line: String): Int {
    val digits = line.trim().map { it - '0' }
    if (digits.size < 2) return 0
    var bestSecond = -1
    var best = 0
    for (i in digits.size - 2 downTo 0) {
        bestSecond = maxOf(bestSecond, digits[i + 1])
        val candidate = digits[i] * 10 + bestSecond
        if (candidate > best) {
            best = candidate
        }
    }

    return best
}

fun main() {
    var sum = 0L
    File("src/DayThree/input.txt").forEachLine { rawLine ->
        val line = rawLine.replace("\uFEFF", "").trim()
        if (line.isEmpty()) return@forEachLine

        val maxForBank = maxBankJoltage(line)
        println("Line: $line -> max bank joltage: $maxForBank")
        sum += maxForBank
    }

    println("Total output joltage: $sum")
}
