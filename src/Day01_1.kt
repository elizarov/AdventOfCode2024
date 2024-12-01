import kotlin.math.abs

fun main() {
    val input = readInput("Day01")
    val l1 = ArrayList<Long>()
    val l2 = ArrayList<Long>()
    for (s in input) {
        val (a, b) = s.replace(Regex("[ ]+"), " ").split(' ').map { it.toLong() }
        l1.add(a)
        l2.add(b)
    }
    var sum = 0L
    l1.sort()
    l2.sort()
    for (i in input.indices) {
        sum += abs(l1[i] - l2[i])
    }
    println(sum)
}