/*
 * Created on Oct 29, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package jface.wizard.test;

import jface.wizard.LinkedWizard;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author bishen
 */
public class LinkedWizardTest
{

    private static final String NEXT_WIZARD_ID = "org.eclipse.ui.wizards.new.project";

    @Test
    public void testOpen()
            throws CoreException
    {
        MyLinkedWizard myWiz = new MyLinkedWizard();

        IWizardDescriptor nextWizDscr = WorkbenchPlugin.getDefault().getNewWizardRegistry().findWizard(NEXT_WIZARD_ID);
        Assert.assertNotNull(nextWizDscr);
        INewWizard nextWiz = (INewWizard) nextWizDscr.createWizard();
        nextWiz.init(PlatformUI.getWorkbench(), new StructuredSelection());

        myWiz.setNextWizard(nextWiz);

        WizardDialog dlg = new WizardDialog(null, myWiz);
        dlg.open();
    }

    private static class MyLinkedWizard extends LinkedWizard
    {

        public MyLinkedWizard()
        {

        }

        /*
         * (non-Javadoc)
         * @see org.eclipse.jface.wizard.Wizard#getWindowTitle()
         */
        @Override
        public String getWindowTitle()
        {
            return "My Wizard";
        }

        /*
         * (non-Javadoc)
         * @see jface.wizard.LinkedWizard#addPages()
         */
        @Override
        public void addPages()
        {
            super.addPages();

            addPage(new WizardPage("My Page")
            {

                /*
                 * (non-Javadoc)
                 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
                 */
                @Override
                public void createControl(Composite parent)
                {
                    Composite cmp = new Composite(parent, SWT.NONE);
                    cmp.setLayout(new GridLayout());
                    cmp.setLayoutData(new GridData(GridData.FILL_BOTH));
                    new Label(cmp, SWT.NONE).setText("I'm a label.");
                    setControl(cmp);
                }

                /*
                 * (non-Javadoc)
                 * @see org.eclipse.jface.dialogs.DialogPage#getTitle()
                 */
                @Override
                public String getTitle()
                {
                    return "My Page";
                }

            });
        }

        /*
         * (non-Javadoc)
         * @see org.eclipse.jface.wizard.Wizard#performFinish()
         */
        @Override
        public boolean performFinish()
        {
            return true;
        }

    }

}
