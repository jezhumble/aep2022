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
        Path path = pathTo(destination, new HashSet<>(), Path.HOPS_STRATEGY);
        return path.hops();
    }

    public int costTo(Airport destination) {
        Path path = pathTo(destination, new HashSet<>(), Path.COST_STRATEGY);
        return path.cost();
    }

    public Path pathTo(Airport destination, HopComparisonStrategy strategy) {
        return pathTo(destination, new HashSet<>(), strategy);
    }

    Path pathTo(Airport other, Set<Airport> visited, HopComparisonStrategy strategy) {
        if (!visited.add(this)) return Path.UNREACHABLE;
        if (this.equals(other)) return new Path();
        Path champion = Path.UNREACHABLE;
        for (var child: children) {
            Path hops = child.pathTo(other, new HashSet<>(visited), strategy);
            if (hops.compareTo(champion, strategy) < 0) {
                champion = hops;
            }
        }
        return champion;
    }

}
