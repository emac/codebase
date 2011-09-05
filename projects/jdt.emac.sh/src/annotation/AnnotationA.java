/*
 * Created on Jan 12, 2011 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author bishen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AnnotationA
{

    Class<? extends Exception> expectedException();

}
