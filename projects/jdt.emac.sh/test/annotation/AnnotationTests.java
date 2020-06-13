/*
 * Created on Jan 12, 2011 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author bishen
 */
public class AnnotationTests {

    @AnnotationA(expectedException = RuntimeException.class)
    public void throwRuntimeException() {
        throw new RuntimeException();
    }

    @Test
    public void testAnnotationTest() {
        for (Method m : AnnotationTests.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(AnnotationA.class)) {
                try {
                    m.invoke(null);
                } catch (Exception ex) {
                    assertTrue(m.getAnnotation(AnnotationA.class).expectedException().isInstance(ex));
                }
            }
        }
    }
}
