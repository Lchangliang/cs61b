import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    private PriorityQueue<Flight> startQueue;
    private PriorityQueue<Flight> endQueue;
    public FlightSolver(ArrayList<Flight> flights) {
        Comparator<Flight> compareStart = (flight1, flight2) -> {
            return flight1.startTime() - flight2.startTime();
        };
        Comparator<Flight> compareEnd = (flight1, flight2) -> {
            int endDiff = flight1.endTime() - flight2.endTime();
            return endDiff;
        };
        startQueue = new PriorityQueue<>(compareStart);
        endQueue = new PriorityQueue<>(compareEnd);
        for (Flight flight : flights) {
            startQueue.add(flight);
            endQueue.add(flight);
        }
    }

    public int solve() {
        int count = 0;
        int max = 0;
        while (!startQueue.isEmpty()) {
            Flight startFlight = startQueue.peek();
            Flight endFlight = endQueue.peek();
            if (startFlight.startTime() <= endFlight.endTime()) {
                count += startQueue.poll().passengers();
                if (count > max) {
                    max = count;
                }
            } else {
                count -= endQueue.poll().passengers();
            }
        }
        return max;
    }

}
