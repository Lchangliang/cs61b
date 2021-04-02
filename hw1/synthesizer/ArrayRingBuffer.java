package synthesizer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        first = last = fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last++] = x;
        if (last == capacity) {
            last = 0;
        }
        fillCount++;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T result = rb[first++];
        if (first == capacity) {
            first = 0;
        }
        fillCount--;
        return result;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    private class ArrayRingBUfferIterator implements Iterator<T> {
        private int count;
        public ArrayRingBUfferIterator() { count = 0; }
        public boolean hasNext() {
            return count != fillCount;
        }
        public T next() {
            int curIndex = (first + count) % rb.length;
            T result = rb[curIndex];
            count++;
            return result;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayRingBUfferIterator();
    }
}

