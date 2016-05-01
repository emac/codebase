package lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Emac
 * @since 2016-03-21
 */
public class SynchronizationTests {

    @Test
    public void testHello(){
        ClassU u = new ClassU();
        Assert.assertEquals(2, u.hello());

        ClassT t = u;
        Assert.assertEquals(2, t.hello());
    }

    public static class ClassT {
        public int hello(){
            return 1;
        }
    }

    public static class ClassU extends ClassT {
        public synchronized int hello(){
            return super.hello() + 1;
        }
    }
}
