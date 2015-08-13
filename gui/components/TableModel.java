/**
 * Package for GUI components
 */
package gui.components;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * a custom TableModel subclass, extended from AbstractTableModel
 * for holding our database of account objects.
 * created to better handle table updating 
 * @author Barber, Coulby
 * 
 */
public class TableModel extends AbstractTableModel
{

    private Vector<Object> accounts;
    private Vector<String> colNames;

    /**
     * Constructor which populates the private fields.
     * @param accounts Vector of Type Object to be added
     * @param colNames Vector of Type String to be added
     */
    public TableModel(Vector<Object> accounts, Vector<String> colNames)
    {
        this.accounts = accounts;
        this.colNames = colNames;
    }

    @Override
    public int getRowCount()
    {
        return accounts.size();
    }

    /**
     * Adds and account Object to the table and then fires a data changed event
     * @param account The Object to be added to the TableModel
     */
    public void add(Object account)
    {
        accounts.add(account);
        fireTableDataChanged();
    }

    /**
     * Removes and account Object to the table and then fires a data changed event
     * @param account The Object to be removed to the TableModel
     */
    public void remove(Object account)
    {
        if (accounts.contains(account))
        {
            accounts.remove(account);
            fireTableDataChanged();
        }
    }

    @Override
    public int getColumnCount()
    {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return "";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

}