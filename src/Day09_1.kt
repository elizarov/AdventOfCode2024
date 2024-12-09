fun main() {
    val input = readInput("Day09")
    val s = input[0]
    val m = s.sumOf { it.digitToInt() }
    val a = IntArray(m)
    var cur = 0
    for (i in s.indices) {
        val k = s[i].digitToInt()
        val t = if (i % 2 == 0) i / 2 else -1
        repeat(k) { a[cur++] = t }
    }
    check(cur == m)
    val space = ArrayDeque<Int>()
    for (j in 0..<m) {
        if (a[j] == -1) space.add(j)
    }
    for (j in m - 1 downTo 0) {
        if (a[j] == -1) continue
        if (space.isEmpty()) break
        val k = space.removeFirst()
        if (k > j) break
        a[j] = a[k].also { a[k] = a[j] }
    }
    var sum = 0L
    for (j in 0..<m) {
        if (a[j] != -1) sum += a[j] * j
    }
    println(sum)
}