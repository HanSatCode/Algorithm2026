// 222121014 한석희
package homework.ch07; // 제출할 때 지우기

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        int boundR = routes[0][1];
        for(int i = 1 ; i < routes.length; i++) {
            if(boundR < routes[i][0]) { boundR = routes[i][1]; answer++; }
        }
        answer++;

        return answer;
    }
}

public class HW2 {
    public static void main(String[] args) {
        System.out.println("[테스트용 main 함수입니다.]");
        Scanner sc = new Scanner(System.in);
        Solution solve = new Solution();

        ArrayList<int[]> routes = new ArrayList<>();
        int index = 0;
        while(true) {
            System.out.print(index++ + "번째 좌표 x1, x2를 공백으로 구분하여 입력해주세요 ! (종료는 -30001 -30001) : ");
            int x1 = sc.nextInt(); int x2 = sc.nextInt();
            if(x1 == -30001 && x2 == -30001) break;
            routes.add(new int[]{x1, x2});
        }
        System.out.println(">>> 정답은 " + solve.solution(routes.toArray(new int[0][])) + "입니다.");
        sc.close();
    }
}
