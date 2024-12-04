fun main() {
    val a = readInput("Day04").toCharArray2()
    val (n, m) = a.size2()
    val ms = setOf('M', 'S')
    var cnt = 0
    for (i in 1..<n - 1) for (j in 1..<m - 1) {
        if (a[i][j] != 'A') continue
        val c1 = a[i - 1][j - 1]
        val c2 = a[i + 1][j + 1]
        if (setOf(c1, c2) != ms) continue
        val c3 = a[i + 1][j - 1]
        val c4 = a[i - 1][j + 1]
        if (setOf(c3, c4) != ms) continue
        cnt++
    }
    println(cnt)
}