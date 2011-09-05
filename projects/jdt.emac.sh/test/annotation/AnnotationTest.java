/*
 * Created on Jan 12, 2011 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package annotation;

import java.lang.reflect.Method;

import junit.framework.Assert;

import org.junit.Test;

import annotation.AnnotationA;

/**
 * @author bishen
 */
public class AnnotationTest
{

    @AnnotationA(expectedException = RuntimeException.class)
    public void throwRuntimeException()
    {
        throw new RuntimeException();
    }

    @Test
    public void testAnnotationTest()
    {
        for (Method m : AnnotationTest.class.getDeclaredMethods())
        {
            if ( m.isAnnotationPresent(AnnotationA.class) )
            {
                try
                {
                    m.invoke(null);
                }
                catch (Exception ex)
                {
                    Assert.assertTrue(m.getAnnotation(AnnotationA.class).expectedException().isInstance(ex));
                }
            }
        }
    }

}
