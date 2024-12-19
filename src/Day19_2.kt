fun main() {
    val (dss, towels) = readInput("Day19").parts { it }
    val ds = dss[0].split(", ")
    fun count(s: String, i: Int, dp: LongArray): Long {
        if (i >= s.length) return 1L
        if (dp[i] >= 0) return dp[i]
        var cnt = 0L
        for (d in ds) {
            if (i + d.length <= s.length && s.substring(i, i + d.length) == d) {
                cnt += count(s, i + d.length, dp)
            }
        }
        dp[i] = cnt
        return cnt
    }
    fun count(s: String): Long {
        val dp = LongArray(s.length) { -1 }
        return count(s, 0, dp)
    }
    val cnt = towels.sumOf { count(it) }
    println(cnt)
}