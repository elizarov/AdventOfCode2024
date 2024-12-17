fun main() {
    val (regs, prog) = readInput("Day17").parts { it }
    val p = prog[0].substringAfter(": ").split(",").map { it.toInt() }.toIntArray()
    for (ip in 0..<p.size step 2) {
        fun combo(): String {
            val v = p[ip + 1]
            return when (v) {
                in 0..3 -> v.toString()
                in 4..6 -> "r${v - 4}"
                else -> error("v=$v")
            }
        }
        when (p[ip]) {
            0 -> println("$ip: r0 = div2(r0, ${combo()})")
            1 -> println("$ip: r1 = r1 xor ${p[ip + 1]}")
            2 -> println("$ip: r1 = ${combo()} and 7")
            3 -> println("$ip: if (r0 != 0) goto ${p[ip + 1]}")
            4 -> println("$ip: r1 = r1 xor r2")
            5 -> println("$ip: out ${combo()} and 7")
            6 -> println("$ip: r1 = div2(r0, ${combo()})")
            7 -> println("$ip: r2 = div2(r0, ${combo()})")
        }
    }
}