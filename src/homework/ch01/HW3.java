// 22212014 한석희
package homework.ch01;

import java.util.Scanner;

public class HW3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = sc.nextInt();
		}
		sc.close();
		
		int[] sortedA = new int[n];
		System.arraycopy(A, 0, sortedA, 0, n);

		int temp, h = 1;
		while (h < n / 3) h = 3 * h + 1;	
		while (h >= 1) {
			for(int i = h; i < n; i++) {	
				for (int j = i; j >= h && sortedA[j] < sortedA[j - h]; j -= h) {
					temp = sortedA[j]; sortedA[j] = sortedA[j - h]; sortedA[j - h] = temp;
				}
			}
			h /= 3;	
		}
		
		int[] uniqueArray = new int[n];	
		int uniqueCount = 0;
		uniqueArray[uniqueCount++] = sortedA[0]; 
		for(int i = 1; i < n; i++) {
			if(sortedA[i] != uniqueArray[uniqueCount - 1]) {
				uniqueArray[uniqueCount++] = sortedA[i];
			}
		}
		
		for(int i = 0; i < n; i++) {
			int target = A[i];
			int left = 0, right = uniqueCount - 1, mid;
			while(left <= right) {
				mid = (left + right) / 2;
				if (target < uniqueArray[mid]) {
					right = mid - 1;
				}
				else if (target > uniqueArray[mid]) {
					left = mid + 1;
				}
				else {
					System.out.print(mid + " ");
					break;
				}
			}
		}	
	}
}
