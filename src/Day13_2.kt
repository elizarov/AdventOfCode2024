import java.math.BigInteger

fun main() {
    var sum = 0L
    val parts = readInput("Day13").parts { it }
    for (it in parts) {
        val (ax, ay) = Regex("Button A: X\\+(\\d+), Y\\+(\\d+)").matchEntire(it[0])!!.destructured.toList().map { it.toBigInteger() }
        val (bx, by) = Regex("Button B: X\\+(\\d+), Y\\+(\\d+)").matchEntire(it[1])!!.destructured.toList().map { it.toBigInteger() }
        val (px, py) = Regex("Prize: X=(\\d+), Y=(\\d+)").matchEntire(it[2])!!.destructured.toList().map { (it.toLong() + 10000000000000L).toBigInteger() }
        // ax * a + bx * b = px
        // ay * a + by * b = py
        var det = ax * by - ay * bx
        val zero = BigInteger.ZERO
        val detSign = det.signum().toBigInteger()
        check(det != zero) { "Never happened in my data" }
        det *= detSign // ensures det > 0
        val na = (px * by - py * bx) * detSign
        val nb = (ax * py - ay * px) * detSign
        if (na.mod(det) == zero && nb.mod(det) == zero && na > zero && nb > zero) {
            val a = (na / det).toLong()
            val b = (nb / det).toLong()
            sum += 3 * a + b
        }
    }
    println(sum)
}