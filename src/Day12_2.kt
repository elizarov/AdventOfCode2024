fun main() {
    val a = readInput("Day12").toCharArray2()
    val (m, n) = a.size2()
    val f = Array(m) { IntArray(n) }
    val q = ArrayList<P2>()
    var block = 0
    fun enq(i: Int, j: Int, c: Char) {
        if (i !in 0..<m || j !in 0..<n) return
        if (f[i][j] != 0 || a[i][j] != c) return
        f[i][j] = block
        q.add(P2(i, j))
    }
    val (di, dj) = RDLU_DIRS
    var sum = 0L
    for (i0 in 0..<m) for (j0 in 0..<n) if (f[i0][j0] == 0) {
        block++
        q.clear()
        val c = a[i0][j0]
        enq(i0, j0, c)
        var h = 0
        while (h < q.size) {
            val (i, j) = q[h++]
            for (k in 0..3) enq(i + di[k], j + dj[k], c)
        }
        val g = Array(4) { ArrayList<P2>() }
        for ((i, j) in q) {
            for (k in 0..3) {
                val i1 = i + di[k]
                val j1 = j + dj[k]
                if (i1 !in 0..<m || j1 !in 0..<n || f[i1][j1] != block) {
                    g[k].add(P2(i1, j1))
                }
            }
        }
        var p = 0L
        for (k in 0..3) {
            val ii = di[(k + 1) % 4]
            val gr: Map<Int, List<P2>>
            val sel: (P2) -> Int
            if (ii == 0) {
                gr = g[k].groupBy { it.i }
                sel = P2::j
            } else {
                gr = g[k].groupBy { it.j }
                sel = P2::i
            }
            for (row: List<P2> in gr.values) {
                var prev = Int.MIN_VALUE
                for (pt in row.sortedBy(sel)) {
                   val cur = sel(pt)
                   if (cur > prev + 1) p++
                   prev = cur
                }
            }
        }
        sum += p * q.size
    }
    println(sum)
}