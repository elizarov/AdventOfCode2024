fun main() {
    val a = readInput("Day10").toCharArray2()
    val (n, m) = a.size2()
    var sum = 0L
    for (i0 in 0..<n) for (j0 in 0..<m) if (a[i0][j0] == '0') {
        val d = mazeDistances(n, m, i0, j0) { i, j, d ->
            val ok = a[i][j] == '0' + d
            if (ok && d == 9) sum++
            ok
        }
    }
    println(sum)
}