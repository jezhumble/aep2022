package edu.berkeley.aep;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Understands how to reach other airports
public class Airport {

    public static final int UNREACHABLE = -1;
    List<Route> children = new ArrayList<>();

    public boolean canReach(Airport other) {
        return hopsTo(other) != UNREACHABLE;
    }

    public void addChild(Airport child, int cost) {
        children.add(new Route(child, cost));
    }

    public int hopsTo(Airport destination) {
        return hopsTo(destination, new HashSet<>(), Route.HOP_STRATEGY);
    }

    public int costTo(Airport destination) {
        return hopsTo(destination, new HashSet<>(), Route.COST_STRATEGY);
    }

    int hopsTo(Airport other, Set<Airport> visited, HopStrategy strategy) {
        if (!visited.add(this)) return UNREACHABLE;
        if (this.equals(other)) return 0;
        int champion = Integer.MAX_VALUE;
        for (var child: children) {
            int hops = child.hopsTo(other, new HashSet<>(visited), strategy);
            if (hops != UNREACHABLE && hops < champion) {
                champion = hops;
            }
        }
        return champion == Integer.MAX_VALUE ? UNREACHABLE : champion;
    }
}
