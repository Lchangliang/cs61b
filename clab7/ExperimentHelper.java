/**
 * Created by hug.
 */
import edu.princeton.cs.introcs.StdRandom;

import java.util.List;
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int depth = 0;
        int OIPL = 0;
        int number = 0;
        for (int i = 0; i < N; i++) {
            OIPL += depth;
            number++;
             if (number == Math.pow(2, depth)) {
                depth++;
                number = 0;
            }
        }
        return OIPL;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return optimalIPL(N) / N;
    }

    public static void randomDeleteTakingSuccessor(BST bst, List list) {
        int delete = StdRandom.uniform(list.size());
        String key = (String) list.get(delete);
        bst.deleteTakingSuccessor(key);
        String newKey = "cat";
        newKey = StringUtils.randomString(3);
        list.set(delete, newKey);
        bst.add(newKey);
    }

    public static void randomDeleteTakingRandom(BST bst, List list) {
        int delete = StdRandom.uniform(list.size());
        String key = (String) list.get(delete);
        bst.deleteTakingRandom(key);
        String newKey = "cat";
        newKey = StringUtils.randomString(3);
        list.set(delete, newKey);
        bst.add(newKey);
    }
}
