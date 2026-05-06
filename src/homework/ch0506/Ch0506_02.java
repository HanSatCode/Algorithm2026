// 22212014 한석희
package homework.ch0506; // 제출할 때 지울 것

import java.util.*;

public class Ch0506_02 {
    static void solve(ArrayList<Integer> current, int n, int k, int start) {
        if (current.size() == k) {
            System.out.print(current + " ");
            return;
        } 
        else {
            for (int i = start; i <= n; i++) {
                current.add(i);
                solve(current, n, k, i + 1);
                current.remove(current.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 n과 k를 입력? ");
        int n = sc.nextInt(); int k = sc.nextInt(); sc.close();
        ArrayList<Integer> temp = new ArrayList<>();
        solve(temp, n, k, 1);
    }
}