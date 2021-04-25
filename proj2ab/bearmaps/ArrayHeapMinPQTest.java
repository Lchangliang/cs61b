package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {

    @Test
    public void baseTese() {
        ArrayHeapMinPQ<String> minPQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 10; i++) {
            minPQ.add(String.valueOf(i), i * 10.0);
        }
        assertEquals("0", minPQ.getSmallest());
        assertTrue(minPQ.contains("5"));
        minPQ.changePriority("0", 1000);
        assertEquals("1", minPQ.removeSmallest());
        assertEquals(9, minPQ.size());
    }
}
