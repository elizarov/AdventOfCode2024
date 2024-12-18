fun main() {
    val a = readInput("Day18").map {
        val (i, j) = it.split(",").map { it.toInt() }
        P2(i, j)
    }
    val n = 71
    val c = Array(n) { BooleanArray(n) }
    val (di, dj) = RDLU_DIRS
    for ((ic, jc) in a) {
        c[ic][jc] = true
        val dist = Array(n) { IntArray(n) { Int.MAX_VALUE } }
        val q = ArrayList<P2>()
        fun enq(i: Int, j: Int, d: Int) {
            if (i !in 0..<n || j !in 0..<n) return
            if (c[i][j]) return
            if (dist[i][j] <= d) return
            dist[i][j] = d
            q += P2(i, j)
        }
        enq(0, 0, 0)
        var h = 0
        while (h < q.size) {
            val (i, j) = q[h++]
            val d = dist[i][j]
            for (k in 0..3) {
                enq(i + di[k], j + dj[k], d + 1)
            }
        }
        if (dist[n - 1][n - 1] == Int.MAX_VALUE) {
            println("$ic,$jc")
            break
        }
    }
}