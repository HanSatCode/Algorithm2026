package homework.ch02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

class Node<K extends Comparable<K>, V> {
	K leftKey, rightKey;
	V leftValue, rightValue;

	Node<K, V> leftChild, midChild, rightChild;
	Node<K, V> parent; 

	int keyCount;
	int size; int depth;

	public Node(K key, V value) {
		this.leftKey = key; this.leftValue = value;
		this.keyCount = 1; this.size = 1;
	}
}

class Tree23<K extends Comparable<K>, V> {
	Node<K, V> root;

	private Node<K, V> search(Node<K, V> cur, K key) {
		if (cur == null) return null;
		
		int cmpLeft = key.compareTo(cur.leftKey);

		if (cmpLeft == 0) return cur;
		else if (cur.leftChild == null && cur.midChild == null) return cur;
		else if (cmpLeft < 0) return search(cur.leftChild, key);
		else {
			if (cur.rightKey != null) {
				int cmpRight = key.compareTo(cur.rightKey);
				if (cmpRight == 0) return cur;
				else if (cmpRight < 0) return search(cur.midChild, key);
				else return search(cur.rightChild, key);
			}
			else return search(cur.midChild, key);
		}
	}

	public boolean contains(K key) {
		Node<K, V> node = search(root, key);
		if (node == null) return false;
		else return key.equals(node.leftKey) || (node.rightKey != null && key.equals(node.rightKey));
	}

	public V get(K key) {
		Node<K, V> node = search(root, key);
		if (node == null) return null;
		else if (key.equals(node.leftKey)) return node.leftValue;
		else if (node.rightKey != null && key.equals(node.rightKey)) return node.rightValue;
		else return null;
	}

	public Iterable<K> keys() {
		if (root == null) return null;
		ArrayList<K> keyList = new ArrayList<K>();
		inorder(root, keyList);
		return keyList;
	}

	private void inorder(Node<K,V> x, ArrayList<K> keyList) {
		if (x != null) {
			inorder(x.leftChild, keyList);
			keyList.add(x.leftKey);
			inorder(x.midChild, keyList);
			if (x.rightKey != null){
				keyList.add(x.rightKey);
				inorder(x.rightChild, keyList);
			}
		}
	}



	public void put(K key, V value) {
		if (root == null) {
			root = new Node<>(key, value);
			root.depth = 1;
			return;
		}
		Node<K, V> target = search(root, key);

		if(key.equals(target.leftKey)) target.leftValue = value;
		else if(target.rightKey != null && key.equals(target.rightKey)) target.rightValue = value;
		else {
			insertAndSplit(target, key, value);
			updateSizeDepth(target, 0);
		}
	}

	private void insertAndSplit(Node<K, V> node, K key, V value) {
		K upKey = key; V upVal = value;
		Node<K, V> childL = null; Node<K, V> childR = null;
		Node<K, V> cur = node;

		while (cur != null) {
			if (cur.keyCount == 1) {
				if (upKey.compareTo(cur.leftKey) < 0) {
					cur.rightKey = cur.leftKey; cur.rightValue = cur.leftValue;
					cur.leftKey = upKey; cur.leftValue = upVal;
					if (childL != null) {
						cur.rightChild = cur.midChild; cur.midChild = childR; cur.leftChild = childL;
						childL.parent = cur; childR.parent = cur;
					}
				} 
				else {
					cur.rightKey = upKey; cur.rightValue = upVal;
					if (childL != null) {
						cur.midChild = childL; cur.rightChild = childR;
						childL.parent = cur; childR.parent = cur;
					}
				}
				cur.keyCount = 2;
				return;
			}

			Node<K, V> newR = new Node<>(null, null);
			K midKey; V midValue;

			if (upKey.compareTo(cur.leftKey) < 0) {
				midKey = cur.leftKey; midValue = cur.leftValue;
				newR.leftKey = cur.rightKey; newR.leftValue = cur.rightValue;
				newR.leftChild = cur.midChild; newR.midChild = cur.rightChild;
				cur.leftKey = upKey; cur.leftValue = upVal;
				cur.leftChild = childL; cur.midChild = childR;
			} 
			else if (upKey.compareTo(cur.rightKey) < 0) {
				midKey = upKey; midValue = upVal;
				newR.leftKey = cur.rightKey; newR.leftValue = cur.rightValue;
				newR.leftChild = childR; newR.midChild = cur.rightChild;
				cur.midChild = childL;
			} 
			else {
				midKey = cur.rightKey; midValue = cur.rightValue;
				newR.leftKey = upKey; newR.leftValue = upVal;
				newR.leftChild = cur.rightChild; newR.midChild = childL; 
				newR.midChild = childR;
			}

			cur.rightKey = null; cur.rightValue = null; cur.rightChild = null; cur.keyCount = 1;
			newR.keyCount = 1;

			if (cur.leftChild != null) { cur.leftChild.parent = cur; cur.midChild.parent = cur; }
			if (newR.leftChild != null) { newR.leftChild.parent = newR; newR.midChild.parent = newR; }

			cur.size = cur.keyCount + (cur.leftChild != null ? cur.leftChild.size : 0) + (cur.midChild != null ? cur.midChild.size : 0);
			cur.depth = (cur.leftChild != null ? cur.leftChild.depth : 0) + 1;

			newR.size = newR.keyCount + (newR.leftChild != null ? newR.leftChild.size : 0) + (newR.midChild != null ? newR.midChild.size : 0);
			newR.depth = (newR.leftChild != null ? newR.leftChild.depth : 0) + 1;

			if (cur.parent == null) { 
				root = new Node<>(midKey, midValue);
				root.leftChild = cur; root.midChild = newR;
				cur.parent = root; newR.parent = root;
				return;
			}
			
			upKey = midKey; upVal = midValue; childL = cur; childR = newR;
			cur = cur.parent;
		}
	}


	public void updateSizeDepth(Node<K, V> node, int pm) {
		Node<K, V> cur = node;
		while (cur != null) {
            int newSize = cur.keyCount;
            int maxD = 0;
            if (cur.leftChild != null) { newSize += cur.leftChild.size; maxD = Math.max(maxD, cur.leftChild.depth); }
            if (cur.midChild != null) { newSize += cur.midChild.size; maxD = Math.max(maxD, cur.midChild.depth); }
            if (cur.rightChild != null) { newSize += cur.rightChild.size; maxD = Math.max(maxD, cur.rightChild.depth); }
            
            cur.size = newSize;
            cur.depth = maxD + 1;
            cur = cur.parent;
        }
	}
	
	public int size() { return root.size; }
	public int depth() { return root.depth; }
}


public class HW2_old {

	public static void main(String[] args) {
		Tree23<String, Integer> st = new Tree23<>();
		Scanner sc = new Scanner(System.in);	
		System.out.print("입력 파일 이름? ");
		String fname = sc.nextLine();	// 파일 이름을 입력
		System.out.print("난수 생성을 위한 seed 값? ");
		Random rand = new Random(sc.nextLong());
		sc.close();
		try {
			sc = new Scanner(new File(fname));
			long start = System.currentTimeMillis();
			while (sc.hasNext()) {
				String word = sc.next();
				if (!st.contains(word))
					st.put(word, 1);
				else	st.put(word, st.get(word) + 1);
			}
			long end = System.currentTimeMillis();
			System.out.println("입력 완료: 소요 시간 = " + (end-start) + "ms");
			
			System.out.println("### 생성 시점의 트리 정보");
			print_tree(st);		// 정상적으로 출력되면 50점
			
			ArrayList<String> keyList = (ArrayList<String>) st.keys();
			Collections.shuffle(keyList, rand);
			int loopCount = (int)(keyList.size() * 0.95);
			for (int i = 0; i < loopCount; i++) {
				//st.delete(keyList.get(i));						// 주석 처리 가능
			}
			//System.out.println("\n### 키 삭제 후 트리 정보");		// 주석 처리 가능
			//print_tree(st);										// 주석 처리 가능. 여기까지 정상적으로 출력되면 100점
		} catch (FileNotFoundException e) { e.printStackTrace(); }
		if (sc != null)
			sc.close();
	}
	
	private static void print_tree(Tree23<String, Integer> st) {
		System.out.println("등록된 단어 수 = " + st.size());		
		System.out.println("트리의 깊이 = " + st.depth());		
		
		String maxKey = "";
		int maxValue = 0;
		for (String word : st.keys())
			if (st.get(word) > maxValue) {
				maxValue = st.get(word);
				maxKey = word;
			}
		System.out.println("가장 빈번히 나타난 단어와 빈도수: " + maxKey + " " + maxValue);
	}
}