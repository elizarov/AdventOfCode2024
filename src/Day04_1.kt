fun main() {
    val a = readInput("Day04").toCharArray2()
    val (n, m) = a.size2()
    val t = "XMAS"
    var cnt = 0
    for (i in 0..<n) for (j in 0..<m) {
        for (di in -1..1) for (dj in -1..1) if (di != 0 || dj != 0) {
            var found = true
            for (k in 0..<t.length) {
                val i1 = i + di * k
                val j1 = j + dj * k
                if (i1 !in 0..<n || j1 !in 0..<m || a[i1][j1] != t[k]) {
                    found = false
                    break
                }
            }
            if (found) cnt++
        }
    }
    println(cnt)
}