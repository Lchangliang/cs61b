package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Color color() {
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public void move() {
        energy -= 0.03;
    }

    public void stay() {
        energy -= 0.01;
    }

    public Clorus replicate() {
        energy /= 2;
        Clorus clorus = new Clorus(energy);
        return clorus;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        for (Map.Entry<Direction, Occupant> entry: neighbors.entrySet()) {
            Direction d = entry.getKey();
            Occupant o = entry.getValue();
            if (o.name().equals("empty")) {
                emptyNeighbors.add(d);
            } else if (o.name().equals("plip")) {
                plipNeighbors.add(d);
            }
        }

        // Rule 1
        if (!plipNeighbors.isEmpty()) {
            Direction d = HugLifeUtils.randomEntry(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, d);
        }

        // Rule 2
        if (energy >= 1.0 && !emptyNeighbors.isEmpty()) {
            Direction d = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, d);
        }

        // Rule 3
        if (!emptyNeighbors.isEmpty()) {
            Direction d = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.MOVE, d);
        }

        return new Action(Action.ActionType.STAY);
    }
}
