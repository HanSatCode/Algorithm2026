// 22212014 한석희
package homework.ch01;

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
		while(head != null) {
			System.out.print(head.val + " -> ");	
			head = head.next;
		}
		System.out.println("null ]");
	}
}

class Solution2 {
	public ListNode insertionSortList(ListNode head) {
		ListNode dummy = new ListNode(-1, head);
		ListNode target = head.next;
		ListNode pre1 = head;
		
		while(target != null) { 
			if (pre1.val <= target.val) { 
				pre1 = target;	
				target = target.next;
			}
			else {
				ListNode inPoint = dummy;
				while (inPoint.next.val < target.val) inPoint = inPoint.next;
				
				pre1.next = target.next;
				target.next = inPoint.next;
				inPoint.next = target;
				
				target = pre1.next;
			}
		}
		return dummy.next;
	}
}
