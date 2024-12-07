fun main() {
    val input = readInput("Day07")
    var sum = 0L
    for (s in input) {
        val t = s.substringBefore(':').toLong()
        val a = s.substringAfter(':').trim().split(" ").map { it.toLong() }
        fun scan(cur: Long, i: Int): Boolean {
            if (i >= a.size) {
                return cur == t
            }
            if (scan(cur + a[i], i + 1)) return true
            if (scan(cur * a[i], i + 1)) return true
            return false
        }
        if (scan(a[0], 1)) sum += t
    }
    println(sum)
}