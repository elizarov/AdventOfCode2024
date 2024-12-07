fun main() {
    val a = readInput("Day06").toCharArray2()
    val (n, m) = a.size2()
    val v = Array(n) { BooleanArray(m) }
    var i0 = -1
    var j0 = -1
    for (i in 0..<n) for (j in 0..<m) if (a[i][j] == '^') {
        i0 = i
        j0 = j
    }
    val (di, dj) = RDLU_DIRS
    var d0 = 3
    var cnt = 0
    while (true) {
        if (!v[i0][j0]) cnt++
        v[i0][j0] = true
        val i1 = i0 + di[d0]
        val j1 = j0 + dj[d0]
        if (i1 !in 0..<n || j1 !in 0..<m) break
        if (a[i1][j1] == '#') {
            d0 = (d0 + 1) % 4
            continue
        }
        i0 = i1
        j0 = j1
        a[i0][j0] = 'X'
    }
    println(cnt)
}