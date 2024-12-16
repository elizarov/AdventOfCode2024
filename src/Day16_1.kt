import java.util.*

fun main() {
    val a = readInput("Day16").toCharArray2()
    val (m, n) = a.size2()
    val (ri, rj) = a.mapIndexed2NotNull { i, j, c -> if (c == 'S') P2(i, j) else null }.single()
    data class Q(val i: Int, val j: Int, val d: Int, val c: Int) : Comparable<Q> {
        override fun compareTo(other: Q): Int = c - other.c
    }
    val (di, dj) = RDLU_DIRS
    val q = PriorityQueue<Q>()
    val cost = Array(m) { Array(n) { IntArray(4) { Int.MAX_VALUE }  } }
    val f = Array(m) { Array(n) { BooleanArray(4)  } }
    fun enq(i: Int, j: Int, d: Int, c: Int) {
        if (cost[i][j][d] <= c) return
        if (a[i][j] == '#') return
        cost[i][j][d] = c
        q.add(Q(i, j, d, c))
    }
    enq(ri, rj, 0, 0)
    while (true) {
        val (i, j, d, c) = q.remove()!!
        if (f[i][j][d]) continue
        f[i][j][d] = true
        if (a[i][j] == 'E') {
            println(c)
            break
        }
        enq(i + di[d], j + dj[d], d, c + 1)
        enq(i, j, (d + 1) % 4, c + 1000)
        enq(i, j, (d + 3) % 4, c + 1000)
    }
}