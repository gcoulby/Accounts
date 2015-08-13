/**
 * Package for GUI Windows
 */
package gui.windows;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Account;
import model.AccountHandler;

/**
 * The AccountWindowClass is an abstract super class
 * This class handles all of the Account display such as
 * tables, status bars, title bars, columns etc.
 * It also handles the deletion of Accounts via the
 * means of a right click context menu
 * @author Barber, Carr, Coulby
 *
 */
public abstract class AccountWindow extends JPanel
{
    protected Account account;
    protected Vector<String> columnNames = new Vector<String>();
    protected JTable table;

    protected String columnOne = "Account Number";
    protected String columnTwo = "First Name";
    protected String columnThree = "Surname";
    protected String accountType;
    protected JLabel noOfAccounts;
    protected JLabel perStatusBarLabel = new JLabel();
    protected JPanel statusBar = new JPanel();
    protected JPopupMenu rightClickMenu = new JPopupMenu();
    protected JMenuItem deleteItem = new JMenuItem("Delete");

    /**
     * The super Constructor for subclasses.
     * This constructor has all of the setter methods ready to
     * be called by the subclasses when the sub class calls
     * super(); this reduces the complexity of the subclass.
     */
    public AccountWindow()
    {
        setLayout(new BorderLayout());
        setVisible(true);
        setColumnNames();
        setAccount();
        setAccountType();
        setTable();
        setStatusBar();
        setPopUpMenu();
        add(topBar(accountType), BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        removeCellEdit();
    }

    /**
     * Sets the Status bar  with a JLabel that is updated dynamically
     * to show the number of accounts
     * @author Barber, Coulby
     */
    public void statusBar()
    {
        String numberOfAccounts = String.valueOf((accountType.equals("Trade") ? AccountHandler.getNoOfTradeAccounts() : AccountHandler.getNoOfPersonalAccounts()));
        JPanel jp = new JPanel();
        jp.setBorder(BorderFactory.createEtchedBorder());
        JLabel jl = new JLabel("Total Number of " + accountType + " Accounts: ");
        noOfAccounts = new JLabel(numberOfAccounts);
        jp.add(jl);
        jp.add(noOfAccounts);
        statusBar = jp;
    }

    /**
     * Adds the title bar above the Account Table indicating
     * which table they are.
     * @author Barber, Coulby
     * @param accountType The Account Type Associated with the 
     * sub class that made the call 
     * @return jp The JPanel for the topBar
     */
    public JPanel topBar(String accountType)
    {
        JPanel jp = new JPanel();
        jp.setBorder(BorderFactory.createEtchedBorder());
        JLabel jl = new JLabel(accountType + " Accounts");
        jl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        jp.add(jl);
        return jp;
    }

    /**
     * Sets the columnNames Vector
     * (Must be overridden by all sub classes)
     * @author Carr
     */
    protected abstract void setColumnNames();

    /**
     * Sets the table
     * (Must be overridden by all sub classes)
     * @author Carr
     */
    protected abstract void setTable();

    /**
     * Sets the Account
     * (Must be overridden by all sub classes)
     * @author Carr
     */
    protected abstract void setAccount();

    /**
     * Sets the Account Type
     * (Must be overridden by all sub classes)
     * @author Carr
     */
    protected abstract void setAccountType();

    /**
     * Sets the Status Bar
     * (Must be overridden by all sub classes)
     * @author Carr
     */
    protected abstract void setStatusBar();

    /**
     * Sets the cell to be uneditable
     * (Must be overridden by all sub classes)
     * @author Carr
     */
    protected abstract void removeCellEdit();

    /**
     * Sets the Right Click PopUpMenu
     * (Must be overridden by all sub classes)
     * @author Carr
     */
    protected abstract void setPopUpMenu();

    /**
     * Returns the full name of the person a given row
     * @author Carr
     * @param row The row in which to get values from
     * @return String Full Name as a String
     */
    public String getFullName(int row)
    {
        return table.getValueAt(row, 0) + " " + table.getValueAt(row, 1);
    }

    /**
     * Returns the Account for the object that makes the call
     * @author Carr
     * @return account The Account to be returned
     */
    public Account getAccount()
    {
        return account;
    }

    /**
     * Returns the table for the object that makes the call
     * @author Carr
     * @return table The JTable to be returned
     */
    public JTable getTable()
    {
        return table;
    }

    /**
     * Updates the JLabel in the status bar with the number of accounts
     * associated with the sub class that made the call.
     * @author Carr
     * @param numberOfAccounts The Number of Accounts associated to the
     * subclass making the call
     */
    public void updateStatusBar(int numberOfAccounts)
    {
        noOfAccounts.setText(String.valueOf(numberOfAccounts));
        removeCellEdit();
    }

    /**
     * Builds the right click menu for the JTable
     * Triggers an action that deletes an account from the
     * JTable Vector and the Account associated with it
     * @author Barber, Coulby
     */
    public void buildRightClickMenu()
    {
        rightClickMenu.add(deleteItem);
        table.setComponentPopupMenu(rightClickMenu);
        table.addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    int row = table.rowAtPoint(e.getPoint());
                    table.getSelectionModel().setSelectionInterval(row, row);
                    if (e.getButton() == MouseEvent.BUTTON3)
                    {
                        rightClickMenu.show(table, e.getX(), e.getY());
                    }
                }
            });

        deleteItem.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    int row = table.getSelectedRow();
                    String accountNo = String.valueOf(table.getValueAt(row, 0));
                    String surname = String.valueOf(table.getValueAt(row, 2));
                    AccountHandler.deleteAccount(surname, accountNo);
                }
            });
    }

}
