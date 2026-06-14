// 222121014 한석희
package homework.ch07; // 제출할 때 지우기

import java.util.*;

class Solution {
    public String solve(String number, int k) {
        char[] num = number.toCharArray();
        Deque<Character> array = new ArrayDeque<>(); array.addLast(num[0]);

        for(int i = 1; i < num.length; i++) {
            char cur = num[i];
            while(k > 0 && array.size() > 0 && cur > array.getLast()) { array.pollLast(); k--; }
            array.addLast(cur);
        }

        int size = array.size();
        StringBuilder sb = new StringBuilder();
        if (k != 0) for(int i = 0; i < size - k; i++) { sb.append(array.pollFirst()); }

        return sb.toString();
    }
}

public class HW1 {
    public static void main(String[] args) {
        System.out.println("[테스트용 main 함수입니다.]");
        Scanner sc = new Scanner(System.in);
        Solution solve = new Solution();
        while(true) {
            System.out.print("S(number) 값을 입력해주세요! (종료는 -1) : "); String S = sc.next();
            if (S.equals("-1")) break;
            System.out.print("k 값을 입력해주세요! : "); int k = sc.nextInt();

            System.out.println(">>> 정답은 " + solve.solve(S, k) + " 입니다.");
        }
        sc.close();
    }
}