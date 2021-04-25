package bearmaps;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private List<Node> minPQ;
    private Map<T, Integer> keySet;

    private class Node {
        T item;
        double priority;
        public Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
    }

    public ArrayHeapMinPQ() {
        minPQ = new ArrayList<>(32);
        minPQ.add(null);
        keySet = new HashMap<>();
    }

    private void floating(int currentIndex) {
        while (currentIndex > 1) {
            int parent = currentIndex / 2;
            if (minPQ.get(parent).priority > minPQ.get(currentIndex).priority) {
                Node tmp = minPQ.get(parent);
                minPQ.set(parent, minPQ.get(currentIndex));
                minPQ.set(parent, tmp);
                currentIndex = parent;
                keySet.put(minPQ.get(currentIndex).item, currentIndex);
            } else {
                break;
            }
        }
    }

    public void add(T item, double priority) {
        if (keySet.containsKey(item)) {
            throw new IllegalArgumentException();
        }
        keySet.put(item, minPQ.size());
        Node node = new Node(item, priority);
        minPQ.add(node);
        floating(minPQ.size() - 1);
    }

    public boolean contains(T item) {
        return keySet.containsKey(item);
    }

    public T getSmallest() {
        if (minPQ.size() == 1) {
            throw new NoSuchElementException();
        }
        return minPQ.get(1).item;
    }

    private void sink(int currentIndex) {
        while (2 * currentIndex < minPQ.size()) {
            int minIndex = 2 * currentIndex;
            if (2 * currentIndex + 1 < minPQ.size() &&
                    minPQ.get(minIndex).priority > minPQ.get(2*currentIndex+1).priority) {
                minIndex = 2 * currentIndex + 1;
            }
            if (minPQ.get(currentIndex).priority < minPQ.get(minIndex).priority) {
                break;
            } else {
                Node tmp = minPQ.get(minIndex);
                minPQ.set(minIndex, minPQ.get(currentIndex));
                minPQ.set(currentIndex, tmp);
                currentIndex = minIndex;
                keySet.put(minPQ.get(currentIndex).item, currentIndex);
            }
        }
    }

    public T removeSmallest() {
        if (minPQ.size() == 1) {
            throw new NoSuchElementException();
        }
        T remove = minPQ.get(1).item;
        Node tail = minPQ.remove(minPQ.size()-1);
        keySet.put(tail.item, 1);
        minPQ.set(1, tail);
        sink(1);
        return remove;
    }

    public int size() {
        return minPQ.size() - 1;
    }

    public void changePriority(T item, double priority) {
        if (!keySet.containsKey(item)) {
            throw new NoSuchElementException();
        }
        Integer index = keySet.get(item);
        if (minPQ.get(index).priority > priority) {
            minPQ.get(index).priority = priority;
            floating(index);
        } else if (minPQ.get(index).priority < priority){
            minPQ.get(index).priority = priority;
            sink(index);
        }
    }
}
