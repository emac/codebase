/*
 * Created on Jan 13, 2011 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author bishen
 */
public class ObservableSetWithAlienMethod<E>
{

    private Set<E>               set;
    private List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();

    public ObservableSetWithAlienMethod(Set<E> set)
    {
        this.set = set;
    }

    public void addObserver(SetObserver<E> observer)
    {
        synchronized (this.observers)
        {
            this.observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer)
    {
        synchronized (this.observers)
        {
            return this.observers.remove(observer);
        }
    }

    public boolean add(E element)
    {
        boolean added = this.set.add(element);
        if ( added )
        {
            notifyElementAdded(element);
        }

        return added;
    }

    /**
     * Error: Never invoke any alien methods in a synchronized block.
     * Best Practice: Do as less as possible in a synchronized block.
     * 
     * @param element
     */
    private void notifyElementAdded(E element)
    {
        synchronized (this.observers)
        {
            for (SetObserver<E> o : this.observers)
            {
                o.added(this, element);
            }
        }
    }

    public interface SetObserver<E>
    {
        void added(ObservableSetWithAlienMethod<E> set, E element);
    }
    
}
