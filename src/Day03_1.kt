fun main() {
    val input = readInput("Day03")
    val re = Regex("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)")
    var sum = 0L
    for (s in input) {
        for (match in re.findAll(s)) {
            val (a, b) = match.destructured
            sum += a.toInt() * b.toInt()
        }
    }
    println(sum)
}