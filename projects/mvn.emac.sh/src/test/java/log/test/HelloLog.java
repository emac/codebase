/*
 * Created on Jun 24, 2010
 *
 * Copyright (c) eBay, Inc. 2010   
 * All rights reserved.                                    
 */

package log.test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.Filer;

/**
 * @author bishen
 *
 */
public class HelloLog implements Filer
{

    public static Logger log = Logger.getLogger(HelloLog.class);
    
    public static void main(String[] args){
        
    }

    /* (non-Javadoc)
     * @see org.apache.xmlbeans.Filer#createBinaryFile(java.lang.String)
     */
    public OutputStream createBinaryFile(String arg0)
            throws IOException
    {
        return null;
    }

    /* (non-Javadoc)
     * @see org.apache.xmlbeans.Filer#createSourceFile(java.lang.String)
     */
    public Writer createSourceFile(String arg0)
            throws IOException
    {
        return null;
    }
    
}
