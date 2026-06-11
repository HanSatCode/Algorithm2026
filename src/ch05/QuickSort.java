package ch05;

public class QuickSort {
    public static void sort(Comparable[] a) { quicksort(a, 0, a.length -1); }

    private static void quicksort(Comparable[] a, int low, int high) {
        int pivotpoint = partition(a, low, high);
        quicksort(a, low, pivotpoint - 1);
        quicksort(a, pivotpoint + 1, high);
    }

    private static int partition(Comparable[] a, int low, int high) {
        Comparable pivotitem = a[low]; int j = low;
        for(int i = low + 1; i <= high; i++) {
            //if(less(a[i], pivotitem)) {
            //    j += 1; exch(a, i, j);
            //}
        }
        int pivotpoint = j;
        //exch(a, low, pivotpoint);
        return pivotpoint;
    }
}
