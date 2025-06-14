package test.java.org.itmo;

import org.junit.Test;
import org.itmo.manageBean.AreaChecker;

import static org.junit.Assert.*;

public class AreaBeanTest {
    @Test
    public void testCheckHitFirstQuadrant() {
        assertTrue(AreaChecker.IsHit(-1, 1, 4));
    }

    @Test
    public void testCheckHitSecondQuadrant() {
        assertTrue(AreaChecker.IsHit(-0.5, -0.5, 2));
    }

    @Test
    public void testCheckHitThirdQuadrant() {
        assertTrue(AreaChecker.IsHit(2, -1, 3));
    }
}