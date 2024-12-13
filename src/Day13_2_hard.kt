import kotlin.math.absoluteValue
import kotlin.math.sign

/*
   Solving it in a hard way via diaphanous equations.
   This way not needed at all.
 */

data class EgcdResult(val g: Long, val r: Long, val s: Long)

fun egcd(a: Long, b: Long): EgcdResult {
    var oldR = a
    var r = b
    var oldS = 1L
    var s = 0L
    var oldT = 0L
    var t = 1L
    while (r != 0L) {
        val q = oldR / r
        oldR = r.also { r = oldR - q * r }
        oldS = s.also { s = oldS - q * s }
        oldT = t.also { t = oldT - q * t }
    }
    return EgcdResult(oldR, oldS, oldT)
}

data class SQ(val p: Long, val q: Long) // p + q * t

fun find(a: Long, b: Long, c: Long): SQ? {
    val (g, s, _) = egcd(a, b)
    val rc = c % b
    if (rc % g != 0L) return null
    var p0 = s * (rc / g)
    val q0 = b / g
    while (p0 < 0) p0 += q0
    if (p0 > q0) p0 = p0 % q0
    check((c - a * p0) % b == 0L)
    check((c - a * (p0 + q0)) % b == 0L)
    return SQ(p0, q0)
}

fun combine(a: SQ, b: SQ): SQ? {
    val g = egcd(a.q, b.q).g
    if (a.p % g != b.p % g) return null
    var p0 = a.p
    while (p0 % b.q != b.p) {
        p0 += a.q
    }
    val q0 = a.q / g * b.q
    check(p0 % a.q == a.p)
    check(p0 % b.q == b.p)
    return SQ(p0, q0)
}

fun main() {
    var sum = 0L
    val parts = readInput("Day13").parts { it }
    for (it in parts) {
        val (ax, ay) = Regex("Button A: X\\+(\\d+), Y\\+(\\d+)").matchEntire(it[0])!!.destructured.toList().map { it.toLong() }
        val (bx, by) = Regex("Button B: X\\+(\\d+), Y\\+(\\d+)").matchEntire(it[1])!!.destructured.toList().map { it.toLong() }
        val (px, py) = Regex("Prize: X=(\\d+), Y=(\\d+)").matchEntire(it[2])!!.destructured.toList().map { it.toLong() + 10000000000000L }
        val sx = find(ax, bx, px) ?: continue
        val sy = find(ay, by, py) ?: continue
        val sc = combine(sx, sy) ?: continue

        var best = Long.MAX_VALUE
        fun test(a: Long): Boolean {
            val rx = px - ax * a
            val ry = py - ay * a
            if (rx < 0 || ry < 0) return false
            val b = rx / bx
            if (rx == b * bx && ry == b * by) {
                val c = 3 * a + b
                if (c < best) best = c
            }
            return true
        }
        var a = sc.p
        val rx = px - ax * sc.p
        val ry = py - ay * sc.p
        check(rx % bx == 0L)
        check(ry % by == 0L)
        val xb = rx / bx
        val yb = ry / by
        val dxb = ax * sc.q / bx
        val dyb = ay * sc.q / by
        if (xb == yb) {
            println("!! Found on the first try (never happened for me)")
            test(a)
            if (dxb == dyb) {
                println("!!! Multiple solutions")
                test(a + (rx / ax) / sc.q * sc.q)
            }
        } else if (dxb != dyb && (xb - yb).sign == (dxb - dyb).sign) {
            val d = (xb - yb).absoluteValue
            val dd = (dxb - dyb).absoluteValue
            if (d % dd == 0L) test(a + (d /dd) * sc.q)
        }
        if (best < Long.MAX_VALUE) {
            sum += best
        }
    }
    println(sum)
}