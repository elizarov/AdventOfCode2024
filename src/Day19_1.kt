fun main() {
    val (dss, towels) = readInput("Day19").parts { it }
    val ds = dss[0].split(", ")
    fun possible(s: String, i: Int, dp: BooleanArray): Boolean {
        if (i >= s.length) return true
        if (dp[i]) return false
        for (d in ds) {
            if (i + d.length <= s.length && s.substring(i, i + d.length) == d) {
                if (possible(s, i + d.length, dp)) return true
            }
        }
        dp[i] = true
        return false
    }
    fun possible(s: String): Boolean {
        val dp = BooleanArray(s.length)
        return possible(s, 0, dp)
    }
    val cnt = towels.count { possible(it) }
    println(cnt)
}