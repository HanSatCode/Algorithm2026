// 22212014 한석희
package homework.ch0506;

import java.util.*;

public class ch0506_04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 N을 입력하세요! > "); int N = sc.nextInt(); sc.close();
        
        int[] dp = new int[N + 1]; for(int i = N; i >= 0; i--) { dp[i] = N - i; }

        for(int i = N; i > 1; i--) {
            if(i % 5 == 0) dp[i / 5] = Math.min(dp[i / 5], dp[i] + 1);
            if(i % 3 == 0) dp[i / 3] = Math.min(dp[i / 3], dp[i] + 1);
            if(i % 2 == 0) dp[i / 2] = Math.min(dp[i / 2], dp[i] + 1);
            dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
        }
        System.out.println(dp[1]);
    }
}