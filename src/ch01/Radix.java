package ch01;

public class Radix {
    public static void sort(int[] A) {
        int m = 0, exp = 1, n = A.length;
        int[] B = new int[n];

        for(int i = 0; i < n; i++) {
            if (m < A[i]) m = A[i];
        }

        while(m / exp > 0) {
            int[] C = new int[10];
            for(int i = 0; i < n; i++) C[(A[i] / exp) % 10]++;
            for(int i = 1; i < 10; i++) C[i] += C[i - 1];
            for(int i = n - 1; i >= 0; i--) B[--C[(A[i] / exp) % 10]] = A[i];
            System.arraycopy(B, 0, A, 0, n);
            exp *= 10;
        }
    }
    public static void main(String[] args) {
        int[] A = {10, 4, 5, 8, 1, 8, 3, 6}, B;
        B = Counting.sort(A, 11);
        for (int i = 0; i < B.length; i++)
        System.out.print(B[i] + " ");
        System.out.println();
    }
}
