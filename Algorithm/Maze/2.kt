import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    // n x m 크기의 미로
    val (n, m) = br.readLine()!!.split(' ').map { it.toInt() }

    // 그래프 크기를 + 2나 늘린경우는 마지막 줄에서 4방면의 값을 확인하기 위해!
    val graph = Array(n + 2) { IntArray(m + 2) { 0 } }

    // 간선 배열 --> Set 설정(중복 방지)
    val edge = Array(n * m + 1) { mutableListOf<Int>() }

    // 방문, 시작점, 순서
    val visited = BooleanArray(n * m + 1)
    val v = 1
    var num = 1

    /*
    입력된 숫자를 그래프 배열에 대입.
    값이 1이면 해당하는 순번을 저장.
    else면 값 0을 대입
    */
    for (i in 1..n) {
        val input = br.readLine()

        for (j in 1..m) {
            if (input[j - 1] == '1') {
                graph[i][j] = num
            } else {
                graph[i][j] = 0
            }
            num++
        }
    }

    // 간선 생성
    for (i in 1 until n + 1) {
        for (j in 1 until m + 1) {
            // 4방면 같은 값 확인해서 간선 생성
            if (graph[i][j] != 0) {

                // 위
                if (graph[i - 1][j] != 0) {
                    if (i > 1) {
                        edge[graph[i][j]].add(graph[i - 1][j])
                        edge[graph[i - 1][j]].add(graph[i][j])
                    }
                }
                // 아래
                if (graph[i + 1][j] != 0) {
                    if (i < n) {
                        edge[graph[i][j]].add(graph[i + 1][j])
                        edge[graph[i + 1][j]].add(graph[i][j])
                    }
                }
                // 왼
                if (graph[i][j - 1] != 0) {
                    if (j > 1) {
                        edge[graph[i][j]].add(graph[i][j - 1])
                        edge[graph[i][j - 1]].add(graph[i][j])
                    }
                }
                //오
                if (graph[i][j + 1] != 0) {
                    if (j < m) {
                        edge[graph[i][j]].add(graph[i][j + 1])
                        edge[graph[i][j + 1]].add(graph[i][j])
                    }
                }
            }
        }
    }
    //edge 배열 출력
    edge.forEach { it.sort() }
    for (i in 1 until edge.size) {
        edge[i] = edge[i].distinct().toMutableList()
    }
    for (i in 1 until edge.size) {
        println("$i -> ${edge[i]}")
    }
    //정렬 후 bfs 알고리즘 사용
    //bfs(edge, visited, v)
}

fun bfs(edge: Array<out List<Int>>, visited: BooleanArray, start: Int) {
    val queue: Queue<Int> = LinkedList()
    var count: Int = 0

    queue.offer(start)
    visited[start] = true

    while (queue.isNotEmpty()) {
        val v = queue.poll()

        for (i in edge[v]) {
            if (!visited[i]) {
                visited[i] = true
                queue.offer(i)
                count++
            }
        }

    }
    print(count)
}