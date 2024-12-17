fun main() {
    val (regs, prog) = readInput("Day17").parts { it }
    val r = regs.map { s ->
        Regex("Register [A-C]: (\\d+)").matchEntire(s)!!.groupValues[1].toLong()
    }.toLongArray()
    val p = prog[0].substringAfter(": ").split(",").map { it.toInt() }.toIntArray()
    var ip = 0
    fun combo(): Long {
        val v = p[ip + 1]
        return when (v) {
            in 0..3 -> v.toLong()
            in 4..6 -> r[v - 4]
            else -> error("v=$v")
        }
    }
    fun div2(a: Long, b: Long): Long {
        if (b > 31) return 0
        return a shr b.toInt()
    }
    for (aa in 0L..Long.MAX_VALUE) {
        if (aa % 10000 == 0L) {
            println("$aa ...")
        }
        ip = 0
        r[0] = aa
        r[1] = 0
        r[2] = 0
        var outIndex = 0
        var ok = true
        while (ip < p.size) {
            when (p[ip]) {
                0 -> r[0] = div2(r[0], combo())
                1 -> r[1] = r[1] xor (p[ip + 1].toLong())
                2 -> r[1] = combo() and 7
                3 -> if (r[0] != 0L) {
                    ip = p[ip + 1]; continue
                }
                4 -> r[1] = r[1] xor r[2]
                5 -> {
                    val v = (combo() and 7).toInt()
                    if (outIndex >= p.size || p[outIndex] != v) {
                        ok = false
                        break
                    }
                    outIndex++
                }
                6 -> r[1] = div2(r[0], combo())
                7 -> r[2] = div2(r[0], combo())
            }
            ip += 2
        }
        ok = ok && outIndex == p.size
        if (ok) {
            println(aa)
            break
        }
    }
}