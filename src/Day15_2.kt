fun main() {
    val (map, moves) = readInput("Day15").parts { it }
    val a = map.map { it.map {
        when (it) {
            '#' -> "##"
            'O' -> "[]"
            '.' -> ".."
            '@' -> "@."
            else -> error("!$it")
        }
    }.joinToString("") }.toCharArray2()
    var (ri, rj) = a.mapIndexed2NotNull { i, j, c -> if (c == '@') P2(i, j) else null }.single()
    a[ri][rj] = '.'
    for (line in moves) for (c in line) {
        var di = 0
        var dj = 0
        when (c) {
            '<' -> dj = -1
            'v' -> di = 1
            '>' -> dj = 1
            '^' -> di = -1
            else -> error("!$c")
        }
        var k = 1
        if (di == 0) {
            while (a[ri][rj + dj * k] in setOf('[', ']')) k++
            when (a[ri][rj + dj * k]) {
                '#' -> continue
                '.' -> {
                    if (k > 1) {
                        check(k % 2 == 1)
                        for (t in 2..k) {
                            a[ri][rj + dj * t] = if ((t % 2 == 0) == (dj < 0)) ']' else '['
                        }
                        a[ri + di][rj + dj] = '.'
                    }
                    ri += di
                    rj += dj
                }
                else -> error("!!!")
            }
        } else {
            val ms = HashMap<Int, HashSet<Int>>()
            ms[ri] = HashSet(setOf(rj))
            var ci = ri
            var blocked = false
            while (!blocked && ci in ms) {
                for (j in ms[ci]!!) {
                    when (a[ci + di][j]) {
                        '[' -> ms.getOrPut(ci + di) { HashSet() }.addAll(setOf(j, j + 1))
                        ']' -> ms.getOrPut(ci + di) { HashSet() }.addAll(setOf(j - 1, j))
                        '#' -> { blocked = true; break }
                        '.' -> {}
                        else -> error("!!!")
                    }
                }
                ci += di
            }
            if (!blocked) {
                ci -= di
                while (ci in ms) {
                    for (j in ms[ci]!!)  {
                        a[ci + di][j] = a[ci][j]
                        a[ci][j] = '.'
                    }
                    ci -= di
                }
                ri += di
            }
        }
    }
    a.forEach { println(it.concatToString()) }
    var sum = 0L
    a.forEachIndexed2 { i, j, c -> if (c == '[') sum += 100 * i + j  }
    println(sum)
}