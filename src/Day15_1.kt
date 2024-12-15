fun main() {
    val (map, moves) = readInput("Day15").parts { it }
    val a = map.toCharArray2()
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
        while (a[ri + di * k][rj + dj * k] == 'O') k++
        when (a[ri + di * k][rj + dj * k]) {
            '#' -> continue
            '.' -> {
                if (k > 1) {
                    a[ri + di * k][rj + dj * k] = 'O'
                    a[ri + di][rj + dj] = '.'
                }
                ri += di
                rj += dj
            }
            else -> error("!!!")
        }
    }
    a.forEach { println(it.concatToString()) }
    var sum = 0L
    a.forEachIndexed2 { i, j, c -> if (c == 'O') sum += 100 * i + j  }
    println(sum)
}