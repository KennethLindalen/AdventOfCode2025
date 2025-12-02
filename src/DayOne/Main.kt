package DayOne

fun calculateNewPlacement(start: Int, rotationRaw: String): IntArray {
    val rotation = rotationRaw.replace("\uFEFF", "").trim()
    if (rotation.isEmpty()) return intArrayOf(start, 0)

    val direction: Char = rotation.first()
    val steps: Int = rotation.substring(1).toInt()

    var position = start
    var zeroPasses = 0

    val delta = if (direction == 'R') 1 else -1

    repeat(steps) {
        position += delta

        if (position == 100) position = 0
        else if (position == -1) position = 99

        if (position == 0) {
            zeroPasses++
        }
    }

    return intArrayOf(position, zeroPasses)
}


fun main() {
    var start = 50
    var count = 0

    java.io.File("src/DayOne/input.txt").forEachLine { line ->
        val res = calculateNewPlacement(start, line)
        val newPos = res[0]
        val zerosThisRotation = res[1]

        start = newPos
        count += zerosThisRotation
    }

    println("Password (method 0x434C49434B): $count")
}
