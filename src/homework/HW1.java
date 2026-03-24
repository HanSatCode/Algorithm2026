// 22212014 한석희
package homework;

import java.util.Arrays;
import java.util.ArrayList;

public class HW1 {
	public static void main(String[] args) {
		Solution1 S = new Solution1();
		int[] numbers = {5, 0, 2, 7};
		System.out.println("입력 = " + Arrays.toString(numbers));
		S.solution(numbers);
		System.out.println("출력 = " + Arrays.toString(S.solution(numbers)));
	}
}

class Solution1 {
	public int[] solution(int[] numbers) {
		int i, j, n = numbers.length; // 임시 변수 i j, 입력인자 배열의 길이 n
		int[] combination = new int[(n * (n - 1)) / 2]; // n개의 원소에서 2개의 '최대' 조합 = nC2
		int index = 0; // 조합 배열의 인덱스
		
		// 1단계. 가능한 모든 조합 추가하기.
		for (i = 0; i < n; i++) {
			for (j = i + 1; j < n; j++) {
				combination[index++] = numbers[i] + numbers[j];
			}
		}
		
		// 2단계. (삽입) 정렬하기.
		for(i = 1; i < index; i++) {
			int target = combination[i]; // 타겟 값 백업하기 (마지막에 한 번에 넣기 위함)
			for (j = i - 1; j >= 0 && combination[j] > target; j--) { // 타겟보다 앞에 있는 원소가 클 때까지 반복
				combination[j + 1] = combination[j]; // 한 칸씩 뒤로 미루기
			}
			combination[j + 1] = target; // 마지막에 자리에 삽입하기
		}
		
		// 3단계. 중복 제거하기.
		int[] uniqueArray = new int[index];	// 만들어진 조합 크기만큼의 배열 생성하기
		int uniqueCount = 0; // 중복 제거 배열의 인덱스
		uniqueArray[uniqueCount++] = combination[0]; // 처음에는 넣고 시작 (그래야 2번째 원소부터 중복 여부 판단 가능)
		for(i = 1; i < index; i++) {
			if(combination[i] != uniqueArray[uniqueCount - 1]) {	// 만약에, 현재 조합이 마지막에 넣은 원소와 같다면?
				uniqueArray[uniqueCount++] = combination[i];	// 중복 제거 배열에 넣고 인덱스 증가하기
			}
		}
		
		return Arrays.copyOfRange(uniqueArray, 0, uniqueCount);	// 슬라이싱하기
	}
}