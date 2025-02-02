import java.util.*;

public class NetworkFlow {
    private static final int INF = Integer.MAX_VALUE;

    // BFS를 사용하여 경로를 찾는 함수
    private static boolean bfs(int[][] capacity, int[][] flow, int source, int sink, int[] parent) {
        int n = capacity.length;    
        boolean[] visited = new boolean[n];     
        Queue<Integer> queue = new LinkedList<>(); 
        
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int next = 0; next < n; next++) {
                // 잔여 용량이 남아있고 방문하지 않은 노드
                if (!visited[next] && capacity[current][next] - flow[current][next] > 0) {
                    queue.add(next);
                    parent[next] = current;
                    visited[next] = true;

                    // 싱크 노드에 도달하면 true 반환
                    if (next == sink) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 최대 유량 계산 함수
    public static int edmondsKarp(int[][] capacity, int source, int sink) {
        int n = capacity.length;
        int[][] flow = new int[n][n];
        int maxFlow = 0;
        int[] parent = new int[n];

        // BFS를 통해 가능한 경로를 찾으며 최대 유량 계산
        while (bfs(capacity, flow, source, sink, parent)) {
            // 경로의 최소 잔여 용량 찾기
            int pathFlow = INF;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v] - flow[u][v]);
            }

            // 경로를 따라 유량 업데이트
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += pathFlow;
                flow[v][u] -= pathFlow; 
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        // 그래프의 용량 (예제에 맞게 수정 가능)
        int[][] capacity = {
            {0, 4, 6, 0, 0, 0, 0}, // A
            {0, 0, 0, 3, 4, 0, 0}, // B
            {0, 0, 0, 3, 0, 3, 0}, // C
            {0, 0, 0, 0, 0, 0, 3}, // D
            {0, 0, 0, 0, 0, 0, 3}, // E
            {0, 0, 0, 0, 0, 0, 2}, // F
            {0, 0, 0, 0, 0, 0, 0}  // G
        };

        int source = 0; // A 노드/
        int sink = 6;   // G 노드

        int maxFlow = edmondsKarp(capacity, source, sink);
        System.out.println("최대 유량: " + maxFlow);
    }
}

