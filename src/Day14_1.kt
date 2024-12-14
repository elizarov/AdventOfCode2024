fun main() {
    val input = readInput("Day14")
    val m = 101
    val n = 103
    val k = 100
    val p = input.map { s ->
        val (x, y, vx, vy) = Regex("p=(\\d+),(\\d+) v=(\\-?[0-9]+),(\\-?[0-9]+)").matchEntire(s)!!.destructured.toList().map { it.toInt() }
        P2((x + vx * k).mod(m), (y + vy * k).mod(n))
    }
    val c = IntArray(4)
    for ((x, y) in p) {
        val xi = if (x < m / 2) 0 else if (x > m / 2) 1 else continue
        val yi = if (y < n / 2) 0 else if (y > n / 2) 1 else continue
        c[xi * 2 + yi]++
    }
    val ans = c.fold(1L, Long::times)
    println(c.joinToString(" "))
    println(ans)
}