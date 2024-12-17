fun main() {
    val (_, prog) = readInput("Day17").parts { it }
    val out = prog[0].substringAfter(": ").split(",").map { it.toInt() }.toIntArray()
    var c1 = -1
    var c2 = -1
    // expected structure of the program for which this code works
    val expectOut = "2,4,1,c1,7,5,4,3,0,3,1,c2,5,5,3,0".split(",").mapIndexed { i, s ->
        when (s) {
            "c1" -> { c1 = out[i]; c1 }
            "c2" -> { c2 = out[i]; c2 }
            else -> s.toInt()
        }
    }.toIntArray()
    check(c1 >= 0 && c2 >= 0)
    check(out.contentEquals(expectOut))
    /* -- expected program dump ---
        0: r1 = r0 and 7
        2: r1 = r1 xor c1
        4: r2 = div2(r0, r1)
        6: r1 = r1 xor r2
        8: r0 = div2(r0, 3)
        10: r1 = r1 xor c2
        12: out r1 and 7
        14: if (r0 != 0) goto 0
     */
    val n = out.size * 3
    val bits = IntArray(n + 10)
    val f = BooleanArray(n + 10)
    f.fill(true, n)
    fun scan(k: Int): Boolean {
        if (k == out.size) {
            var ans = 0L
            for (i in 0..<n) {
                ans = ans or (bits[i].toLong() shl i)
            }
            println(ans)
            return true
        }
        val i = 3 * k
        val wf0 = f[i]
        val wf1 = f[i + 1]
        val wf2 = f[i + 2]
        f[i] = true
        f[i + 1] = true
        f[i + 2] = true
        for (j in 0 .. 7) {
            val b0 = j and 1
            val b1 = (j shr 1) and 1
            val b2 = (j shr 2) and 1
            if (wf0 && (b0 != bits[i])) continue
            if (wf1 && (b1 != bits[i + 1])) continue
            if (wf2 && (b2 != bits[i + 2])) continue
            bits[i] = b0
            bits[i + 1] = b1
            bits[i + 2] = b2
            // r1 = bits3[i] xor c1
            // out r1 xor bits3[i + r1] xor c2
            val r1 = j xor c1
            val expect = out[k] xor r1 xor c2
            check(expect in 0..7)
            val e0 = expect and 1
            val e1 = (expect shr 1) and 1
            val e2 = (expect shr 2) and 1
            val zf0 = f[i + r1]
            val zf1 = f[i + r1 + 1]
            val zf2 = f[i + r1 + 2]
            if (zf0 && (e0 != bits[i + r1])) continue
            if (zf1 && (e1 != bits[i + r1 + 1])) continue
            if (zf2 && (e2 != bits[i + r1 + 2])) continue
            f[i + r1] = true
            f[i + r1 + 1] = true
            f[i + r1 + 2] = true
            bits[i + r1] = e0
            bits[i + r1 + 1] = e1
            bits[i + r1 + 2] = e2
            if (scan(k + 1)) return true
            f[i + r1] = zf0
            f[i + r1 + 1] = zf1
            f[i + r1 + 2] = zf2
        }
        f[i] = wf0
        f[i + 1] = wf1
        f[i + 2] = wf2
        return false
    }
    scan(0)
}