import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testRandom() {
        String errorMessage = "\n";
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        int i = 0;
        while (i < 1000) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                sad1.addLast(i);
                ads1.addLast(i);
                errorMessage += "addLast(" + i + ")\n";
                i++;
            } else if (numberBetweenZeroAndOne >= 0.25 && numberBetweenZeroAndOne < 0.5) {
                sad1.addFirst(i);
                ads1.addFirst(i);
                errorMessage += "addFirst(" + i + ")\n";
                i++;
            } else if (numberBetweenZeroAndOne >= 0.5 && numberBetweenZeroAndOne < 0.75) {
                if (!ads1.isEmpty()) {
                    Integer item1 = sad1.removeFirst();
                    Integer item2 = ads1.removeFirst();
                    errorMessage += "removeFirst()\n";
                    if (item2 == null) {
                        assertNull(errorMessage, item1);
                    } else {
                        assertEquals(errorMessage, item2, item1);
                    }
                }
            } else if (numberBetweenZeroAndOne >= 0.75){
                if (!ads1.isEmpty()) {
                    Integer item1 = sad1.removeLast();
                    Integer item2 = ads1.removeLast();
                    errorMessage += "removeLast()\n";
                    if (item2 == null) {
                        assertNull(errorMessage, item1);
                    } else {
                        assertEquals(errorMessage, item2, item1);
                    }
                }
            }
        }
    }
}
