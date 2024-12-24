fun main() {
    val (start, codeStr) = readInput("Day24").parts { it }
    val cur: HashMap<String, Int> = start.map {
        val (name, value) = it.split(": ")
        name to value.toInt()
    }.toMap(HashMap())
    data class C(val a: String, val op: String, val b: String, val dest: String)
    val code = codeStr.map {
        val (a, op, b, dest) = Regex("(\\w+) (\\w+) (\\w+) -> (\\w+)").matchEntire(it)!!.destructured
        C(a, op, b, dest)
    }
    val rem = code.toHashSet()
    while (rem.isNotEmpty()) {
        val it = rem.iterator()
        while (it.hasNext()) {
            val c = it.next()
            val av = cur[c.a] ?: continue
            val bv = cur[c.b] ?: continue
            it.remove()
            val res = when (c.op) {
                "AND" -> av and bv
                "OR" -> av or bv
                "XOR" -> av xor bv
                else -> error(c.op)
            }
            cur[c.dest] = res
        }
    }
    var i = 0
    var ans = 0L
    while (true) {
        val name = "z" + i.toString().padStart(2, '0')
        val iv =  cur[name] ?: break
        ans = ans or (iv.toLong() shl i)
        i++
    }
    println(ans)
}