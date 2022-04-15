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

    public int hopsTo(Airport other, HashSet<Airport> airports) {
        int hopsTo = child.hopsTo(other, airports);
        if (hopsTo == Airport.UNREACHABLE) return Airport.UNREACHABLE;
        return hopsTo + 1;
    }

    public int costTo(Airport other, HashSet<Airport> airports) {
        int costTo = child.costTo(other, airports);
        if (costTo == Airport.UNREACHABLE) return Airport.UNREACHABLE;
        return costTo + cost;
    }
}
