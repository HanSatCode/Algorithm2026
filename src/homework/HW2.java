// 22212014 한석희
package homework;

class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class HW2 {
	public static void main(String[] args) {
		ListNode head = null;
		
		ListNode five = new ListNode(0);
		ListNode four = new ListNode(4, five);
		ListNode three = new ListNode(3, four);
		ListNode two = new ListNode(5, three);
		head = new ListNode(-1, two);
		
		Solution2 sol = new Solution2();
		head = sol.insertionSortList(head);
		printList(head);
	}
	
	public static void printList(ListNode head) {
		System.out.print("정렬 결과 = [ ");
		while(head != null) {	// 연결 리스트를 순회하며 출력함
			System.out.print(head.val + " -> ");	
			head = head.next;
		}
		System.out.println("null ]");
	}
}

class Solution2 {
	public ListNode insertionSortList(ListNode head) {
		ListNode dummy = new ListNode(-1, head);		// 더미 노드 생성 (나중에 헤드가 다른 원소로 바뀌더라도 바로 가리키기 위함)
		ListNode target = head.next;	// 이동시킬 노드 (처음엔 2번째를 가리킴)
		ListNode pre1 = head;	// 1단계 이전 노드 (처음엔 1번째를 가리킴)
		
		while(target != null) { // 순차적으로 전체 탐색하기
			if (pre1.val <= target.val) { // 만약에 앞에 있는 노드의 값이 뒤에 있는 노드보다 작다면? (이미 정렬됨)
				pre1 = target;	// 1단계 이전 노드를 다음 타겟으로 이동 (= 타겟 노드)
				target = target.next;	// 현재 노드를 다음 타겟 이동 (= 타겟 노드의 그 다음 노드)
			}
			else { // 그렇지 않다면... (정렬 필요)
				ListNode inPoint = dummy;	// 처음부터 순차적 탐색을 통해 들어갈 자리를 찾기
				while (inPoint.next.val < target.val) inPoint = inPoint.next;	// 그 지점까지 이동
				
				pre1.next = target.next;	// 이전노드는 현 노드의 다음 노드를 가리킴 (타겟의 다음 노드부터 먼저 바꾸면 유실되기 때문)
				target.next = inPoint.next;	// 현 노드는 2단계 이전 노드의 다음 노드를 가리킴
				inPoint.next = target;		// 2단계 이전 노드는 현 노드를 가리킴
				
				target = pre1.next;	// 바뀐 순서의 다음 노드로 이동 (= 원래 순서의 다음 노드)
			}
		}
		return dummy.next;	// 정렬된 연결 리스트의 Head를 반환
	}
}
