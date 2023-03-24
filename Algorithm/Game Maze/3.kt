import java.util.*

data class Point(val x: Int, val y: Int, val step: Int)

fun solution(maze: Array<IntArray>): Int {
    val n = maze.size
    val m = maze[0].size
    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)
    val visited = Array(n) { BooleanArray(m) }

    val q: Queue<Point> = LinkedList()
    q.add(Point(0, 0, 1))
    visited[0][0] = true

    while (!q.isEmpty()) {
        val cur = q.poll()
        if (cur.x == n - 1 && cur.y == m - 1) {
            return cur.step
        }

        for (i in 0..3) {
            val nx = cur.x + dx[i]
            val ny = cur.y + dy[i]
            if (nx in 0 until n && ny in 0 until m && maze[nx][ny] == 0 && !visited[nx][ny]) {
                visited[nx][ny] = true
                q.add(Point(nx, ny, cur.step + 1))
            }
        }
    }

    return -1
}
