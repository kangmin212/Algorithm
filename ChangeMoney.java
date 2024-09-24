import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class ChangeMoney
{
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);

        System.out.println("화폐 종류 : ");
        String[] coinStrings=scanner.nextLine().split(" ");
        Integer[] coins=new Integer[coinStrings.length];

        for(int i=0; i<coinStrings.length; i++) {
            coins[i]=Integer.parseInt(coinStrings[i]);
        }

        Arrays.sort(coins, Collections.reverseOrder());

        System.out.println("거슬러줘야 하는 금액 : ");
	    int total = scanner.nextInt();

        int minCoinCnt=0;
	    
	    for (int coin:coins) {
            if(total==0) break;
	        minCoinCnt += (total/coin);
	        total-=(total/coin)*coin;
	    }
		
		System.out.println("최소 화폐 개수 : " + minCoinCnt);
	}
}