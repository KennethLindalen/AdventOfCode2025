package common

fun cleanInput(input: String): String =
    input.replace("\uFEFF", "").trim()

fun cleanInput(input: List<String>): List<String> =
    input.map { it.replace("\uFEFF", "").trim() }
