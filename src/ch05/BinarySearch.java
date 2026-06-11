package ch05;

public class BinarySearch {
    static int x = 3;
    static int[] S = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static int location(int low, int high) {
        if(low > high) return -1;
        else {
            int mid = (low + high) / 2;
            if (x == S[mid]) return mid;
            else if (x < S[mid]) return location(low, mid - 1);
            else return location(mid + 1, high);
        }
    }
    public static void main(String[] args) {
        System.out.println(x + "의 인덱스 : " + location(0, S.length -1));
    }
}
