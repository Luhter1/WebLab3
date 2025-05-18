import org.junit.jupiter.api.Test;
import org.itmo.manageBean.AreaChecker;

import static org.junit.jupiter.api.Assertions.*;

public class AreaBeanTest {

    @Test
    public void testCheckHitFirstQuadrant() {
        AreaChecker bean = new AreaChecker();
        assertTrue(bean.IsHit(-1,1, 4));
    }

    @Test
    public void testCheckHitSecondQuadrant() {
        AreaChecker bean = new AreaChecker();
        assertTrue(bean.IsHit(-0.5, -0.5, 2));
    }

    @Test
    public void testCheckHitThirdQuadrant() {
        AreaChecker bean = new AreaChecker();
        assertTrue(bean.IsHit(2, -1, 3));
    }
}