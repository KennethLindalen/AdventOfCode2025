package DayThree

import common.cleanInput
import java.io.File

private fun StringBuilder.lastChar(): Char = this[this.length - 1]


fun maxBankJoltagePart2(line: String, k: Int = 12): Long {
    val s = cleanInput(line)
    val n = s.length
    if (n < k) return 0L
    var toRemove = n - k
    val stack = StringBuilder()
    for (ch in s) {
        while (toRemove > 0 && stack.isNotEmpty() && stack.lastChar() < ch) {
            stack.deleteCharAt(stack.length - 1)
            toRemove--
        }
        stack.append(ch)
    }

    if (toRemove > 0) {
        stack.setLength(stack.length - toRemove)
    }

    val resultStr = stack.substring(0, k)
    return resultStr.toLong()
}

fun main() {
    var sum = 0L

    File("src/DayThree/test-input.txt").forEachLine { rawLine ->
        val line = cleanInput(rawLine)
        if (line.isEmpty()) return@forEachLine

        val maxForBank = maxBankJoltagePart2(line, 12)
        println("Line: $line -> max bank joltage (12 digits): $maxForBank")
        sum += maxForBank
    }

    println("Total output joltage (part 2): $sum")
}
