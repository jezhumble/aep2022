package edu.berkeley.aep;

// Understands how to calculate the cost and hops between two airports
public class Path {

    public static HopComparisonStrategy COST_STRATEGY = (first, second) -> Integer.compare(first.cost, second.cost);
    public static HopComparisonStrategy HOPS_STRATEGY = (first, second) -> Integer.compare(first.hops, second.hops);

    public static Path UNREACHABLE = new Path(Integer.MAX_VALUE, Integer.MAX_VALUE) {
        @Override
        public int compareTo(Path other, HopComparisonStrategy strategy) {
            return 1; // always bigger!
        }

        @Override
        public int cost() {
            return Airport.UNREACHABLE;
        }

        @Override
        public Path add(int cost) {
            return this;
        }

        @Override
        public int hops() {
            return Airport.UNREACHABLE;
        }
    };

    private final int cost;
    private final int hops;

    public Path() {
        this.cost = 0;
        this.hops = 0;
    }

    private Path(int cost, int hops) {
        this.cost = cost;
        this.hops = hops;
    }

    public int cost() {
        return cost;
    }

    public Path add(int cost) {
        return new Path(this.cost + cost, this.hops + 1);
    }

    public int compareTo(Path other, HopComparisonStrategy strategy) {
        return strategy.compareTo(this, other);
    }

    public int hops() {
        return hops;
    }
}
