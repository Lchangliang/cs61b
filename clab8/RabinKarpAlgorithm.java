public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        if (input.length() < pattern.length()) {
            return -1;
        }
        int patternLength = pattern.length();
        RollingString patternRS = new RollingString(pattern, patternLength);
        RollingString matchRS = new RollingString(input.substring(0, patternLength), patternLength);
        for (int i = patternLength; i <= input.length(); i++) {
            if (matchRS.hashCode() == patternRS.hashCode()) {
                if (matchRS.equals(patternRS)) {
                    return i - patternLength;
                }
            }
            if (i != input.length()) {
                matchRS.addChar(input.charAt(i));
            }
        }
        return -1;
    }

}
