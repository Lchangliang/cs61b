package es.datastructur.synthesizer;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(5);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        try {
            arb.enqueue(1);
            assertFalse(true);
        } catch (Exception e) {
            assertTrue(true);
        }
        Integer expect = 1;
        assertEquals(expect, arb.peek());
        Integer i = 1;
        Iterator<Integer> iterator = arb.iterator();
        while (iterator.hasNext()) {
            Integer actual = iterator.next();
            assertEquals(i, actual);
            i++;
        }
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        try {
            arb.dequeue();
            assertFalse(true);
        } catch (Exception e) {
            assertTrue(true);
        }
        try {
            arb.peek();
            assertFalse(true);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<Integer> expect = new ArrayRingBuffer<Integer>(5);
        expect.enqueue(1);
        expect.enqueue(2);
        expect.enqueue(3);
        expect.enqueue(4);
        expect.enqueue(5);
        ArrayRingBuffer<Integer> actual1 = new ArrayRingBuffer<Integer>(5);
        actual1.enqueue(1);
        actual1.enqueue(2);
        actual1.enqueue(3);
        actual1.enqueue(4);
        ArrayRingBuffer<Integer> actual2 = new ArrayRingBuffer<Integer>(5);
        actual2.enqueue(1);
        actual2.enqueue(2);
        actual2.enqueue(3);
        actual2.enqueue(4);
        actual2.enqueue(6);
        ArrayRingBuffer<Integer> actual3 = new ArrayRingBuffer<Integer>(5);
        actual3.enqueue(1);
        actual3.enqueue(2);
        actual3.enqueue(3);
        actual3.enqueue(4);
        actual3.enqueue(5);
        assertNotEquals(expect, actual1);
        assertNotEquals(expect, actual2);
        assertEquals(expect, actual3);
    }
}
