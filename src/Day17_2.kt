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
    // k -- current output index (bit index == 3*k)
    // b -- current bits
    // f -- current "fixed" bits (do not change anymore)
    fun scan(k: Int, b: Long, f: Long): Boolean {
        if (k == out.size) {
            println(b)
            return true
        }
        val i = 3 * k // bit index
        val b3 = ((b shr i) and 7).toInt()
        val f3 = ((f shr i) and 7).toInt()
        for (j in 0 .. 7) {
            if (b3 != (f3 and j)) continue
            val bu = b or (j.toLong() shl i)
            val fu = f or (7L shl i)
            // r1 = bits3[i] xor c1
            // out r1 xor bits3[i + r1] xor c2
            val r1 = j xor c1
            val expect = out[k] xor r1 xor c2
            check(expect in 0..7)
            val eb3 = ((bu shr (i + r1)) and 7).toInt()
            val ef3 = ((fu shr (i + r1)) and 7).toInt()
            if (eb3 != (ef3 and expect)) continue
            val bb = bu or (expect.toLong() shl (i + r1))
            val ff = fu or (7L shl (i + r1))
            if (scan(k + 1, bb, ff)) return true
        }
        return false
    }
    scan(0, 0L, ((1L shl n) - 1).inv())
}