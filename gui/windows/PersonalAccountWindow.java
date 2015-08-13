/**
 * Package for GUI Windows
 */
package gui.windows;

import java.awt.BorderLayout;

import javax.swing.JPopupMenu;
import javax.swing.JTable;

import model.AccountHandler;
import model.PersonalAccount;

/**
 * Sub Class of AccountWindow: Overrides setters and calls super class.
 * @author Barber, Carr, Coulby
 */
public class PersonalAccountWindow extends AccountWindow
{
    /**
     * Constructor for the PersonalAccountWindow; calls the super class,
     * which in turn sets all of the setters that are overridden in this class. 
     * @author Barber, Coulby
     * @param showStatusBar Boolean control over whether to show the status bar
     * (used when showing all accounts window, so a master status bar can be shown)
     */
    public PersonalAccountWindow(boolean showStatusBar)
    {
        super();
        if (showStatusBar)
        {
            add(statusBar, BorderLayout.SOUTH);
        }
    }

    @Override
    public void setColumnNames()
    {
        columnNames.add(columnOne);
        columnNames.add(columnTwo);
        columnNames.add(columnThree);
        columnNames.add("Card Type");
        columnNames.add("Card Number");
    }

    @Override
    public void setTable()
    {
        table = new JTable(AccountHandler.perAccTableModel);
    }

    @Override
    public void setAccount()
    {
        account = new PersonalAccount();
    }

    @Override
    protected void setAccountType()
    {
        accountType = "Personal";
    }

    @Override
    protected void setStatusBar()
    {
        statusBar();
    }

    @Override
    protected void removeCellEdit()
    {
        for (int i = 0; i < AccountHandler.getNoOfPersonalAccounts(); i++)
        {
            for (int j = 0; j < 5; j++)
            {
                table.isCellEditable(i, j);
            }
        }
    }

    @Override
    protected void setPopUpMenu()
    {
        rightClickMenu = new JPopupMenu();
        buildRightClickMenu();
    }

}