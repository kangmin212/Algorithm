import java.util.*;

public class SnakeAndLadder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();  
        int M = sc.nextInt();  

        int[] board = new int[101];  
        boolean[] visited = new boolean[101];  

        for (int i = 0; i < N + M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            board[start] = end;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});  

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int position = current[0];
            int moves = current[1];

            if (position == 100) {
                System.out.println(moves);
                return;
            }

            for (int i = 1; i <= 6; i++) {
                int nextPosition = position + i;

                if (nextPosition > 100) continue;

                if (board[nextPosition] != 0) {
                    nextPosition = board[nextPosition];
                }

                if (!visited[nextPosition]) {
                    visited[nextPosition] = true;
                    queue.add(new int[]{nextPosition, moves + 1});
                }
            }
        }

        sc.close();
    }
}