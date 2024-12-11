fun main() {
    val input = readInput("Day11")
    val a = input[0].split(" ").map { it.toLong() }
    var g0 = HashMap<Long, Long>(a.groupingBy { it }.eachCount().mapValues { it.value.toLong() })
    var g1 = HashMap<Long, Long>(a.groupingBy { it }.eachCount().mapValues { it.value.toLong() })
    fun add(nn: Long, c: Long) {
        g1[nn] = g1.getOrDefault(nn, 0) + c
    }
    repeat(25) { iter ->
        g1.clear()
        for ((n, c) in g0) {
            if (n == 0L) {
                add(1, c)
                continue
            }
            var dc = 1
            var pow = 1L
            while (n >= pow * 10) {
                pow *= 10
                dc++
            }
            if (dc % 2 == 0) {
                var pow2 = 1L
                repeat(dc / 2) { pow2 *= 10 }
                val ln = n / pow2
                val rn = n % pow2
                add(ln, c)
                add(rn, c)
            } else {
                val nn = n * 2024
                add(nn, c)
            }
        }
        g1 = g0.also { g0 = g1 }
    }
    println(g0.values.sum())
}