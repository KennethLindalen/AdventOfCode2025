package DayTwo

fun cleanInput(input: List<String>): List<String> {
    return input.map { it.replace("\uFEFF", "").trim() }
}

fun splitIntoParts(input: String, parts: Int): List<String>? {
    if (input.length % parts != 0) return null
    val size = input.length / parts
    return input.chunked(size)
}


fun findValidIds(input: List<String>): Long {
    var invalidSum: Long = 0;
    for (i in input) {
        val fields = i.split("-");
        for (j in fields[0].toLong()..fields[1].toLong()) {
            val s: String = j.toString()
            for (k in 2..s.length) {
                val strings = splitIntoParts(s, k)
                if (strings != null) {
                    val size = strings.distinct().size
                    if (size == 1) {
                        invalidSum += j
                        break
                    }
                }
            }
        }
    }
    return invalidSum;
}


fun main() {
    var sum: Long = 0;
    java.io.File("src/DayTwo/input.txt").forEachLine { line ->
        val fields = cleanInput(line.split(","));
        sum += findValidIds(fields)
    }
    println(sum)
}
