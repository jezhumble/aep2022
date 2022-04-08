package edu.berkeley.aep;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Airport {

    public static final int UNREACHABLE = -1;
    List<Airport> children = new ArrayList<>();

    public boolean canReach(Airport other) {
        return hopsTo(other) != UNREACHABLE;
    }

    public void addChild(Airport child) {
        children.add(child);
    }

    public int hopsTo(Airport destination) {
        return hopsTo(destination, new HashSet<>());
    }

    private int hopsTo(Airport other, Set<Airport> visited) {
        if (!visited.add(this)) return UNREACHABLE;
        if (this.equals(other)) return 0;
        int champion = Integer.MAX_VALUE;
        for (var child: children) {
            int hops = child.hopsTo(other, new HashSet<>(visited));
            if (hops != UNREACHABLE && (hops + 1) < champion) {
                champion = hops + 1;
            }
        }
        return champion == Integer.MAX_VALUE ? UNREACHABLE : champion;
    }
}
