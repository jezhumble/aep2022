package edu.berkeley.aep;

import java.util.HashSet;

// Understands the cost of travelling between two airports
public class Route {
    private final Airport child;
    private final int cost;

    public Route(Airport child, int cost) {
        this.child = child;
        this.cost = cost;
    }

    public Path pathTo(Airport other, HashSet<Airport> airports, HopComparisonStrategy strategy) {
        Path pathTo = child.pathTo(other, airports, strategy);
        return pathTo.add(cost);
    }
}
