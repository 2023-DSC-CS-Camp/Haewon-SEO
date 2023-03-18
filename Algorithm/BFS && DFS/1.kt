import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    // 입력 함수 설정
    val br = BufferedReader(InputStreamReader(System.`in`))

    // 정점: n, 간선: m, 시작 정점: v
    val (n, m, v) = br.readLine()!!.split(' ').map { it.toInt() }
    val graph = Array(n + 1) { mutableListOf<Int>() }

    // 간선이 연결하는 두 정점 저장 ( m 크기 만큼 반복 )
    repeat(m) {
        val (a, b) = br.readLine()!!.split(' ').map { it.toInt() }

        // 정점에 연결되어 있는 간선 추가 -- index 0은 Null 값
        graph[a].add(b)
        graph[b].add(a)
    }

    // 정점에 연결되어 있는 간선 정렬
    graph.forEach { it.sort() }

    /*
    정점을 지나쳤는지 Boolean 값 반환
    index = 정점, index 0은 null 값이기 때문에 n + 1
     */
    val visited = BooleanArray(n + 1)

    // dfs 함수
    dfs(graph, visited, v)

    // 방문 배열 초기화
    visited.fill(false)
    println()

    // bfs 함수
    bfs(graph, visited, v)
}

fun dfs(graph: Array<out List<Int>>, visited: BooleanArray, v: Int) {
    //out 해당 타입이 반환되는 것만 가능하며 수정될 수 없다는 것을 의미

    visited[v] = true
    print("$v ")

    // 해당 정점에 간선을 찾고, 방문 기록이 있는지 확인
    for (i in graph[v]) {
        if (!visited[i]) {
            // 출발 정점을 다시 i로 반환
            dfs(graph, visited, i)
        }
    }
}

fun bfs(graph: Array<out List<Int>>, visited: BooleanArray, start: Int) {

    // bfs는 Queue를 사용, 따라서 Queue 선언
    val queue: Queue<Int> = LinkedList<Int>()

    /*
    queue에서는 add 대신 offer 을 사용 ( offer은 null 반환)
    queue의 제네릭 타입을 Queue<MutableList<Int>>로 지정
     */
    queue.offer(start)
    visited[start] = true


    while (queue.isNotEmpty()) {
        // poll은 queue의 첫 번째 값을 삭제하고 반환
        val v = queue.poll()
        print("$v ")

        for (i in graph[v]) {
            // visisted으로 방문 했는지 확인 후, queue에 추가
            if (!visited[i]) {
                visited[i] = true
                queue.offer(i)
            }
        }
    }
}


