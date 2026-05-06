package homework.ch0506; // 제출할 때 지우기

import java.util.*;


public class ch0506_04 {
    public int N = 0;
    static public int solve(int N, int cnt) {
        if (N == 0) {
            return cnt;
        }
        else {
        if(N % 5 == 0) return solve(N / 5, cnt + 1);
        if(N % 3 == 0) return solve(N / 3, cnt + 1);
        if(N % 2 == 0) return solve(N /2, cnt + 1);
        return solve(N - 1, cnt + 1);
        } 
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("정수 N을 입력하세요! > ");
        int N = sc.nextInt();

        System.out.println(solve(N, 0));   

    }
}
