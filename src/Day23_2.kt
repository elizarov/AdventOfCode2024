fun main() {
    val input = readInput("Day23").map { it.split('-', limit = 2) }
    val g = HashMap<String, HashSet<String>>()
    fun put(a: String, b: String) {
        g.getOrPut(a) { HashSet() }.add(b)
    }
    for ((a, b) in input) {
        put(a, b)
        put(b, a)
    }
    val l = g.keys.sorted()
    val cur = Array(l.size) { "" }
    var best = emptyArray<String>()
    fun find(i0: Int, n: Int) {
        if (n > best.size) {
            best = cur.copyOfRange(0, n)
        }
        loop@ for (i in i0..<l.size) {
            val si = l[i]
            val ss = g[si]!!
            for (j in 0..<n) {
                val sj = cur[j]
                if (sj !in ss) continue@loop
            }
            cur[n] = si
            find(i + 1, n + 1)
        }
    }
    find(0, 0)
    println(best.joinToString(","))
}