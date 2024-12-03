fun main() {
    val input = readInput("Day03")
    var sum = 0L
    var on = true
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
        while (i < s.length) {
            if (i + 4 <= s.length && s.substring(i, i + 4) == "do()") {
                on = true
                i += 4
                continue
            }
            if (i + 7 <= s.length && s.substring(i, i + 7) == "don't()") {
                on = false
                i += 7
                continue
            }
            if (i + 4 <= s.length && s.substring(i, i + 4) == "mul(") {
                i += 4
                val a = nextNum()
                if (i >= s.length || s[i] != ',') continue
                i++
                val b = nextNum()
                if (i >= s.length || s[i] != ')') continue
                if (on) {
                    sum += a * b
                }
                continue
            }
            i++
        }
    }
    println(sum)
}