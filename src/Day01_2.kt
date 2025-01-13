fun main() {
    val input = readInput("Day01").map { line ->
        line.split(' ').filter { it.isNotEmpty() }.map { it.toLong() }
    }
    val l1 = input.map { it[0] }
    val l2 = input.map { it[1] }
    val cnt = l2.groupingBy { it }.eachCount()
    val sum = l1.sumOf { x -> x * (cnt[x] ?: 0) }
    println(sum)
}