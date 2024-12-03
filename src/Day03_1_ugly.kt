fun main() {
    val input = readInput("Day03")
    var sum = 0L
    for (s in input) {
        var i = 0
        fun nextNum(): Int {
            var j = i
            var num = 0
            while (j < s.length && j < i + 3 && s[j] in '0'..'9') {
                num = num * 10 + (s[j] - '0')
                j++
            }
            i = j
            return num
        }
        while (true) {
            i = s.indexOf("mul(", i)
            if (i < 0) break
            i += 4
            val a = nextNum()
            if (s[i] != ',') continue
            i++
            val b = nextNum()
            if (s[i] != ')') continue
            sum += a * b
        }
    }
    println(sum)
}