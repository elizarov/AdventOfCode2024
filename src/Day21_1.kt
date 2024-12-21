import kotlin.math.abs

fun main() {
    val input = readInput("Day21")

    data class Pad(val a: CharArray2) {
        val s = a.mapIndexed2NotNull { i, j, c -> if (c == 'A') P2(i, j) else null }.single()
        val g = a.mapIndexed2NotNull { i, j, c -> if (c == ' ') P2(i, j) else null }.single()
    }

    val numPad = Pad(
        arrayOf(
            "789".toCharArray(),
            "456".toCharArray(),
            "123".toCharArray(),
            " 0A".toCharArray()
        )
    )
    val dirPad = Pad(
        arrayOf(
            " ^A".toCharArray(),
            "<v>".toCharArray()
        )
    )
    val pads = arrayOf(numPad, dirPad, dirPad)
    fun find(code: String, p: Int): Int {
        check(code.endsWith("A"))
        if (p == pads.size) {
            return code.length
        }
        val pad = pads[p]
        var len = 0
        var i0 = pad.s.i
        var j0 = pad.s.j
        for (tc in code) {
            val (i1, j1) = pad.a.mapIndexed2NotNull { i, j, c -> if (c == tc) P2(i, j) else null }.single()
            val im = i1 - i0
            val jm = j1 - j0
            val ud = (if (im > 0) "v" else "^").repeat(abs(im))
            val lr = (if (jm > 0) ">" else "<").repeat(abs(jm))
            val s1 = ud + lr + "A"
            val s2 = lr + ud + "A"
            val c1 = P2(i1, j0)
            val c2 = P2(i0, j1)
            val best = minOf(
                if (c1 != pad.g) find(s1, p + 1) else Int.MAX_VALUE,
                if (c2 != pad.g) find(s2, p + 1) else Int.MAX_VALUE
            )
            i0 = i1
            j0 = j1
            len += best
        }
        return len
    }

    val sum = input.sumOf { code -> find(code, 0) * code.dropLast(1).toInt() }
    println(sum)
}
