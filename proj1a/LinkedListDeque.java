public class LinkedListDeque<T> {

    private class Node {
        T item;
        Node next;
        Node prev;
        public Node(T item) {
            this.item = item;
        }
    }

    private Node head;
    private int length;

    public LinkedListDeque() {
        head = new Node(null);
        head.prev = head;
        head.next = head;
        length = 0;
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        Node node = new Node(item);
        Node first = null;
        if (head.next == head) {
            first = head;
        } else {
            first = head.next;
        }
        head.next = node;
        first.prev = node;
        node.next = first;
        node.prev = head;
        length++;
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        Node node = new Node(item);
        Node last = null;
        if (head.prev == head) {
            last = head;
        } else {
            last = head.prev;
        }
        head.prev = node;
        last.next = node;
        node.prev = last;
        node.next = head;
        length++;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return length == 0;
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return length;
    }

    /* Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node cur = head.next;
        while (cur != head) {
            System.out.print(cur.item);
            System.out.print(' ');
            cur = cur.next;
        }
    }

    /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node oldFirst = head.next;
        Node newFirst = oldFirst.next;
        head.next = newFirst;
        newFirst.prev = head;
        length--;
        return oldFirst.item;
    }

    /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node oldLast = head.prev;
        Node newLast = oldLast.prev;
        head.prev = newLast;
        newLast.next = head;
        length--;
        return oldLast.item;
    }

    /* Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque! */
    public T get(int index) {
        if (length <= index) {
            return null;
        }
        Node cur = head;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.item;
    }

    private T getRecursive(Node node, int index) {
        if (node == head && index >= 0) {
            return null;
        }
        if (index == 0) {
            return node.item;
        }
        return getRecursive(node.next, index - 1);
    }

    public T getRecursive(int index) {
        return getRecursive(head.next, index);
    }
}
