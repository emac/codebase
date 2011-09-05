/*
 * Created on Oct 29, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package jface.wizard;

import org.eclipse.jface.wizard.IWizard;

/**
 * @author bishen
 */
public interface ILinkedWizard
        extends IWizard
{

    /**
     * Returns next wizard.
     * 
     * @return
     */
    public IWizard getNextWizard();

    /**
     * Sets next wizard.
     * 
     * @param wizard
     */
    public void setNextWizard(IWizard wizard);

    /**
     * Returns previous wizard.
     * 
     * @return
     */
    public IWizard getPreviousWizard();

    /**
     * Sets previous wizard.
     * 
     * @param wizard
     */
    public void setPreviousWizard(ILinkedWizard wizard);

}
