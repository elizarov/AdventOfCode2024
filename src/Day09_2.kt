import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val input = readInput("Day09")
    val s = input[0]
    val m = s.sumOf { it.digitToInt() }
    val a = IntArray(m)
    var cur = 0
    data class BD(val j: Int, val k: Int): Comparable<BD> {
        override fun compareTo(other: BD): Int = j - other.j
    }
    val files = ArrayList<BD>()
    val space = Array(10) { TreeSet<BD>() }
    for (i in s.indices) {
        val k = s[i].digitToInt()
        val t = if (i % 2 == 0) {
            files += BD(cur, k)
            i / 2
        } else {
            if (k > 0) space[k].add(BD(cur, k))
            -1
        }
        repeat(k) { a[cur++] = t }
    }
    check(cur == m)
    for ((fj, fl) in files.reversed()) {
        var best: BD? = null
        for (k in fl..9) {
            if (space[k].isNotEmpty() && (best == null || space[k].first().j < best.j)) best = space[k].first()
        }
        if (best == null) continue
        val (sj, sl) = best
        if (sj > fj) continue
        for (k in 0..<fl) {
            a[fj + k] = a[sj + k].also { a[sj + k] = a[fj + k] }
        }
        space[sl].remove(best)
        val rem = sl - fl
        if (rem > 0) space[rem] += BD(sj + fl, rem)
    }
    var sum = 0L
    for (j in 0..<m) {
        if (a[j] != -1) sum += a[j] * j
    }
    println(sum)
}