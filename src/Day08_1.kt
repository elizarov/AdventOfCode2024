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
    fun node(i: Int, j: Int) {
        if (i in 0..<m && j in 0..<n && a[i][j] != '#') {
            a[i][j] = '#'
            ans++
        }
    }
    for (l in map.values) for (p in 0..<l.size) for (q in p + 1..<l.size) {
        val (i1, j1) = l[p]
        val (i2, j2) = l[q]
        node(2 * i1 - i2, 2 * j1 - j2)
        node(2 * i2 - i1, 2 * j2 - j1)
    }
    println(ans)
}