import kotlin.math.abs

fun main() {
    val input = readInput("Day01").map { line ->
        line.split(' ').filter { it.isNotEmpty() }.map { it.toLong() }
    }
    val l1 = input.map { it[0] }.sorted()
    val l2 = input.map { it[1] }.sorted()
    val sum = input.indices.sumOf { i -> abs(l1[i] - l2[i]) }
    println(sum)
}