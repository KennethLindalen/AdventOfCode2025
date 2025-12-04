package DayFour

import common.cleanInput
import java.io.File

typealias Grid = MutableList<CharArray>

fun readGrid(path: String): Grid =
    File(path)
        .readLines()
        .map { cleanInput(it) }
        .filter { it.isNotEmpty() }
        .map { it.toCharArray() }
        .toMutableList()

fun inBounds(grid: Grid, y: Int, x: Int): Boolean =
    y in grid.indices && x in grid[0].indices

fun adjacentRolls(grid: Grid, y: Int, x: Int): Int {
    var count = 0
    for (dy in -1..1) {
        for (dx in -1..1) {
            if (dy == 0 && dx == 0) continue
            val ny = y + dy
            val nx = x + dx
            if (inBounds(grid, ny, nx) && grid[ny][nx] == '@') {
                count++
            }
        }
    }
    return count
}

fun accessiblePositions(grid: Grid): List<Pair<Int, Int>> {
    val result = mutableListOf<Pair<Int, Int>>()
    for (y in grid.indices) {
        for (x in grid[0].indices) {
            if (grid[y][x] == '@' && adjacentRolls(grid, y, x) < 4) {
                result += y to x
            }
        }
    }
    return result
}

fun removeAccessible(grid: Grid): Int {
    val toRemove = accessiblePositions(grid)
    for ((y, x) in toRemove) {
        grid[y][x] = '.'
    }
    return toRemove.size
}

fun main() {
    val grid = readGrid("src/DayFour/input.txt")

    var totalRemoved = 0
    while (true) {
        val removedThisRound = removeAccessible(grid)
        if (removedThisRound == 0) break
        totalRemoved += removedThisRound
    }

    println(totalRemoved)
}
