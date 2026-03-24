// 22212014 한석희
package homework.ch01;

import java.util.Arrays;

public class HW1 {
	public static void main(String[] args) {
		Solution1 S = new Solution1();
		int[] numbers = {2, 1, 3, 4, 1};
		//int[] numbers = {5, 0, 2, 7};
		System.out.println("입력 = " + Arrays.toString(numbers));
		S.solution(numbers);
		System.out.println("출력 = " + Arrays.toString(S.solution(numbers)));
	}
}

class Solution1 {
	public int[] solution(int[] numbers) {
		int i, j, n = numbers.length;
		int[] combination = new int[(n * (n - 1)) / 2]; // nC2
		int index = 0;

		for (i = 0; i < n; i++) {
			for (j = i + 1; j < n; j++) {
				combination[index++] = numbers[i] + numbers[j];
			}
		}
		
		int temp = 0;
		for(i = 1; i < index; i++) {
			for (j = i; j > 0 && combination[j] < combination[j - 1]; j--) {
				temp = combination[j];
				combination[j] = combination[j - 1];
				combination[j - 1] = temp;
			}
		}
		
		int[] uniqueArray = new int[index];
		int uniqueCount = 0;
		uniqueArray[uniqueCount++] = combination[0];
		for(i = 1; i < index; i++) {
			if(combination[i] != uniqueArray[uniqueCount - 1]) {
				uniqueArray[uniqueCount++] = combination[i];
			}
		}

		return Arrays.copyOfRange(uniqueArray, 0, uniqueCount);
	}
}