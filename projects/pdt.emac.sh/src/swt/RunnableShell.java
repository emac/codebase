/*
 * Created on 2013-5-9 Copyright (c) eBay, Inc. 2013 All rights reserved.
 */

package swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * @author bishen
 */
public class RunnableShell
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Display display = Display.getDefault();
        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());
        new RunnableShell().createContents(shell);
        shell.open();
        shell.layout();
        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    private void createContents(Composite parent)
    {
        new Label(parent, SWT.NONE).setText("Hello, world!");
    }

}
