public class ArrayDeque<T> implements Deque<T> {

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

    @Override
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

    @Override
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

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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
