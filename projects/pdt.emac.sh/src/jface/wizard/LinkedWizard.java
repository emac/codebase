/*
 * Created on Oct 29, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package jface.wizard;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author bishen
 */
public abstract class LinkedWizard extends Wizard
        implements ILinkedWizard
{

    private IWizard _previousWizard;
    private IWizard _nextWizard;

    /*
     * (non-Javadoc)
     * @see jface.wizard.ILinkedWizard#getNextWizard()
     */
    @Override
    public IWizard getNextWizard()
    {
        return this._nextWizard;
    }

    /*
     * (non-Javadoc)
     * @see jface.wizard.ILinkedWizard#getPreviousWizard()
     */
    @Override
    public IWizard getPreviousWizard()
    {
        return this._previousWizard;
    }

    /*
     * (non-Javadoc)
     * @see jface.wizard.ILinkedWizard#setNextWizard(org.eclipse.jface.wizard.IWizard)
     */
    @Override
    public void setNextWizard(IWizard wizard)
    {
        this._nextWizard = wizard;
    }

    /*
     * (non-Javadoc)
     * @see jface.wizard.ILinkedWizard#setPreviousWizard(jface.wizard.ILinkedWizard)
     */
    @Override
    public void setPreviousWizard(ILinkedWizard wizard)
    {
        this._previousWizard = wizard;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
     */
    @Override
    public IWizardPage getNextPage(IWizardPage page)
    {
        IWizardPage nextPage = super.getNextPage(page);

        if ( (nextPage == null) && this._nextWizard != null )
        {
            if ( this._nextWizard.getPageCount() == 0 )
            {
                this._nextWizard.addPages();
            }
            nextPage = this._nextWizard.getStartingPage();
        }

        return nextPage;
    }

    @Override
    public IWizardPage getPreviousPage(IWizardPage page)
    {
        IWizardPage prevPage = super.getPreviousPage(page);

        if ( (prevPage == null) && (this._previousWizard != null) )
        {
            if ( this._previousWizard.getPageCount() == 0 )
            {
                this._previousWizard.addPages();
            }
            if ( this._previousWizard.getPageCount() > 0 )
            {
                prevPage = this._previousWizard.getPages()[this._previousWizard.getPageCount() - 1];
            }
        }

        return prevPage;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages()
    {
        super.addPages();

        if ( this._nextWizard != null )
        {
            this._nextWizard.addPages();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    @Override
    public boolean canFinish()
    {
        if ( this._nextWizard != null )
        {
            return this._nextWizard.canFinish() && super.canFinish();
        }

        return super.canFinish();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#needsPreviousAndNextButtons()
     */
    @Override
    public boolean needsPreviousAndNextButtons()
    {
        int pageCount = getPageCount();

        // check previous wizard
        if ( this._previousWizard != null )
        {
            pageCount += this._previousWizard.getPageCount();
        }

        // check next wizard
        if ( this._nextWizard != null )
        {
            pageCount += this._nextWizard.getPageCount();

        }

        if ( pageCount == getPageCount() )
        {
            // no previous or next wizard
            return super.needsPreviousAndNextButtons();
        }

        // has previous or next wizard
        return pageCount > 1;
    }

}
