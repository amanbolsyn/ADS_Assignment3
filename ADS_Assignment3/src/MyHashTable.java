public class MyHashTable<K, V> {
    private class HashNode<K,V>{
        private K key;
        private V value;
        private HashNode<K, V> next;
        public HashNode(K key, V value){
            this.key = key; // assigning key
            this.value = value; // assigning value
        }

        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11; // Number of buckets


    private int size;


    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }

    private int computeHashCode(int[] arr) {
        if (arr == null) {
            return 0;
        }

        int hash = 1;
        for (int i = 0; i < arr.length; i++) {
            hash += 51 * hash + arr[i];
        }

        return hash;
    }



    private int computeHashCode(String s) {
        if (s == null) {
            return 0;
        }

        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = hash * 31 + s.charAt(i);
        }
        return hash;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    private int hash(int[] arr) {
        return Math.abs(computeHashCode(arr)) % M;
    }

    private int hash(String s) {
        return Math.abs(computeHashCode(s)) % M;
    }


    public void put(K key, V value){
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);


        if(chainArray[index] == null){
            chainArray[index] = newNode;
            size++;
            return;
        }

        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> lastNode = null;
        while(current != null){
            if(current.key.equals(key)){
                current.value = value;
                return;
            }
            lastNode = current;
            current = current.next;
        }

        if(lastNode != null){
            lastNode.next = newNode;
            size++;
        }

    }


    public V get(K key){
        int index = hash(key);
        HashNode<K,V> current = chainArray[index];
        while(current != null){
            if(current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
    public V remove(K key){
        int index = hash(key);
        HashNode<K,V> current = chainArray[index];
        HashNode<K,V> lastNode = null;
        while(current != null){
            if(current.key.equals(key)){
                if(lastNode != null){
                    lastNode.next = current.next;
                }else{
                    chainArray[index] = current.next;
                }
                size--;
                return current.value;
            }
            lastNode = current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(V value){
        for(int i = 0; i < chainArray.length; i++){
            HashNode<K,V> current = chainArray[i];
            while(current != null){
                if(current.value.equals(value)){
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }


    public K getKey(V value){

        for(int i = 0; i < chainArray.length; i++){

            HashNode<K,V> current = chainArray[i];

            while(current != null){
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }


        }

        return null;
    }



}
