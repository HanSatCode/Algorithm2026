package homework.ch02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

class Node23<K extends Comparable<K>, V> {
	K leftKey, rightKey;
	V leftValue, rightValue;

	Node23<K, V> leftChild, midChild, rightChild;
	Node23<K, V> parent;

	int N;

	boolean is2Node() { return rightKey == null; }
	boolean is3Node() { return rightKey != null; }
	boolean isLeaf() { return leftChild == null; }

	public Node23(K key, V value) {
		this.leftKey = key; this.leftValue = value; N = 1;
	}
}



class Tree23<K extends Comparable<K>, V> {
	private Node23<K, V> root;

	public int size() { return root == null ? 0 : root.N; }

	public int depth() { return depth(root); }
	private int depth(Node23<K, V> x) {
		if (x == null) return 0;
		return 1 + depth(x.leftChild);
	}

	public boolean contains(K key) { return get(key) != null; }

	public V get(K key) {
		if (root == null) return null;
		Node23<K, V> x = search(root, key);
		if (x == null) return null;

		if (key.equals(x.leftKey)) return x.leftValue;
		else if (x.is3Node() && key.equals(x.rightKey)) return x.rightValue;
		else return null;
	}

	private Node23<K, V> search(Node23<K, V> x, K key) {
		while(x != null) {
			int cmpLeft = key.compareTo(x.leftKey);

			if (cmpLeft == 0 || x.isLeaf()) return x;

			if (cmpLeft < 0) { x = x.leftChild; continue;}

			if (x.is3Node()) {
				int cmpRight = key.compareTo(x.rightKey);
				if (cmpRight == 0) return x;
				x = cmpRight < 0 ? x.midChild : x.rightChild;
			}
			else x = x.midChild;
		}
		return null;
	}



	public void put(K key, V value) {
		if (root == null) { root = new Node23<>(key, value); return; }

		Node23<K, V> leaf = search(root, key);

		if (key.equals(leaf.leftKey)) { leaf.leftValue = value; return; }
		else if (leaf.is3Node() && key.equals(leaf.rightKey)) { leaf.rightValue = value; return; }

		splitUp(leaf, key, value, null);
	}

	private void splitUp(Node23<K, V> x, K key, V value, Node23<K, V> newRightChild) {
		if (x.is2Node()) {
			addToNode(x, key, value, newRightChild);
			recalcN(x);
			return;
		}

		K smallKey, midKey, largeKey; V smallValue, midValue, largeValue;
		Node23<K, V> farLeftChild, midLeftChild, midRightChild, farRightChild;

		if (key.compareTo(x.leftKey) < 0) {
			smallKey = key; smallValue = value;
			midKey = x.leftKey; midValue = x.leftValue;
			largeKey = x.rightKey; largeValue = x.rightValue;
			farLeftChild = newRightChild; midLeftChild = x.leftChild;
			midRightChild = x.midChild; farRightChild = x.rightChild;
		}
		else if (key.compareTo(x.rightKey) < 0) {
			smallKey = x.leftKey; smallValue = x.leftValue;
			midKey = key; midValue = value;
			largeKey = x.rightKey; largeValue = x.rightValue; 
			farLeftChild = x.leftChild; midLeftChild = newRightChild;
			midRightChild = x.midChild; farRightChild = x.rightChild;
		}
		else {
			smallKey = x.leftKey; smallValue = x.leftValue;
			midKey = x.rightKey; midValue = x.rightValue;
			largeKey = key; largeValue = value;
			farLeftChild = x.leftChild; midLeftChild = x.midChild;
			midRightChild = newRightChild; farRightChild = x.rightChild;
		}

		x.leftKey = smallKey; x.leftValue = smallValue;
		x.rightKey = null; x.rightValue = null;
		x.leftChild = farLeftChild; x.midChild = midLeftChild; x.rightChild = null;
		if (farLeftChild != null) farLeftChild.parent = x;
		if (midLeftChild != null) midLeftChild.parent = x;
		recalcN(x);

		Node23<K, V> newRight = new Node23<>(largeKey, largeValue);
		newRight.leftChild = midRightChild; newRight.midChild = farRightChild;
		newRight.parent = x.parent;
		if (midRightChild != null) midRightChild.parent = newRight;
		if (farRightChild != null) farRightChild.parent = newRight;
		recalcN(newRight);

		if (x == root) {
			Node23<K, V> newRoot = new Node23<>(midKey, midValue);
			newRoot.leftChild = x; newRoot.midChild = newRight;
			x.parent = newRoot; newRight.parent = newRoot;
			root = newRoot;
			recalcN(x);
			return;
		}
		splitUp(x.parent, midKey, midValue, newRight);
	}

	private void addToNode(Node23<K, V> x, K key, V value, Node23<K, V> newRightChild) {
		if (key.compareTo(x.leftKey) < 0) {
			x.rightKey = x.leftKey; x.rightValue = x.leftValue;
			x.leftKey = key; x.leftValue = value;
			x.rightChild = x.midChild; x.midChild = newRightChild;
		}
		else {
			x.rightKey = key; x.rightValue = value;
			x.rightChild = newRightChild;
		}
		if (newRightChild != null) newRightChild.parent = x;
	}

	private void recalcN(Node23<K, V> x) {
		if (x == null) return;

		int count = (x.is3Node() ? 2 : 1);
		if (x.leftChild != null) count += x.leftChild.N;
		if (x.midChild != null) count += x.midChild.N;
		if (x.rightChild != null) count += x.rightChild.N;
		
		x.N = count;
		recalcN(x.parent);
	}



	public Iterable<K> keys() {
		if (root == null) return null;
		ArrayList<K> keyList = new ArrayList<K>();
		inorder(root, keyList);
		return keyList;
	}

	private void inorder(Node23<K,V> x, ArrayList<K> keyList) {
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
}

public class HW2 {

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