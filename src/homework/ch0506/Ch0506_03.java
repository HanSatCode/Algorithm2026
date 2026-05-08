// 22212014 한석희
package homework.ch0506;

import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int n = triangle.length;
        int[][] dp = new int[n][]; for(int i = 0; i < n; i++) { dp[i] = new int[i + 1]; }

        dp[0][0] = triangle[0][0];
        for(int i = 1; i < n; i++) {
            int preLastIndex = triangle[i - 1].length - 1;
            int curLastIndex = triangle[i].length - 1;
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            dp[i][curLastIndex] = dp[i - 1][preLastIndex] + triangle[i][curLastIndex];
        }

        for(int i = 2; i < n; i++) {
            for(int j = 1; j < triangle[i].length - 1; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
            }
        }

        answer = Arrays.stream(dp[n - 1]).max().getAsInt();
        return answer;
    }
}

public class Ch0506_03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> triangle = new ArrayList<>();
        for(int i = 0; i < 500; i++) {
            System.out.print("삼각형의 " + i + "번째 줄의 원소를 입력하세요. (" + (i + 1) + "개의 원소를 공백으로 구분할 것! 종료는 -1) > ");
            int first = sc.nextInt(); if (first == -1) break;

            int[] row = new int[i + 1];
            row[0] = first;
            for(int j = 1; j <= i; j++) { row[j] = sc.nextInt(); }
            triangle.add(row);
        }
        sc.close();
        Solution sol = new Solution();
        int result = sol.solution(triangle.toArray(new int[0][]));
        System.out.println(result);
    }
}