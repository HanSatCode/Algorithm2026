// 22212014 한석희
package homework.ch0506;

import java.util.*;

public class HW4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 N을 입력하세요! > "); int N = sc.nextInt(); sc.close();
        
        int[] dp = new int[N + 1];
        for(int i = 1; i <= N; i++) { dp[i] = i - 1; }

        for(int i = 2; i <= N; i++) {
            if (i % 5 == 0) dp[i] = Math.min(dp[i], dp[i / 5] + 1);
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            dp[i] = Math.min(dp[i], dp[i - 1] + 1);
        }
        System.out.println(dp[N]);
    }
}