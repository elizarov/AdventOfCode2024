fun main() {
    val (dss, towels) = readInput("Day19").parts { it }
    val ds = dss[0].split(", ")
    fun count(s: String): Long {
        val n = s.length
        val dp = LongArray(n + 1)
        dp[n] = 1
        for (i in n - 1 downTo 0) {
            dp[i] = ds.sumOf { t -> if (s.startsWith(t, i)) dp[i + t.length] else 0L }
        }
        return dp[0]
    }
    val cnt = towels.sumOf { count(it) }
    println(cnt)
}