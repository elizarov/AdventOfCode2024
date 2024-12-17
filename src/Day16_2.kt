import java.util.*

fun main() {
    val a = readInput("Day16").toCharArray2()
    val (m, n) = a.size2()
    val (ri, rj) = a.mapIndexed2NotNull { i, j, c -> if (c == 'S') P2(i, j) else null }.single()
    data class Q(val i: Int, val j: Int, val d: Int, val c: Int) : Comparable<Q> {
        override fun compareTo(other: Q): Int = c - other.c
    }
    val (di, dj) = RDLU_DIRS
    val pq = PriorityQueue<Q>()
    val cost = Array(m) { Array(n) { IntArray(4) { Int.MAX_VALUE }  } }
    val f = Array(m) { Array(n) { BooleanArray(4)  } }
    fun enqDist(i: Int, j: Int, d: Int, c: Int) {
        if (cost[i][j][d] <= c) return
        if (a[i][j] == '#') return
        cost[i][j][d] = c
        pq.add(Q(i, j, d, c))
    }
    enqDist(ri, rj, 0, 0)
    var best = Int.MAX_VALUE
    while (pq.isNotEmpty()) {
        val (i, j, d, c) = pq.remove()!!
        if (f[i][j][d]) continue
        f[i][j][d] = true
        if (a[i][j] == 'E') {
            best = minOf(best, c)
        }
        enqDist(i + di[d], j + dj[d], d, c + 1)
        enqDist(i, j, (d + 1) % 4, c + 1000)
        enqDist(i, j, (d + 3) % 4, c + 1000)
    }

    val (ei, ej) = a.mapIndexed2NotNull { i, j, c -> if (c == 'E') P2(i, j) else null }.single()
    val u = Array(m) { BooleanArray(n) }
    val q = ArrayList<Q>()
    var h = 0
    var cnt = 0
    fun enq(i: Int, j: Int, d: Int, c: Int) {
        if (cost[i][j][d] != c) return
        if (!u[i][j]) {
            u[i][j] = true
            cnt++
        }
        q += Q(i, j, d, c)
    }
    for (d in 0..3) if (cost[ei][ej][d] == best) enq(ei, ej, d, best)
    while (h < q.size) {
        val (i, j, d, c) = q[h++]
        enq(i - di[d], j - dj[d], d, c - 1)
        enq(i, j, (d + 1) % 4, c - 1000)
        enq(i, j, (d + 3) % 4, c - 1000)
    }
    println(cnt)
}