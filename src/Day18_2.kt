fun main() {
    val a = readInput("Day18").map {
        val (i, j) = it.split(",").map { it.toInt() }
        P2(i, j)
    }
    val n = 71
    val c = Array(n) { BooleanArray(n) }
    for ((ic, jc) in a) {
        c[ic][jc] = true
        val inf = Int.MAX_VALUE
        val dist = mazeDistances(n, n, 0, 0, inf) { i, j, _ -> !c[i][j] }
        if (dist[n - 1][n - 1] == inf) {
            println("$ic,$jc")
            break
        }
    }
}