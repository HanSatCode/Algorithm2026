package ch03;

public class LinearProbingHashST<K, V> {
    private int N;
    private int M;
    private K[] keys;
    private V[] vals;

    public LinearProbingHashST() { this(997); }
    public LinearProbingHashST(int M) {
         this.M = M;
         keys = (K[]) new Object[M]; vals = (V[]) new Object[M];
    }

    public boolean contains(K key) { return get(key) != null; }
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    private int hash(K key) { return (key.hashCode() & 0x7fffffff) % M; }

    public V get(K key) {
        for(int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if(key.equals(keys[i])) return vals[i];
        }
        return null;
    }

    public void put(K key, V value) {
        if (N >= M / 2) resize(2 * M + 1);
        int i;
        for(i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (key.equals(keys[i])) { vals[i] = value; return; }
        }
        keys[i] =  key; vals[i] = value; N++;
    }

    public void delete(K key) {
        if (!contains(key)) return;
        
        int i = hash(key);
        while(!key.equals(keys[i])) { i = (i + 1) % M; }
        keys[i] = null; vals[i] = null;
        
        i = (i + 1) % M;
        while(keys[i] != null) {
            K keyToRefresh = keys[i]; V valToRefresh = vals[i];
            keys[i] = null; vals[i] = null;
            put(keyToRefresh, valToRefresh);
            i = (i + 1) % M;
        }
        N--;
    }

    private void resize(int cap) {
        LinearProbingHashST<K, V> t = new LinearProbingHashST<>(cap);
        for(int i = 0; i < M; i++) {
            if (keys[i] != null) t.put(keys[i], vals[i]);
        }
        keys = t.keys;  vals = t.vals;  M = t.M;
    }
}
