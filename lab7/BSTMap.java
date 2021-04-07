import java.util.Iterator;
import java.util.Set;
import java.lang.Iterable;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>, Iterable<K>  {

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    /** Removes all of the mappings from this map. */
    public void clear() {
        size = 0;
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        Node curNode = root;
        while (curNode != null) {
            if (key.compareTo(curNode.key) == 0) {
                return true;
            } else if (key.compareTo(curNode.key) > 0) {
                curNode = curNode.right;
            } else {
                curNode = curNode.left;
            }
        }
        return false;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        Node curNode = root;
        while (curNode != null) {
            if (key.compareTo(curNode.key) == 0) {
                return curNode.value;
            } else if (key.compareTo(curNode.key) > 0) {
                curNode = curNode.right;
            } else {
                curNode = curNode.left;
            }
        }
        return null;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    private Node put(Node curNode, K key, V value) {
        if (curNode == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(curNode.key) == 0) {
            curNode.value = value;
        } else if (key.compareTo(curNode.key) > 0) {
            curNode.right = put(curNode.right, key, value);
        } else {
            curNode.left = put(curNode.left, key, value);
        }
        return curNode;
    }
    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        Node node = put(root, key, value);
        if (root == null) {
            root = node;
        }
    }

    private void keySetInOrder(Set<K> set, Node node) {
        if (node == null) {
            return ;
        }
        keySetInOrder(set, node.left);
        set.add(node.key);
        keySetInOrder(set, node.right);
    }
    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        keySetInOrder(set, root);
        return set;
    }

    private boolean find(List<Node> list, K key) {
        Node parent = null;
        Node cur = root;
        while (cur != null) {
            if (cur.key.compareTo(key) == 0) {
                list.add(cur);
                list.add(parent);
                return true;
            } else if (cur.key.compareTo(key) < 0) {
                parent = cur;
                cur = cur.right;
            } else {
                parent = cur;
                cur = cur.left;
            }
        }
        return false;
    }

    private void remove(List<Node> list) {
        Node removeNode = list.get(0);
        Node parent = list.get(1);
        if (removeNode.left == null) {
            if (parent == null) {
                root = removeNode.right;
            } else {
                if (parent.left == removeNode) {
                    parent.left = removeNode.right;
                } else {
                    parent.right = removeNode.right;
                }
            }
        } else if (removeNode.right == null) {
            if (parent == null) {
                root = removeNode.left;
            } else {
                if (parent.left == removeNode) {
                    parent.left = removeNode.left;
                } else {
                    parent.right = removeNode.left;
                }
            }
        } else {
            Node changeNode = removeNode.right;
            parent = removeNode;
            while (changeNode.left != null) {
                parent = changeNode;
                changeNode = changeNode.left;
            }
            removeNode.key = changeNode.key;
            removeNode.value = changeNode.value;
            if (parent == removeNode) {
                parent.right = changeNode.right;
            } else {
                parent.left = changeNode.right;
            }
        }
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        List<Node> list = new ArrayList<>();
        if (find(list, key)) {
            remove(list);
            size--;
            return list.get(0).value;
        }
        return null;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        List<Node> list = new ArrayList<>();
        if (find(list, key)) {
            if (list.get(0).value.equals(value)) {
                remove(list);
                size--;
                return list.get(0).value;
            }
        }
        return null;
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("unsupported operation iterator");
    }

    private void printInOrder(Node node) {
        if (node == null) {
            return ;
        }
        printInOrder(node.left);
        System.out.println(node.key + ": " + node.value);
        printInOrder(node.right);
    }
    public void printInOrder() {
        printInOrder(root);
    }

}
