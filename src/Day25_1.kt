fun main() {
    val input = readInput("Day25").parts { it.toCharArray2() }
    val (n, m) = input[0].size2()
    val (ls0, ks0) = input.partition { a ->
        (0..<m).all { j -> a[0][j] == '#' }
    }
    val ls = ls0.map { a ->
        Array(m) { j ->
            (1..n).first { i -> a[i][j] != '#' }
        }
    }
    val ks = ks0.map { a ->
        Array(m) { j ->
            (1..n).first { i -> a[n - i - 1][j] != '#' }
        }
    }
    var sum = 0
    for (l in ls) for (k in ks) {
        if ((0..<m).all { j -> l[j] + k[j] <= n }) sum++
    }
    println(sum)
}