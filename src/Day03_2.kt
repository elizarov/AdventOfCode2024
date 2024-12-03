fun main() {
    val input = readInput("Day03")
    val re = Regex("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)|do\\(\\)|don't\\(\\)")
    var sum = 0L
    var on = true
    for (s in input) {
        for (match in re.findAll(s)) {
            when (match.value) {
                "do()" -> on = true
                "don't()" -> on = false
                else -> if (on) {
                    val (a, b) = match.destructured
                    sum += a.toInt() * b.toInt()
                }
            }
        }
    }
    println(sum)
}