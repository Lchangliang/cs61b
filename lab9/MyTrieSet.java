import java.util.ArrayList;
import java.util.List;

public class MyTrieSet implements TrieSet61B {

    private static final int R = 256;

    private static class Node {
        private boolean isTrie;
        private Node[] next = new Node[R];
    }

    private Node root;

    MyTrieSet() {
        root = new Node();
    }

    public void clear() {
        for (int i = 0; i < root.next.length; i++) {
            root.next[i] = null;
        }
    }

    private boolean contains(Node node, String key, int index) {
        if (key.length() == index) {
            return node.isTrie;
        }
        if (node.next[key.charAt(index)] != null) {
            return contains(node.next[key.charAt(index)], key, index+1);
        } else {
            return false;
        }
    }

    public boolean contains(String key) {
        return contains(root, key, 0);
    }

    private void add(Node node, String key, int index) {
        if (key.length() == index) {
            node.isTrie = true;
            return ;
        }
        if (node.next[key.charAt(index)] == null) {
            node.next[key.charAt(index)] = new Node();
        }
        add(node.next[key.charAt(index)], key, index+1);
    }

    public void add(String key) {
        add(root, key, 0);
    }

    private Node getPrefixNode(Node node, String prefix, int index) {
        if (prefix.length() == index) {
            return node;
        }
        if (node.next[prefix.charAt(index)] != null) {
            return getPrefixNode(node.next[prefix.charAt(index)], prefix, index+1);
        } else {
            return null;
        }
    }

    private void getKeys(Node node, String prefix, List<String> keys) {
        for (int i = 0; i < 256; i++) {
            if (node.next[i] != null) {
                if (node.next[i].isTrie) {
                    keys.add(prefix + (char)i);
                }
                getKeys(node.next[i], prefix + (char)i, keys);
            }
        }
    }

    public List<String> keysWithPrefix(String prefix) {
        Node node = getPrefixNode(root, prefix, 0);
        if (node == null) {
            return null;
        }
        List<String> result = new ArrayList<>();
        getKeys(node, prefix, result);
        return result;
    }

    private String longestPrefixOf(Node node, String key, int index) {
        if (key.length() == index) {
            return "";
        }
        if (node.next[key.charAt(index)] != null) {
            return key.charAt(index) + longestPrefixOf(node.next[key.charAt(index)], key, index + 1);
        } else {
            return "";
        }
    }

    public String longestPrefixOf(String key) {
        return longestPrefixOf(root, key, 0);
    }

}
