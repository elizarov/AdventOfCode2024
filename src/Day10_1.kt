fun main() {
    val a = readInput("Day10").toCharArray2()
    val (n, m) = a.size2()
    var sum = 0L
    val q = ArrayList<P2>()
    val f = HashSet<P2>()
    fun enq(i: Int, j: Int, c: Char) {
        if (i !in 0..<n || j !in 0..<m) return
        if (a[i][j] != c) return
        if (f.add(P2(i, j))) q += P2(i, j)
    }
    val (di, dj) = RDLU_DIRS
    for (i0 in 0..<n) for (j0 in 0..<m) if (a[i0][j0] == '0') {
        q.clear()
        f.clear()
        enq(i0, j0, '0')
        var qh = 0
        while (qh < q.size) {
            val (i, j) = q[qh++]
            val c = a[i][j]
            if (c == '9') {
                sum++
                continue
            }
            for (k in 0..3) {
                enq(i + di[k], j + dj[k], c + 1)
            }
        }
    }
    println(sum)
}