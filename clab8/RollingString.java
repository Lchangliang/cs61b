import java.util.List;
import java.util.LinkedList;
import java.util.Objects;

/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString{

    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;
    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;
    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */

    private int hashCode;
    private List<Character> list;
    private int delta = 1;

    public RollingString(String s, int length) {
        assert(s.length() == length);
        list = new LinkedList<Character>();
        for (int i = 1; i < length; i++) {
            if (i == length-1) {
                delta = delta * UNIQUECHARS;
            } else {
                delta = delta * UNIQUECHARS % PRIMEBASE;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            list.add(s.charAt(i));
            if (i == 0) {
                hashCode = s.charAt(i) * UNIQUECHARS % PRIMEBASE;
            } else if (i == 1) {
                hashCode = hashCode + s.charAt(i) % PRIMEBASE;
            } else {
                hashCode = (hashCode * UNIQUECHARS % PRIMEBASE + s.charAt(i)) % PRIMEBASE;
            }
        }
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        list.add(c);
        char remove = list.remove(0);
        hashCode = ((hashCode + PRIMEBASE - remove * delta % PRIMEBASE) * UNIQUECHARS + c) % PRIMEBASE;
    }


    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() {
        StringBuilder strb = new StringBuilder();
        for (Character c : list) {
            strb.append(c);
        }
        return strb.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        return list.size();
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RollingString that = (RollingString) o;
        if (this.list.size() != that.list.size()) {
            return false;
        }
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i) != that.list.get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        return hashCode;
    }

    public static void main(String[] args) {
        String a = new String("abr");
        RollingString rs = new RollingString(a, 3);
        rs.addChar('a');
        return ;
    }
}
