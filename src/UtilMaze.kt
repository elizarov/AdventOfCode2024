/**
 * Finds 4-distances in a maze grid:
 * - Maze dimensions are [n] x [m].
 * - Start point is ([i0], [j0]) with distance of 0.
 * - Unreachable cells are marked with [inf] distance.
 * - Moves in 4 directions are allowed.
 * - Only cells where [allow] predicate returns true can be moved into.
 * - `allow(i, j, d)` is called on cell (`i`, `j`) that is distance `d` from the start.
 */
fun mazeDistances(n: Int, m: Int, i0: Int, j0: Int, inf: Int = Int.MAX_VALUE / 4, allow: (i: Int, j: Int, d: Int) -> Boolean): IntArray2 {
    val (di, dj) = RDLU_DIRS
    val ds = Array(n) { IntArray(m) { inf } }
    val q = ArrayDeque<P2>()
    q += P2(i0, j0)
    ds[i0][j0] = 0
    while (q.isNotEmpty()) {
        val (i, j) = q.removeFirst()
        val d1 = ds[i][j] + 1
        for (k in 0..3) {
            val i1 = i + di[k]
            val j1 = j + dj[k]
            if (i1 in 0..<n && j1 in 0..<m && ds[i1][j1] == inf && allow(i1, j1, d1)) {
                ds[i1][j1] = d1
                q += P2(i1, j1)
            }
        }
    }
    return ds
}