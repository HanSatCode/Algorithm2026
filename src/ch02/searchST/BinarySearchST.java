package ch02.searchST;

import java.util.ArrayList;

public class BinarySearchST <K extends Comparable<K>, V>{
    private static final int INIT_CAPACITY = 10;
    private K[] keys;   // 키의 배열
    private V[] vals;   // 값의 배열
    private int N;

    public BinarySearchST() {
        keys = (K[]) new Comparable[INIT_CAPACITY];
        vals = (V[]) new Object[INIT_CAPACITY];
    }

    public BinarySearchST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Object[capacity];
    }

    public boolean contains(K key) { return get(key) != null; }
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    
    private void resize(int capacity) {
        K[] tempk = (K[]) new Comparable[capacity];
        V[] tempv = (V[]) new Object[capacity];
        for(int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        keys = tempk;
        vals = tempv;
    }

    private int search(K key) {
        int lo = 0;
        int hi = N - 1;
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(keys[mid]);
            
            if(cmp < 0) hi = mid - 1;
            if(cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public V get(K key) {
        if (isEmpty()) return null;
        int i = search(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    public void put(K key, V val) {
        int i = search(key);
        
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        if(N == keys.length) resize(2 * keys.length);

        for(int j = N; j > i; j--) {
            keys[j] = keys[j-1]; vals[j] = vals[j-1];       // 추가될 곳의 공간 확보
        }
        
        keys[i] = key; vals[i] = val; N++;
    }

    public void delete(K key) {
        if (isEmpty()) return;
        int i = search(key);
        if(i == N || keys[i].compareTo(key) != 0) return;

        for(int j = i; j < N-1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        } N--;
        keys[N] = null; vals[N] = null;
        if(N > INIT_CAPACITY && N == keys.length/4) {
            resize(keys.length/2);
        }
    }

    public Iterable<K> keys() {
        ArrayList<K> keyList = new ArrayList<K>(N);
        for(int i = 0; i < N; i++) keyList.add(keys[i]);
        return keyList;
    }
}
