import java.util.Scanner;

public class KnapSack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 배낭의 최대 무게(K)와 물건의 개수(N)를 입력받는다.
        System.out.println("배낭의 최대 용량을 입력하세요:");
        int K = sc.nextInt(); // 배낭의 최대 무게
        System.out.println("물건의 개수를 입력하세요:");
        int N = sc.nextInt(); // 물건의 개수
        
        int[] W = new int[N + 1]; // 물건들의 무게 배열
        int[] V = new int[N + 1]; // 물건들의 가치 배열
        
        // 물건들의 무게와 가치를 입력받는다.
        for (int i = 1; i <= N; i++) {
            System.out.println("물건 " + i + "의 무게와 가치를 입력하세요 (공백으로 구분):");
            W[i] = sc.nextInt();
            V[i] = sc.nextInt();
        }

        // DP 테이블 선언: dp[i][j]는 i번째 물건까지 고려했을 때, 무게 j 이하에서 얻을 수 있는 최대 가치
        int[][] dp = new int[N + 1][K + 1];
        
        // 동적 프로그래밍으로 배낭 문제 해결
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                if (W[i] <= j) {
                    // 물건을 넣을 수 있는 경우: 물건을 넣는 경우와 안 넣는 경우 중 큰 값을 선택
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
                } else {
                    // 물건을 넣을 수 없는 경우: 이전 상태를 그대로 가져옴
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        // 결과 출력
        System.out.println("배낭에 들어간 물건들의 최대 가치: " + dp[N][K]);
        
        // 배낭에 들어간 물건 찾기
        int weight = K;
        System.out.print("배낭에 들어간 물건들: ");
        for (int i = N; i > 0; i--) {
            if (dp[i][weight] != dp[i - 1][weight]) {
                System.out.print(i + " ");
                weight -= W[i];
            }
        }
    }
}