package ch02.searchST;

import java.util.ArrayList;

class Node<K, V>{
    K key; V value; Node<K, V> next;
    public Node(K key, V value, Node<K, V> next) {
        this.key = key; this.value = value; this.next = next;
    }
}

public class SequentialSearchST<K, V> {
    private Node<K, V> first;       // 첫 번째 노드에 대한 참조를 유지. 초기값 = null
    int N;      // 연결 리스트의 노드 수를 유지. 초기값 = 0

    public V get(K key) {
        for(Node <K, V> x = first; x != null; x = x.next) {
            if(key.equals(x.key)) return x.value;
        }
        return null;
    }

    public void put(K key, V value) {
        for(Node <K, V> x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first = new Node<K, V>(key, value, first);
        N++;
    }

    public void delete(K key) {
        if(key.equals(first.key)) {
            first = first.next; N--;
            return;
        }
        for(Node<K, V> x = first; x != null; x = x.next) {
            if (key.equals(x.next.key)) {
                x.next = x.next.next; N--;
                return;
            }
        }
    }

    public Iterable<K> keys() {
        ArrayList<K> keyList = new ArrayList<K>(N);
        for(Node<K, V> x = first; x != null; x = x.next) {
            keyList.add(x.key);
        }
        return keyList;
    }

    public boolean contains(K key) { return get(key) != null; }
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
}
