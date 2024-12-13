fun main() {
    var sum = 0L
    readInput("Day13").parts { it }.forEach {
        val (ax, ay) = Regex("Button A: X\\+(\\d+), Y\\+(\\d+)").matchEntire(it[0])!!.destructured.toList().map { it.toInt() }
        val (bx, by) = Regex("Button B: X\\+(\\d+), Y\\+(\\d+)").matchEntire(it[1])!!.destructured.toList().map { it.toInt() }
        val (px, py) = Regex("Prize: X=(\\d+), Y=(\\d+)").matchEntire(it[2])!!.destructured.toList().map { it.toInt() }
        var best = Int.MAX_VALUE
        for (a in 0..px / ax) {
            val rx = px - ax * a
            val ry = py - ay * a
            if (rx >= 0 && ry >= 0) {
                val b = rx / bx
                if (rx == b * bx && ry == b * by) {
                    val c = 3 * a + b
                    if (c < best) best = c
                }
            }
        }
        if (best < Int.MAX_VALUE) sum += best
        Unit
    }
    println(sum)
}