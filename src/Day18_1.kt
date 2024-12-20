fun main() {
    val a = readInput("Day18").map {
        val (i, j) = it.split(",").map { it.toInt() }
        P2(i, j)
    }
    val n = 71
    val count = 1024
    val c = Array(n) { BooleanArray(n) }
    for ((i, j) in a.take(count)) {
        c[i][j] = true
    }
    val dist = mazeDistances(n, n, 0, 0) { i, j, _ -> !c[i][j] }
    println(dist[n - 1][n - 1])
}