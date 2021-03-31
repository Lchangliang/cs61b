public class ArrayDeque<T> {

    private int capacity;
    private int length;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        this.capacity = 8;
        items = (T[]) new Object[this.capacity];
        length = 0;
        nextFirst = capacity / 2;
        nextLast = capacity / 2 + 1;
    }

    private void update(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        int newNextFirst = newCapacity / 2;
        int newNextLast = newCapacity / 2 + 1;
        int curIndex = nextFirst;
        for (int i = 0; i < length; i++) {
            curIndex++;
            if (curIndex == this.capacity) {
                curIndex = 0;
            }
            newItems[newNextLast++] = items[curIndex];
            if (newNextLast == newCapacity) {
                newNextLast = 0;
            }
        }
        this.items = newItems;
        this.capacity = newCapacity;
        this.nextFirst = newNextFirst;
        this.nextLast = newNextLast;
    }

    private void expandCapacity() {
        int newCapacity = this.capacity * 2;
        update(newCapacity);
    }

    private void reduceCapacity() {
        int newCapacity = this.capacity / 2;
        update(newCapacity);
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (length == capacity) {
            expandCapacity();
        }
        items[nextFirst--] = item;
        length++;
        if (nextFirst == -1) {
            nextFirst = capacity - 1;
        }
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (length == capacity) {
            expandCapacity();
        }
        items[nextLast++] = item;
        length++;
        if (nextLast == capacity) {
            nextLast = 0;
        }
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
        int first = nextFirst + 1;
        if (first == capacity) {
            first = 0;
        }
        while (first != nextLast) {
            System.out.print(items[first]);
            System.out.print(' ');
            first += 1;
            if (first == capacity) {
                first = 0;
            }
        }
    }

    /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        ++nextFirst;
        if (nextFirst == capacity) {
            nextFirst = 0;
        }
        length--;
        T result = items[nextFirst];
        if (capacity > 8 && length <= capacity / 4) {
            reduceCapacity();
        }
        return result;
    }

    /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        --nextLast;
        if (nextLast == -1) {
            nextLast = capacity - 1;
        }
        length--;
        T result = items[nextLast];
        if (capacity > 8 && length <= capacity / 4) {
            reduceCapacity();
        }
        return result;
    }

    /* Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque! */
    public T get(int index) {
        if (length <= index) {
            return null;
        }
        int cur = nextFirst;
        for (int i = 0; i <= index; i++) {
            cur++;
            if (cur == capacity) {
                cur = 0;
            }
        }
        return items[cur];
    }
}
