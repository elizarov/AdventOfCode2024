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
    val cnt = l2.groupingBy { it }.eachCount()
    for (x in l1) {
        sum += x * (cnt[x] ?: 0)
    }
    println(sum)
}