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
    val l = g.keys.toList()
    var cnt = 0
    for (i in 0..<l.size) {
        val si = l[i]
        for (j in i + 1..<l.size) {
            val sj = l[j]
            if (sj in g[si]!!) {
                for (k in j + 1..<l.size) {
                    val sk = l[k]
                    if (sj in g[sk]!! && si in g[sk]!!) {
                        if (si.startsWith("t") ||  sj.startsWith("t") || sk.startsWith("t")) {
                            cnt++
                        }
                    }
                }
            }
        }
    }
    println(cnt)
}