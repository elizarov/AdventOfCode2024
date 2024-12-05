fun main() {
    val (rulesL, pages) = readInput("Day05").parts { it }
    val rules = rulesL.map {
        val (a, b) = it.split("|").map { it.toInt() }
        Pair(a, b)
    }.toSet()
    check(rulesL.size == rules.size)
    var sum = 0L
    for (p in pages) {
        val a = p.split(",").map { it.toInt() }
        var ok = true
        for (i in 0..<a.size) for (j in i + 1..<a.size) {
            if (Pair(a[j], a[i]) in rules) ok = false
        }
        if (ok) {
            sum += a[a.size / 2]
        }
    }
    println(sum)
}