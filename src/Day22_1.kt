fun main() {
    val a = readInput("Day22").map { it.toInt() }
    val mod = 16777216
    val sum = a.sumOf { n ->
        var cur = n
        repeat(2000) {
            cur = (cur * 64 xor cur).mod(mod)
            cur = (cur / 32 xor cur).mod(mod)
            cur = (cur * 2048 xor cur).mod(mod)
        }
        cur.toLong()
    }
    println(sum)
}