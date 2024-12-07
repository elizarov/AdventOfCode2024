fun main() {
    val a = readInput("Day06").toCharArray2()
    val (n, m) = a.size2()
    var ii = -1
    var jj = -1
    for (i in 0..<n) for (j in 0..<m) if (a[i][j] == '^') {
        ii = i
        jj = j
    }
    val (di, dj) = RDLU_DIRS
    val v = Array(4) { Array(n) { BooleanArray(m) } }
    var cnt = 0
    for (ib in 0..<n) for (jb in 0..<m) {
        v.forEach { w -> w.forEach { it.fill(false) } }
        var i0 = ii
        var j0 = jj
        var d0 = 3
        var found = true
        while (!v[d0][i0][j0]) {
            v[d0][i0][j0] = true
            val i1 = i0 + di[d0]
            val j1 = j0 + dj[d0]
            if (i1 !in 0..<n || j1 !in 0..<m) {
                found = false
                break
            }
            if (a[i1][j1] == '#' || (i1 == ib && j1 == jb)) {
                d0 = (d0 + 1) % 4
                continue
            }
            i0 = i1
            j0 = j1
        }
        if (found) cnt++
    }
    println(cnt)
}