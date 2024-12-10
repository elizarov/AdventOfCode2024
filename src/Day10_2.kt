fun main() {
    val a = readInput("Day10").toCharArray2()
    val (n, m) = a.size2()
    var sum = 0L
    val q = ArrayList<P2>()
    val f = HashMap<P2, Long>()
    fun enq(i: Int, j: Int, c: Char, r: Long) {
        if (i !in 0..<n || j !in 0..<m) return
        if (a[i][j] != c) return
        val p = P2(i, j)
        if (p !in f) q += p
        f[p] = f.getOrDefault(p, 0) + r
    }
    val (di, dj) = RDLU_DIRS
    for (i0 in 0..<n) for (j0 in 0..<m) if (a[i0][j0] == '0') {
        q.clear()
        f.clear()
        enq(i0, j0, '0', 1)
        var qh = 0
        while (qh < q.size) {
            val (i, j) = q[qh++]
            val c = a[i][j]
            val r = f[P2(i, j)]!!
            if (c == '9') {
                sum += r
                continue
            }
            for (k in 0..3) {
                enq(i + di[k], j + dj[k], c + 1, r)
            }
        }
    }
    println(sum)
}