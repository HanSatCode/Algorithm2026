package ch01;

import ch01.abstractSort.AbstractSort;

public class MergeBU extends AbstractSort {
    public static void merge(Comparable[] in, Comparable[] out, int lo, int mid, int hi) {
        int i = lo; int j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if (i > mid) out[k] = in[j++];
            else if (j > hi) out[k] = in[i++];
            else if (less(in[i], in[j])) out[k] = in[i++];
            else out[k] = in[j++];
        }
    }
    
    public static void sort(Comparable[] a) {
        Comparable[] src = a, dst = new Comparable[a.length], tmp;
        int N = a.length;
        for (int n = 1; n < N; n *= 2) {
            for (int i = 0; i < N; i += 2 * n) {
                merge(src, dst, i, i + n - 1, Math.min(i + 2*n - 1, N - 1));
            }
            tmp = src; src = dst; dst = tmp;
        }
        if (src != a) System.arraycopy(src, 0, a, 0, N);
    }

    public static void main(String[] args) {
        String[] a = { "A", "G", "L", "O", "R", "H", "I", "M", "S", "T"};
        MergeBU.sort(a);
        MergeBU.show(a);
    }
}
