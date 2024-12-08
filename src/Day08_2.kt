fun main() {
    val a = readInput("Day08").toCharArray2()
    val (m, n) = a.size2()
    val map = HashMap<Char, ArrayList<P2>>()
    a.forEachIndexed2 { i, j, c ->
        if (c != '.') {
            map.getOrPut(c) { ArrayList() }.add(P2(i, j))
        }
    }
    var ans = 0
    for (l in map.values) for (p in 0..<l.size) for (q in 0..<l.size) if (p != q) {
        val (i1, j1) = l[p]
        val (i2, j2) = l[q]
        val di = i1 - i2
        val dj = j1 - j2
        var k = 0
        while (true) {
            val i = i1 + k * di
            val j = j1 + k * dj
            if (i !in 0..<m || j !in 0..<n) break
            if (a[i][j] != '#') {
                a[i][j] = '#'
                ans++
            }
            k++
        }
    }
    println(ans)
}