package ch06;

public class MaxSubList {
    public static double maxSubList(double[] A, int n) {
        double[] B = new double[n];
        double max = B[0] = A[0];

        for(int i = 1; i < n; i++) {
            B[i] = (B[i - 1] <= 0) ? A[i] : B[i - 1] + A[i];
            if(B[i] > max) max = B[i];
        }
        return max;
    }
}
