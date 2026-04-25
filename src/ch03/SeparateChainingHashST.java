package ch03;

import java.util.ArrayList;
import ch02.searchST.SequentialSearchST;

public class SeparateChainingHashST<K, V> {
    private int N;
    private int M;
    private SequentialSearchST<K, V>[] st;
    
    public SeparateChainingHashST() { this(997); }
    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<K, V>[]) new SequentialSearchST[M];
        for(int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<K, V>();
        }
    }
    public boolean contains(K key) { return get(K) != null; }
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    private int hash(K key) { return (key.hashCode() & 0x7fffffff) % M; }

    public V get(K key) { return st[hash(key)].get(key); }

    public void put(K key, V value) {
        if (!contains(key)) N++;
        st[hash(key)].put(key, value);
    }

    public void delete(K key) {
        if (!contains(key)) return;
        st[hash(key)].delete(key); N--;
    }

    public Iterable<K> keys() {
        ArrayList<K> keyList = new ArrayList<>(N);
        for(int i = 0; i < M; i++) {
            for(K key : st[i].keys()) {
                keyList.add(key);
            }
        }
        return keyList;
    }
}
