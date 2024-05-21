public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;
    private class Node{
        private K key;
        private V value;
        private Node left, right;
        public Node( K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.value = val; // update the value if key already exists
        }
        return node;
    }

    public V get(K key){
        return get( root, key);
    }

    private V get(Node node, K key){
        while(node != null){
            int cmp = key.compareTo(node.key);
            if( cmp < 0) node = node.left;
            else if(cmp > 0) node = node.right;
            else return node.value;
        }
        return null;
    }
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        return node;
    }


    private Node min(Node node) {
        if (node.left == null) return node;
        else return min(node.left);
    }
    private Node deleteMin( Node x ){
        if( x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;

    }
    public int size(){
        return size;
    }



}
