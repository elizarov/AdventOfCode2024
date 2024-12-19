fun main() {
    val (dss, towels) = readInput("Day19").parts { it }
    val ds = dss[0].split(", ")
    fun possible(s: String): Boolean {
        val n = s.length
        val dp = BooleanArray(n + 1)
        dp[n] = true
        for (i in n - 1 downTo 0) {
            dp[i] = ds.any { t -> s.startsWith(t, i) && dp[i + t.length] }
        }
        return dp[0]
    }
    val cnt = towels.count { possible(it) }
    println(cnt)
}