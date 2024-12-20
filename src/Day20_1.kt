import kotlin.math.*

fun main() {
    val a = readInput("Day20").toCharArray2()
    val (m, n) = a.size2()
    val (si, sj) = a.mapIndexed2NotNull { i, j, c -> if (c == 'S') P2(i, j) else null }.single()
    val (ei, ej) = a.mapIndexed2NotNull { i, j, c -> if (c == 'E') P2(i, j) else null }.single()
    val t = 100
    val ds = mazeDistances(n, m, si, sj) { i, j, _ -> a[i][j] != '#' }
    val de = mazeDistances(n, m, ei, ej) { i, j, _ -> a[i][j] != '#' }
    val d0 = ds[ei][ej]
    check(de[si][sj] == d0)
    var cnt = 0
    for (i in 0..<n) for (j in 0..<m) {
        for (di in -2..2) for (dj in -2..2) if (abs(di) + abs(dj) == 2) {
            val i1 = i + di
            val j1 = j + dj
            if (i1 in 0..<n && j1 in 0..<m) {
                if (ds[i][j] + de[i1][j1] + 2 <= d0 - t) {
                    cnt++
                }
            }
        }
    }
    println(cnt)
}