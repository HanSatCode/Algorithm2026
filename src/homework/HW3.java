// 22212014 한석희
package homework;

import java.util.Scanner;
import java.util.Arrays;

public class HW3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = sc.nextInt();
		}
		sc.close();
		
		// 1. (쉘) 정렬하기. -> 각 원소의 순서가 만들어짐
		int[] sortedA = new int[n];	// 받은 배열을 정렬할 리스트를 추가 생성함
		System.arraycopy(A, 0, sortedA, 0, n); // 받은 배열을 방금 생성한 배열에 복사
		int temp, h = 1;	// 스왑을 위한 임시 저장 변수 temp, 그리고 쉘 정렬에 사용할 변수 h 할당
		while (h < n / 3) h = 3 * h + 1;	// 최적의 h 크기 설정
		while (h >= 1) {	// h가 1이 될 때까지 정렬 시도
			for(int i = h; i < n; i++) {	// h부터 시작 (처음에 인덱스 0번과 비교하기 위함)
				for (int j = i; j >= h && sortedA[j] < sortedA[j - h]; j -= h) { // 쉘 내에서 뒷 원소가 앞 원소보다 작을 때까지 반복  (삽입 정렬)
					temp = sortedA[j]; sortedA[j] = sortedA[j - h]; sortedA[j - h] = temp;	// h만큼 떨어진 원소와 스왑
				}
			}
			h /= 3;	// h 감소
		}
		
		// 2. 중복 제거하기.
		int[] uniqueArray = new int[n];	// 만들어진 조합 크기만큼의 배열 생성하기
		int uniqueCount = 0; // 중복 제거 배열의 인덱스
		uniqueArray[uniqueCount++] = sortedA[0]; // 처음에는 넣고 시작. (그래야 2번째 원소의 중복 여부 판단 가능)
		for(int i = 1; i < n; i++) {	// 순차적으로 확인
			if(sortedA[i] != uniqueArray[uniqueCount - 1]) {	// 만약에, 현재 조합이 마지막에 넣은 원소와 같다면?
				uniqueArray[uniqueCount++] = sortedA[i];	// 중복 제거 배열에 넣고 인덱스 증가하기
			}
		}
		Arrays.copyOfRange(uniqueArray, 0, uniqueCount); // 슬라이싱
		
		// 3. 이진탐색으로 찾기 -> 압축 좌표 = 인덱스
		for(int i = 0; i < n; i++) {
			int target = A[i];	// 원 배열의 원소
			int left = 0, right = uniqueCount - 1, mid;	// 이진탐색 범위 변수 설정
			while(left <= right) {
				mid = (left + right) / 2;
				if (target < uniqueArray[mid]) {
					right = mid - 1;
				}
				else if (target > uniqueArray[mid]) {
					left = mid + 1;
				}
				else {
					System.out.print(mid + " ");	// 발견 시 바로 출력
					break;
				}
			}
		}	
	}
}
