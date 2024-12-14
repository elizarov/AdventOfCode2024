fun main() {
    val input = readInput("Day14")
    val m = 101
    val n = 103
    data class RD(val x: Int, val y: Int, val vx: Int, val vy: Int)
    val rd = input.map { s ->
        val (x, y, vx, vy) = Regex("p=(\\d+),(\\d+) v=(\\-?[0-9]+),(\\-?[0-9]+)").matchEntire(s)!!.destructured.toList()
            .map { it.toInt() }
        RD(x, y, vx, vy)
    }
    val c = Array(n) { IntArray(m) }
    var best = 0
    var bestK = -1
    val q = ArrayList<P2>()
    fun enq(x: Int, y: Int) {
        if (x !in 0..<m || y !in 0..<n) return
        if (c[y][x] <= 0) return
        c[y][x] = -1
        q += P2(x, y)
    }
    for (k in 1..m * n) {
        val p = rd.map { (x, y, vx, vy) ->
            P2((x + vx * k).mod(m), (y + vy * k).mod(n))
        }
        for ((x, y) in p) c[y][x]++
        for (x0 in 0..<m) for (y0 in 0..<n) if (c[y0][x0] > 0) {
            q.clear()
            var h = 0
            enq(x0, y0)
            while (h < q.size) {
                val (x, y) = q[h++]
                for (dx in -1..1) for (dy in -1..1) enq(x + dx, y + dy)
            }
            if (q.size > best) {
                best = q.size
                bestK = k
            }
        }
        for ((x, y) in p) c[y][x] = 0
    }
    println(bestK)
}
