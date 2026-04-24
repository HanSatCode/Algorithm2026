package ch01;

import ch01.abstractSort.AbstractSort;

public class MergeTD extends AbstractSort {
    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) aux[k] = a[k];

        int i = lo; int j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if(lo >= hi) return;
        int mid = lo + (hi - lo) / 2; // 오버플로 방지
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void main(String[] args) {
        String[] a = { "A", "G", "L", "O", "R", "H", "I", "M", "S", "T"};
        MergeTD.sort(a);
        MergeTD.show(a);
    }
}
