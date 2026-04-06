import java.util.ArrayList;

public class BST <K extends Comparable<K>, V> {
    class Node<K, V> {
        int N;      // 자손 노드 + 1 (ordered 연산)
        int aux;    // AVL 트리나 RB 트리에서 사용
        Node<K, V> parent;

        K key; V value; Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key; this.value = value;
            this.N = 1;
        }

        public int getAux() { return aux; }
        public void setAux(int value) { this.aux = value; }
    }

    protected Node<K, V> root;

    protected Node<K, V> treeSearch(K key) {
        Node<K, V> x = root;
        while(true) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            else if (cmp < 0) {
                if (x.left == null) return x;
                else x = x.left;
            }
            else {
                if (x.right == null) return x;
                else x = x.right;
            }
        }
    }

    public int size() { return (root != null ? root.N : 0); }
    public V get(K key) {
        if (root == null) return null;
        Node<K, V> x = treeSearch(key);
        if(key.equals(x.key)) return x.value;
        else return null;
    }

    public void put(K key, V val) {
        if (root == null) { root = new Node<K, V>(key, val); return; }
        Node<K, V> x = treeSearch(key);
        int cmp = key.compareTo(x.key);
        if(cmp == 0) x.value = val;
        else {
            Node<K, V> newNode = new Node<K, V>(key, val);
            if (cmp < 0) x.left = newNode;
            else x.right = newNode;
            newNode.parent = x;
            rebalanceInsert(newNode);

        }
    }

    protected void rebalanceInsert(Node<K,V> x) {
        resetSize(x.parent, 1);
    }

    protected void rebalanceDelete(Node<K, V> p, Node<K, V> deleted) {
        resetSize(p, -1);
    }

    private void resetSize(Node<K, V> x, int value) {
        for(; x != null; x = x.parent) x.N += value;
    }

    public Iterable<K> keys() {
        if (root == null) return null;
        ArrayList<K> keyList = new ArrayList<K>(size());
        inorder(root, keyList);
        return keyList;
    }

    private void inorder(Node<K, V> x, ArrayList<K> keyList) {
        if (x != null) {
            inorder(x.left, keyList);
            keyList.add(x.key); 
            inorder(x.right, keyList);
        }
    }

    public void delete(K key) {
        if (root == null) return;
        Node<K, V> x, y, p;
        x = treeSearch(key);

        if(!key.equals(x.key)) return;

        // 루트이거나 자식이 2개인 노드
        if(x == root || isTwoNode(x)) {
            if (isLeaf(x)) {    // 루트가 리프인 경우
                root = null; return;
            }
            else if (!isTwoNode(x)) {   // 루트의 자식이 하나
                root = (x.right == null) ? x.left : x.right;    // 자식을 루트로
                root.parent = null;
                return;
            }
            else {  // 자식이 둘인 경우 (루트 포함)
                y = min(x.right);   // x보다 다음 큰 것 (inOrder Successor)
                x.key = y.key; x.value = y.value;
                p = y.parent;
                relink(p, y.right, y == p.left);    // y의 자식을 p의 자식으로 (절대로 왼쪽 자식이 없음!!)
                rebalanceDelete(p, y);
            }
        }
        else {  // 자식이 1개보다 적거나, 루트가 아닌 경우
            p = x.parent;
            if(x.right == null) relink(p, x.left, x == p.left);
            else if (x.left == null) relink(p, x.right, x == p.right);
            rebalanceDelete(p, x);
        }
    }

    public boolean contains(K key) { return get(key) != null; }
    public boolean isEmpty() { return root == null; }

    protected boolean isLeaf(Node<K, V> x) {
        return (x.left == null && x.right == null);
    }

    protected boolean isTwoNode(Node<K, V> x) {
        return (x.left != null && x.right != null);
    }

    protected void relink(Node<K, V> parent, Node<K, V> child, boolean makeLeft) {
        if (child != null) child.parent = parent;
        if (makeLeft) parent.left = child;      // 왼쪽 자식
        else parent.right = child;      // 오른쪽 자식
    }

    protected Node<K, V> min(Node<K, V> x) { while(x.left != null) x = x.left; return x;}

    protected K min() {
        if (root == null) return null;
        Node<K, V> x = root;
        while(x.left != null) { x = x.left; }
        return x.key;
    }

    protected K max() {
        if (root == null) return null;
        Node<K, V> x = root;
        while(x.right != null) { x = x.right; }
        return x.key;
    }

    public K floor(K key) { // key보다 작거나 같은 키들 중에서 제일 큰 키
        if (root == null || key == null) return null;
        Node<K, V> x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node<K, V> floor(Node<K, V> x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;     // keyy와 동일한 키를 가진 노드
        if (cmp < 0) return floor(x.left, key);     // key보다 크다면 왼쪽으로..
        Node<K, V> t = floor(x.right, key);     // key가 클 경우, 오른쪽으로..
        if (t != null) return t;        // 오른쪽에 작은 키가 있을 경우
        else return x;                  // 오른쪽에 작은 키가 없을 경우
    }

    public int rank(K key) {    // key보다 작은 키의 수
        if (root == null || key == null) return 0;
        Node<K, V> x = root;
        int num = 0;
        while(x != null) {      // 루트부터 비교하면서, key보다 작은 키의 수를 합산
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) {     
                num += 1 + size(x.left);    // 왼쪽 서브트리의 노드 수를 합산
                x = x.right;    // 오른쪽 서브트리도 검사
            }
            else {  // x인 경우? 왼쪽 서브트리만 합산
                num += size(x.left); break;
            }
        }
        return num;
    }

    private int size(Node<K, V> x) { return (x != null) ? x.N : 0; }

    public K select(int rank) { // rank 등수에 해당하는 키를 반환
        if (root == null || rank < 0 || rank >= size()) return null;
        Node<K, V> x = root;
        while (true) {
            int t = size(x.left);
            if (rank < t) x = x.left;   // 왼쪽 서브트리의 노드수보다 rank보다 크면
            else if (rank > t) {    // 왼쪽 서브트리의 노드수보다 rank가 크면
                rank = rank - t - 1;    // 왼쪽 서브트리와 루트를 제외하고
                x = x.right;    // 오른쪽 서브트리 조사
            }
            else return x.key;  // 왼쪽 서브트리 수와 rank가 일치하면 키 반환
        }
    }
}