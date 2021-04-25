package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class KDTreeTest {

    @Test
    public void RandomTest() {

        Random random = new Random(System.currentTimeMillis());

        int pointSum = 20;
        List<Point> points = new ArrayList<>(pointSum);
        for (int i = 0; i < pointSum; i++) {
            points.add(new Point(random.nextDouble()*10, random.nextDouble()*10));
        }

        int testSum = 10;
        List<Point> testPoints = new ArrayList<>(testSum);
        for (int i = 0; i < testSum; i++) {
            testPoints.add(new Point(random.nextDouble()*10, random.nextDouble()*10));
        }

        KDTree kdTree = new KDTree(points);
        NaivePointSet nps = new NaivePointSet(points);

        List<Point> kdTreeResult = new ArrayList<>(testSum);
        List<Point> npsResult = new ArrayList<>(testSum);
        for (Point point : testPoints) {
            kdTreeResult.add(kdTree.nearest(point.getX(), point.getY()));
            npsResult.add(nps.nearest(point.getX(), point.getY()));
        }

        assertEquals(npsResult, kdTreeResult);
    }
}
