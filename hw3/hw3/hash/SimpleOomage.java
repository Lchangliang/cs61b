package hw3.hash;
import java.awt.Color;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;


public class SimpleOomage implements Oomage {
    protected int red;
    protected int green;
    protected int blue;

    private static final double WIDTH = 0.01;
    private static final boolean USE_PERFECT_HASH = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleOomage that = (SimpleOomage) o;
        return red == that.red && green == that.green && blue == that.blue;
    }

    /* Uncomment this method after you've written
       equals and failed the testHashCodeAndEqualsConsistency
       test. */
    @Override
    public int hashCode() {
        if (!USE_PERFECT_HASH) {
            return red + green + blue;
        } else {
            int newRed = red / 5;
            int newGreen = green / 5;
            int newBlue = blue / 5;
            int result = 0;
            result += result * 255 + newRed;
            result += result * 255 + newGreen;
            result += result * 255 + newBlue;
            return result;
        }
    }

    public SimpleOomage(int r, int g, int b) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException();
        }
        if ((r % 5 != 0) || (g % 5 != 0) || (b % 5 != 0)) {
            throw new IllegalArgumentException("red/green/blue values must all be multiples of 5!");
        }
        red = r;
        green = g;
        blue = b;
    }

    @Override
    public void draw(double x, double y, double scalingFactor) {
        StdDraw.setPenColor(new Color(red, green, blue));
        StdDraw.filledSquare(x, y, WIDTH * scalingFactor);
    }

    public static SimpleOomage randomSimpleOomage() {
        int red = StdRandom.uniform(0, 51) * 5;
        int green = StdRandom.uniform(0, 51) * 5;
        int blue = StdRandom.uniform(0, 51) * 5;
        return new SimpleOomage(red, green, blue);
    }

//    public static void main(String[] args) {
//        System.out.println("Drawing 4 random simple Oomages.");
//        randomSimpleOomage().draw(0.25, 0.25, 1);
//        randomSimpleOomage().draw(0.75, 0.75, 1);
//        randomSimpleOomage().draw(0.25, 0.75, 1);
//        randomSimpleOomage().draw(0.75, 0.25, 1);
//    }

    public String toString() {
        return "R: " + red + ", G: " + green + ", B: " + blue;
    }

    public static void main(String[] args) {
        SimpleOomage o1 = new SimpleOomage(0, 0, 155);
        SimpleOomage o2 = new SimpleOomage(0, 5, 0);
        System.out.println(o1.hashCode());
        System.out.println(o2.hashCode());
    }
} 
