/*
 * Created on Jan 13, 2011 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package thread;

import java.util.ConcurrentModificationException;
import java.util.HashSet;

import org.junit.Test;

import thread.ObservableSetWithAlienMethod;
import thread.ObservableSetWithAlienMethod.SetObserver;

/**
 * @author bishen
 */
public class ObservableSetWithAlienMethodTest
{

    @Test(expected = ConcurrentModificationException.class)
    public void testAlienMethod()
    {
        ObservableSetWithAlienMethod<Integer> set = new ObservableSetWithAlienMethod(new HashSet<Integer>());
        set.addObserver(new SetObserver<Integer>()
        {
            @Override
            public void added(ObservableSetWithAlienMethod<Integer> set, Integer element)
            {
                System.out.println(element);
                if ( element.intValue() == 10 )
                {
                    set.removeObserver(this);
                }
            }
        });

        for (int i = 0; i < 100; i++)
        {
            set.add(i);
        }
    }

}
