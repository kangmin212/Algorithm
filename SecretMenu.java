import java.util.Scanner;

public class SecretMenu {
    public static String checkSecretMenu(int M, int N, int K, int[] secretMenu, int[] userInput) {
        // 슬라이딩 윈도우 방식으로 부분 배열 비교
        for (int i = 0; i <= N - M; i++) {
            boolean isSecret = true;
            for (int j = 0; j < M; j++) {
                if (userInput[i + j] != secretMenu[j]) {
                    isSecret = false;
                    break;
                }
            }
            if (isSecret) return "secret";
        }
        return "normal";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int M = sc.nextInt();
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] secretMenu = new int[M];
        for (int i = 0; i < M; i++) {
            secretMenu[i] = sc.nextInt();
        }

        int[] userInput = new int[N];
        for (int i = 0; i < N; i++) {
            userInput[i] = sc.nextInt();
        }

        // 결과 출력
        System.out.println(checkSecretMenu(M, N, K, secretMenu, userInput));

        sc.close();
    }
}