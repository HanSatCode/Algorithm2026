package homework.ch07;

import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        String[] list = number.split("");

        for(int i = 1; i < list.length && k > 0; i++) {
            for(int j = 0; j < i && k > 0; j++) {
                if(list[j].equals("")) continue;
                if(list[j].compareTo(list[i]) < 0) {
                    list[j] = ""; k--;
                }
            }
        }
        if (k == 0) answer = String.join("", list);
        else answer = String.join("", Arrays.copyOfRange(list, 0, list.length - k));

        return answer;
    }
}

public class Ch07_HW1 {
    public static void main(String[] args) {
        System.out.println("[디버깅용 main 함수입니다.]");
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("S(number) 값을 입력해주세요! (종료는 -1) : "); String S = sc.next();
            if (S.equals("-1")) break;
            System.out.print("k 값을 입력해주세요! : "); int k = sc.nextInt();

            Solution solve = new Solution();
            System.out.println(">>> 정답은 " + solve.solution(S, k) + " 입니다.");
        }
    }
}
