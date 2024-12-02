import kotlin.math.abs

fun main() {
    val input = readInput("Day02")
    var cnt = 0
    for (r in input) {
        val a = r.split(" ").map { it.toInt() }
        var ok = isSafeLevel(a)
        if (!ok) {
            for (i in a.indices) {
                val b = ArrayList(a)
                b.removeAt(i)
                if (isSafeLevel(b)) {
                    ok = true
                    break
                }
            }
        }
        if (ok) {
            cnt++
        }
    }
    println(cnt)
}

private fun isSafeLevel(a: List<Int>): Boolean {
    var d = 0
    var ok = true
    for (i in 0..<a.size - 1) {
        val dd = when {
            a[i + 1] > a[i] -> 1
            a[i + 1] < a[i] -> -1
            else -> 0
        }
        if (dd == 0 || d != 0 && dd != d) {
            ok = false
            break
        }
        d = dd
        val ad = abs(a[i + 1] - a[i])
        if (ad !in 1..3) {
            ok = false
            break
        }
    }
    return ok
}