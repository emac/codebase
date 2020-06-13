/*
 * Created on Sep 27, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package generic;

/**
 * @author bishen
 */
public class GenericA {

    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        Object obj = new Object();
        return (T) obj;
    }

}
