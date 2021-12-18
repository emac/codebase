package lang;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.ref.WeakReference;

/**
 * @author Emac
 * @date 2021/12/18
 */
public class ReferenceTests {

    @Test
    public void testWeakReference() {
        WeakReferenceList wrl = new WeakReferenceList();
        String s1 = new String("Hello, world!");
        wrl.add(s1);

        WeakReference<String> wr = wrl.get(0);
        Assertions.assertNotNull(wr.get());

        s1 = null;
        System.gc();
        Assertions.assertNull(wr.get());
    }
}
