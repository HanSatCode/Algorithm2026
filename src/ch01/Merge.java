package ch01;

import ch01.abstractSort.AbstractSort;

public class Merge extends AbstractSort {
    public static void sort(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) aux[k] = a[k];

        int i = lo; int j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    public static void main(String[] args) {
        String[] a = { "A", "G", "L", "O", "R", "H", "I", "M", "S", "T"};
        String[] aux = new String[10];
        Merge.sort(a, aux, 0, 5, 9);
        Merge.show(a);
    }
}
