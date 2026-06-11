// 22212014 한석희
package homework.ch07; // 제출할 때 지우기

import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";

        Deque<Character> deque = new ArrayDeque<>();
        for (char c : number.toCharArray()) {
            while(k > 0 && !deque.isEmpty() && deque.peekLast() < c) {
                deque.pollLast(); k--;
            }
            deque.addLast(c);
        }

        int size = deque.size();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size - k; i++) {
            sb.append(deque.pollFirst());
        }
        answer = sb.toString();

        return answer;
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

            System.out.println(">>> 정답은 " + solve.solution(S, k) + " 입니다.");
        }
        sc.close();
    }
}
