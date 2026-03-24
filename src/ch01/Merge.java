package ch01;

public class Merge {
	public static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
		
		for(int k = lo; k <= hi; k++) aux[k] = a[k];
		
		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++) {
			if(i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (aux[i] > aux[j]) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	}
	
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 9, 2, 3, 4, 5 };
		int[] temp = new int[a.length];
		
		merge(a, temp, 0, 3, a.length - 1);
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
}
