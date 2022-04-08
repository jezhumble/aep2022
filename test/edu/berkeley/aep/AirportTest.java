package edu.berkeley.aep;

import org.junit.Test;

import static org.junit.Assert.*;

public class AirportTest {

    static Airport a = new Airport();
    static Airport b = new Airport();
    static Airport c = new Airport();
    static Airport d = new Airport();
    static Airport e = new Airport();
    static Airport f = new Airport();
    static Airport g = new Airport();
    static Airport h = new Airport();

    static {
        h.addChild(b);
        b.addChild(a);
        a.addChild(f);
        b.addChild(c);
        c.addChild(d);
        c.addChild(e);
        c.addChild(e);
        d.addChild(e);
        e.addChild(b);
    }

    @Test
    public void shouldBeAbleToReachAirportFromSelf() {
        var a = new Airport();
        assertTrue(a.canReach(a));
    }

    @Test
    public void shouldNotBeAbleToReachAirportFromUnrelated() {
        var a = new Airport();
        var b = new Airport();
        assertFalse(a.canReach(b));
    }

    @Test
    public void shouldBeAbleToReachCFromH() {
        assertTrue(h.canReach(c));
    }

    @Test
    public void shouldNotBeAbleToReachGFromC() {
        assertFalse(c.canReach(g));
    }

    @Test
    public void hopsFromHToHShouldBeZero() {
        assertEquals(0, h.hopsTo(h));
    }

    @Test
    public void hopsFromHToCShouldBeTwo() {
        assertEquals(2, h.hopsTo(c));
    }

    @Test
    public void gShouldBeUnreachableFromC() {
        assertEquals(Airport.UNREACHABLE, c.hopsTo(g));
    }

    @Test
    public void eShouldBeOneHopFromC() {
        assertEquals(1, c.hopsTo(e));
    }
}
