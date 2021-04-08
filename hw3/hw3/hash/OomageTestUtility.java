package hw3.hash;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < M; i++) {
            map.put(i, 0);
        }
        int N = oomages.size();
        for (Oomage oomage : oomages) {
            int hashCode = (oomage.hashCode() & 0x7FFFFFFF) % M;
            Integer time = map.get(hashCode);
            map.put(hashCode, ++time);
        }
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (entry.getValue() < (double) N / 50 || entry.getValue() > (double) N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
