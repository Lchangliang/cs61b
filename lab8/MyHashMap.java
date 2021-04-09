import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private double loadFactor;
    private List<List<Node>> map;
    private int size;
    private Set<K> keySet;

    private class Node {
        K key;
        V value;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.loadFactor = loadFactor;
        this.map = new ArrayList<>();
        for (int i = 0; i < initialSize; i++) {
            this.map.add(new LinkedList<>());
        }
        this.size = 0;
        keySet = new HashSet<>();
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        size = 0;
        for (int i = 0; i <  map.size(); i++) {
            map.get(i).clear();
        }
        keySet.clear();
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (containsKey(key)) {
            int hashCode = (Objects.hashCode(key) & 0x7fffffff) % map.size();
            for (Node node : map.get(hashCode)) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    private void resize() {
        MyHashMap<K, V> temp = new MyHashMap<>(2 * map.size(), loadFactor);
        for (List<Node> nodes : map) {
            for (Node node : nodes) {
                temp.put(node.key, node.value);
            }
        }
        this.map = temp.map;
    }
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        int hashCode = (Objects.hashCode(key) & 0x7fffffff) % map.size();
        if (containsKey(key)) {
            for (Node node : map.get(hashCode)) {
                if (node.key == key) {
                    node.value = value;
                }
            }
        } else {
            Node node = new Node(key, value);
            map.get(hashCode).add(node);
            size++;
            keySet.add(key);
            if (((double) size / map.size()) > loadFactor) {
                resize();
            }
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        return new HashSet<>(keySet);
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int hashCode = (Objects.hashCode(key) & 0x7fffffff) % map.size();
        List<Node> list = map.get(hashCode);
        V result = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).key == key) {
                result = list.remove(i).value;
                break;
            }
        }
        return result;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        if (!containsKey(key)) {
            return null;
        }
        int hashCode = (Objects.hashCode(key) & 0x7fffffff) % map.size();
        List<Node> list = map.get(hashCode);
        V result = null;
        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            if (node.key == key && node.value == value) {
                list.remove(i);
                result = node.value;
            }
        }
        return result;
    }
    public class Pair implements Map.Entry {
        K key;
        V value;
        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public Object getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public Object setValue(Object value) {
            V oldValue = this.value;
            this.value = (V) value;
            return oldValue;
        }
    }

    private class MyHashMapIterator implements Iterator<K> {
        private Iterator<K> iterator;
        public MyHashMapIterator() { iterator = keySet.iterator(); }
        public boolean hasNext() {
            return iterator.hasNext();
        }
        public K next() {
            return iterator.next();
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }
}
