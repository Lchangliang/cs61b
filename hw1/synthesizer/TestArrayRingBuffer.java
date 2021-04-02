package synthesizer;
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
        i = 1;
        for (Integer value : arb) {
            assertEquals(i, value);
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
}
