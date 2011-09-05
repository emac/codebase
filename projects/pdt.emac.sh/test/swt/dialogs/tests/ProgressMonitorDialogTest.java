/*
 * Created on Jul 13, 2010
 *
 * Copyright (c) eBay, Inc. 2010   
 * All rights reserved.                                    
 */

package swt.dialogs.tests;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author bishen
 *
 */
public class ProgressMonitorDialogTest
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ProgressMonitorDialog dlg = new ProgressMonitorDialog(null);
        dlg.open();
        dlg.setBlockOnOpen(true);
        
        Display.getCurrent().dispose();
    }

}
