package lang;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Emac
 * @since 2016-03-21
 */
public class SynchronizationTests {

    @Test
    public void testHello() {
        ClassU u = new ClassU();
        assertEquals(2, u.hello());

        ClassT t = u;
        assertEquals(2, t.hello());
    }

    public static class ClassT {
        public int hello() {
            return 1;
        }
    }

    public static class ClassU extends ClassT {
        @Override
        public synchronized int hello() {
            return super.hello() + 1;
        }
    }
}
