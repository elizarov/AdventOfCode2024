import kotlin.system.measureTimeMillis

fun main() {
    val a = readInput("Day22").map { it.toInt() }
    val mod = 16777216
    val ps = Array(a.size) { IntArray(2000) }
    val pd = Array(a.size) { IntArray(2000) }
    for (si in 0..<a.size) {
        val n = a[si]
        var cur = n
        var price = cur % 10
        for (pi in 0..<2000) {
            cur = (cur * 64 xor cur).mod(mod)
            cur = (cur / 32 xor cur).mod(mod)
            cur = (cur * 2048 xor cur).mod(mod)
            val prev = price
            price = cur % 10
            ps[si][pi] = price
            pd[si][pi] = price - prev
        }
    }
    val pc = IntArray(4)
    fun compute(): Int {
        var sum = 0
        for (si in 0..<a.size) {
            for (pi in 3..<2000) {
                var ok = true
                for (j in 0..3) {
                    if (pc[j] != pd[si][pi - j]) {
                        ok = false
                        break
                    }
                }
                if (ok) {
                    sum += ps[si][pi]
                    break
                }
            }
        }
        return sum
    }
    fun find(i: Int): Int {
        if (i >= 4) return compute()
        var ans = 0
        for (j in -9..9) {
            pc[i] = j
            ans = maxOf(ans, find(i + 1))
        }
        return ans
    }
    // pc[3] = -2
    // pc[2] = 1
    // pc[1] = -1
    // pc[0] = 3
    // println(compute())
    val time = measureTimeMillis {
        println(find(0))
    }
    println("Time: $time ms")
}