/*
 * Created on Jan 13, 2011 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package thread;

import org.junit.jupiter.api.Test;
import thread.ObservableSetWithAlienMethod.SetObserver;

import java.util.ConcurrentModificationException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author bishen
 */
public class ObservableSetWithAlienMethodTests {

    @Test
    public void testAlienMethod() {
        ObservableSetWithAlienMethod<Integer> set = new ObservableSetWithAlienMethod<Integer>(new HashSet<Integer>());
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSetWithAlienMethod<Integer> set, Integer element) {
                System.out.println(element);
                if (element.intValue() == 10) {
                    set.removeObserver(this);
                }
            }
        });

        assertThrows(ConcurrentModificationException.class, () -> {
            for (int i = 0; i < 100; i++) {
                set.add(i);
            }
        });
    }
}
