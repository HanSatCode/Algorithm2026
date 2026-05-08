// 22212014 한석희
package homework.ch0506;

import java.util.*;

class Solution {
    public int[] solution(int[][] arr) {
        int[] answer = {};

        answer = new int[2];
        solve(arr, answer, 0, 0, arr.length);

        return answer;
    }

    public void solve(int[][] arr, int[] answer, int startX, int startY, int size) {
        int represent = arr[startY][startX];
        for(int y = startY; y < size + startY; y++) {
            for(int x = startX; x < size + startX; x++) {
                if (represent != arr[y][x]) {
                    solve(arr, answer, startX, startY, size / 2);
                    solve(arr, answer, startX + (size / 2), startY, size / 2);
                    solve(arr, answer, startX , startY + (size / 2), size / 2);
                    solve(arr, answer, startX + (size / 2), startY + (size / 2), size / 2);
                    return;
                }
            }
        }
        answer[represent]++;
    }
}


public class Ch0506_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("행렬 크기(2의 거듭제곱)를 입력해주세요. > "); int n = sc.nextInt();
        
        int[][] arr = new int[n][n];
        System.out.println(n + "x" + n + " 행렬을 아래에 입력해주세요.");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        Solution s = new Solution();
        int[] answer = s.solution(arr);
        System.out.println("[" + answer[0] + ", " + answer[1] + "]");

    }
}
