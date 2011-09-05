/*
 * Created on Jul 5, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package swt.viewers;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;

/**
 * Implements a common {@code ViewerComparator} used in a simple {@code TableViewer} that has been set {@code
 * ITableLabelProvider} or {@code ColumnLabelProvider}s.
 * 
 * @author bishen
 */
public class TableViewerComparator extends ViewerComparator
{

    protected static final int ASC           = 1;
    protected static final int DESC          = -1;
    protected static final int NONE          = 0;
    protected static final int INVALID_INDEX = -1;

    protected TableViewer      _viewer;
    private MODE               _mode         = null;
    private boolean            _canCompare   = false;
    int                        _direction    = NONE;
    int                        _colIndex     = INVALID_INDEX;

    /**
     * Represents two comparator mode: one for table viewer with a single {@code ITableLabelProvider}, the other for
     * table viewer with a {@code ColumnLabelProvider} per column.
     * 
     * @author bshen
     */
    private enum MODE
    {
        TABLE, CELL;
    }

    public TableViewerComparator(TableViewer viewer)
    {
        this._viewer = viewer;
        if ( canCompare() )
        {
            this._canCompare = true;
            hookColListners();
        }
    }

    private boolean canCompare()
    {
        int colCount = this._viewer.getTable().getColumnCount();
        if ( colCount == 0 )
        {
            return false;
        }

        if ( this._viewer.getLabelProvider() instanceof ITableLabelProvider )
        {
            this._mode = MODE.TABLE;
        }
        else
        {
            this._mode = MODE.CELL;
        }

        return true;
    }

    private void hookColListners()
    {
        final TableColumn[] cols = this._viewer.getTable().getColumns();
        for (TableColumn element : cols)
        {
            element.addSelectionListener(new SelectionAdapter()
            {
                @Override
                public void widgetSelected(SelectionEvent e)
                {
                    TableColumn col = (TableColumn) e.widget;
                    if ( (TableViewerComparator.this._colIndex != INVALID_INDEX)
                            && (col == cols[TableViewerComparator.this._colIndex]) )
                    {
                        switch (TableViewerComparator.this._direction)
                        {
                            case ASC:
                                TableViewerComparator.this._direction = DESC;
                                TableViewerComparator.this._viewer.getTable().setSortDirection(SWT.DOWN);
                                break;
                            case DESC:
                                TableViewerComparator.this._direction = NONE;
                                TableViewerComparator.this._colIndex = INVALID_INDEX;
                                TableViewerComparator.this._viewer.getTable().setSortColumn(null);
                                TableViewerComparator.this._viewer.getTable().setSortDirection(SWT.NONE);
                                break;
                            case NONE:
                                TableViewerComparator.this._direction = ASC;
                                TableViewerComparator.this._colIndex = getColIndex(col, cols);
                                TableViewerComparator.this._viewer.getTable().setSortColumn(
                                        cols[TableViewerComparator.this._colIndex]);
                                TableViewerComparator.this._viewer.getTable().setSortDirection(SWT.UP);
                                break;
                        }
                    }
                    else
                    {
                        TableViewerComparator.this._colIndex = getColIndex(col, cols);
                        TableViewerComparator.this._direction = ASC;
                        TableViewerComparator.this._viewer.getTable().setSortColumn(
                                cols[TableViewerComparator.this._colIndex]);
                        TableViewerComparator.this._viewer.getTable().setSortDirection(SWT.UP);
                    }
                    TableViewerComparator.this._viewer.refresh();
                }
            });
        }
    }

    int getColIndex(TableColumn col, TableColumn[] cols)
    {
        int idx = INVALID_INDEX;
        for (int i = 0; i < cols.length; i++)
        {
            if ( col == cols[i] )
            {
                idx = i;
                break;
            }
        }

        return idx == INVALID_INDEX ? 0 : idx;
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2)
    {
        if ( this._canCompare )
        {
            return this._direction * getCmprColText(e1).compareToIgnoreCase(getCmprColText(e2));
        }

        return super.compare(viewer, e1, e2);
    }

    public int getCompareColumnIndex()
    {
        return this._colIndex;
    }

    public int getCompareColumnDirection()
    {
        return this._direction;
    }

    private String getCmprColText(Object e)
    {
        if ( this._mode == MODE.TABLE )
        {
            return ((ITableLabelProvider) this._viewer.getLabelProvider()).getColumnText(e, this._colIndex);
        }
        else if ( this._mode == MODE.CELL
                && this._viewer.getLabelProvider(this._colIndex) instanceof ColumnLabelProvider )
        {
            return ((ColumnLabelProvider) this._viewer.getLabelProvider(this._colIndex)).getText(e);
        }

        return ""; //$NON-NLS-1$
    }

}
