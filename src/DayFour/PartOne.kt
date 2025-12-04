package DayFour

import common.cleanInput
import java.io.File

fun main() {
    val grid = File("src/DayFour/input.txt")
        .readLines()
        .map { cleanInput(it) }
        .filter { it.isNotEmpty() }

    val height = grid.size
    val width = grid.first().length

    fun neighborsOf(y: Int, x: Int) =
        sequence {
            for (dy in -1..1)
                for (dx in -1..1)
                    if (!(dy == 0 && dx == 0))
                        yield(y + dy to x + dx)
        }
            .filter { (ny, nx) -> ny in 0 until height && nx in 0 until width }

    fun isAccessible(y: Int, x: Int): Boolean {
        val adjacentRolls = neighborsOf(y, x)
            .count { (ny, nx) -> grid[ny][nx] == '@' }

        return adjacentRolls < 4
    }

    val accessibleCount =
        (0 until height).sumOf { y ->
            (0 until width).count { x ->
                grid[y][x] == '@' && isAccessible(y, x)
            }
        }

    println(accessibleCount)
}
