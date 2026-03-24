package ch01;

public class MergeBU {
	private static void merge(int[] in, int[] out, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++) {
			if(i > mid) out[k] = in[j++];
			else if(j > hi) out[k] = in[i++];
			else if(in[j] < in[i]) out[k] = in[j++];
			else out[k] = in[i++];
		}
	}
	
	public static void sort(int[] a) {
		int[] src = a, dst = new int[a.length], tmp;
		int N = a.length;
		for(int n = 1; n < N; n *= 2) {
			for(int i = 0; i < N; i+= 2 * n) {
				merge(src, dst, i, i+n-1, Math.min(i+2*n-1, N-1));
			}
			tmp = src; src = dst; dst = tmp;
		}
		if (src != a) System.arraycopy(src, 0, a, 0, N);
	}
	
	public static void main(String[] args) {
		int[] a = { 3, 4, 1, 5, 9, 2, 8, 3 };
		
		sort(a);
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
}
