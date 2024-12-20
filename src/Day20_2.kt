import kotlin.math.*

fun main() {
    val a = readInput("Day20").toCharArray2()
    val (m, n) = a.size2()
    val (si, sj) = a.mapIndexed2NotNull { i, j, c -> if (c == 'S') P2(i, j) else null }.single()
    val (ei, ej) = a.mapIndexed2NotNull { i, j, c -> if (c == 'E') P2(i, j) else null }.single()
    val t = 100
    val inf = Int.MAX_VALUE / 3
    val q = ArrayList<P2>()
    val (di, dj) = RDLU_DIRS
    val ds = Array(n) { IntArray(m) { inf } }
    q += P2(si, sj)
    ds[si][sj] = 0
    var h = 0
    while (h < q.size) {
        val (i, j) = q[h++]
        val d = ds[i][j]
        for (k in 0..3) {
            val i1 = i + di[k]
            val j1 = j + dj[k]
            if (i1 in 0..<n && j1 in 0..<m && a[i1][j1] != '#' && ds[i1][j1] == inf) {
                ds[i1][j1] = d + 1
                q += P2(i1, j1)
            }
        }
    }
    val de = Array(n) { IntArray(m) { inf } }
    q.clear()
    q += P2(ei, ej)
    de[ei][ej] = 0
    h = 0
    while (h < q.size) {
        val (i, j) = q[h++]
        val d = de[i][j]
        for (k in 0..3) {
            val i1 = i + di[k]
            val j1 = j + dj[k]
            if (i1 in 0..<n && j1 in 0..<m && a[i1][j1] != '#' && de[i1][j1] == inf) {
                de[i1][j1] = d + 1
                q += P2(i1, j1)
            }
        }
    }
    val d0 = ds[ei][ej]
    check(de[si][sj] == d0)
    var cnt = 0
    val z = 20
    for (i in 0..<n) for (j in 0..<m) {
        for (di in -z..z) for (dj in -z..z) if (abs(di) + abs(dj) <= z) {
            val i1 = i + di
            val j1 = j + dj
            if (i1 in 0..<n && j1 in 0..<m) {
                if (ds[i][j] + de[i1][j1] + abs(di) + abs(dj) <= d0 - t) {
                    cnt++
                }
            }
        }
    }
    println(cnt)
}