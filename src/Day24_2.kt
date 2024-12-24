fun main() {
    val (_, codeStr) = readInput("Day24").parts { it }
    val expectedSwaps = 4
    data class C(val a: String, val op: String, val b: String, val dest: String)
    val code = codeStr.map {
        val (a, op, b, dest) = Regex("(\\w+) (\\w+) (\\w+) -> (\\w+)").matchEntire(it)!!.destructured
        C(a, op, b, dest)
    }.toTypedArray()
    val nextIndices: Map<String, List<Int>> = code.withIndex()
        .flatMap { (index, c) -> listOf(c.a to index, c.b to index) }
        .groupBy { it.first }
        .mapValues { it.value.map { it.second } }
    data class NCRes(val nextCarry: Map<Int, Map<String, Int>>, val nextUsed: Set<Int>)
    fun computeNextCarry(i: Int, carry: Map<Int, Map<String, Int>>): NCRes? {
        val si = i.toString().padStart(2, '0')
        val xi = "x$si"
        val yi = "y$si"
        val zi = "z$si"
        val nextCarry = HashMap<Int, HashMap<String, Int>>()
        val nextUsed = HashSet<Int>()
        for (cv in 0..1) {
            val carryMap = carry[cv] ?: continue
            for (xv in 0..1) for (yv in 0..1) {
                val cur = carryMap.toMap(HashMap())
                cur[xi] = xv
                cur[yi] = yv
                val remCodeIndices = HashSet<Int>()
                fun addNext(v: String) { nextIndices[v]?.let { remCodeIndices.addAll(it) } }
                for (v in cur.keys) addNext(v)
                while (remCodeIndices.isNotEmpty()) {
                    var changes = false
                    for (codeIndex in remCodeIndices.toList()) {
                        val c = code[codeIndex]
                        val av = cur[c.a] ?: continue
                        val bv = cur[c.b] ?: continue
                        remCodeIndices.remove(codeIndex)
                        val curRes = when (c.op) {
                            "AND" -> av and bv
                            "OR" -> av or bv
                            "XOR" -> av xor bv
                            else -> error(c.op)
                        }
                        if (c.dest in cur) {
                            check(cur[c.dest] == curRes)
                        } else {
                            cur[c.dest] = curRes
                            nextUsed += codeIndex
                            addNext(c.dest)
                            changes = true
                        }
                    }
                    if (!changes) break
                }
                val zv = cur[zi] ?: return null
                val sum = xv + yv + cv
                if (zv != (sum and 1)) return null
                val nc = sum shr 1
                val prev = nextCarry[nc]
                if (prev != null) {
                    for ((key, prevVal) in prev) {
                        val curVal = cur[key]!!
                        if (curVal != prevVal) cur.remove(key)
                    }
                }
                nextCarry[nc] = cur
            }
        }
        return NCRes(nextCarry, nextUsed)
    }

    val names = code.flatMap { c -> listOf(c.a, c.b, c.dest) }.toSet()
    val bitCount = (0..64).first { i -> ("x" + i.toString().padStart(2, '0')) !in names }
    val swapped = BooleanArray(code.size)
    val used = BooleanArray(code.size)

    fun scan(i: Int, sc: Int, carry: Map<Int, Map<String, Int>>): Boolean {
        if (i == bitCount) {
            check(sc == expectedSwaps)
            return true
        }
        val res0 = computeNextCarry(i, carry)
        if (res0 != null) {
            for (j in res0.nextUsed) used[j] = true
            if (scan(i + 1, sc, res0.nextCarry)) return true
            for (j in res0.nextUsed) used[j] = false
        }
        for (p in 0..<code.size) if (!used[p]) for (q in p + 1..<code.size) if (!used[q]) {
            val cp = code[p]
            val cq = code[q]
            check(cp.dest != cq.dest)
            used[p] = true
            used[q] = true
            swapped[p] = true
            swapped[q] = true
            code[p] = cp.copy(dest = cq.dest)
            code[q] = cq.copy(dest = cp.dest)
            val next1 = computeNextCarry(i, carry)
            if (next1 != null) {
                for (j in next1.nextUsed) used[j] = true
                if (scan(i + 1, sc + 1, next1.nextCarry)) return true
                for (j in next1.nextUsed) used[j] = false
            }
            used[p] = false
            used[q] = false
            swapped[p] = false
            swapped[q] = false
            code[p] = cp
            code[q] = cq
        }
        return false
    }
    check(scan(0, 0, mapOf(0 to emptyMap())))
    val res = ArrayList<String>()
    for (i in code.indices) if (swapped[i]) res += code[i].dest
    res.sort()
    println(res.joinToString(","))
}