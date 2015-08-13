/**
 * Package for GUI components
 */
package gui.components;

import gui.windows.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * The MenuBar Class Builds the JMenuBar; 
 * with menus, menuItems, Mnemonics, Accelerators, as well as ActionListeners.
 * @author Barber, Coulby, Carr
 */
public class MenuBar extends JMenuBar
{
    private JDialog aboutBox;
    private JMenuItem home = new JMenuItem("Home");
    private JMenuItem allAccounts = new JMenuItem("All Accounts");
    private JMenuItem trdAccounts = new JMenuItem("Trade Accounts");
    private JMenuItem perAccounts = new JMenuItem("Personal Accounts");
    private JMenuItem addAccount = new JMenuItem("Account");

    /**
     * Constructor for the MenuBar Class
     * Builds the JMenuBar; with menus, menuItems, Mnemonics and Accelerators.
     * @author Barber, Coulby
     * @param frame The parent JFrame in which to display message display messages to.
     */
    public MenuBar(final JFrame frame)
    {

        //=========File Menu=========\\
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        //=========Show Menu=========\\
        JMenu showMenu = new JMenu("Show");

        showMenu.add(home);
        showMenu.addSeparator();
        showMenu.add(allAccounts);
        showMenu.add(trdAccounts);
        showMenu.add(perAccounts);

        //=========Add Accounts Menu=========\\
        JMenu addAccountsMenu = new JMenu("Add");

        addAccount.setActionCommand("Plus");
        addAccountsMenu.add(addAccount);

        //=========Windows Menu=========\\
        JMenu windowMenu = new JMenu("Window");

        JMenuItem aboutItem = new JMenuItem("About");
        windowMenu.addSeparator();
        windowMenu.add(aboutItem);

        add(fileMenu);
        add(showMenu);
        add(addAccountsMenu);
        add(windowMenu);

        aboutBox = new JDialog();
        aboutBox.setLocationRelativeTo(this);

        aboutItem.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0)
                {
                    JOptionPane.showConfirmDialog(frame,
                        "CM0667 - Assignment 1 - 2014 - Semester 1"
                        + "\n\n"
                        + "Group Members:\n"
                        + "         Chris Barber - w14028557\n"
                        + "         Connor Carr - w14036662\n"
                        + "         Graham Coulby - w14002403\n"
                        + "\n\n"
                        + "Briefcase Icon: http://findicons.com/icon/13481/briefcase?id=13481 (Free for non-commercial use)\n"
                        + "Home Icon: http://findicons.com/icon/175944/home?id=360442 (Creative Commons License)\n"
                        + "Plus Icon: http://findicons.com/icon/51035/add?id=51101 (Creative Commons License)\n"
                        + "Person Icon: Created by Graham Coulby\n"
                        + "People Icon: Created by Graham Coulby\n"
                        + "Langsat Logo: Created By Graham Coulby\n"
                        + "Java logo has been used in accordance to the guidelines"
                        + "",
                        "About Us", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                }
            });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        showMenu.setMnemonic(KeyEvent.VK_S);
        addAccountsMenu.setMnemonic(KeyEvent.VK_A);
        windowMenu.setMnemonic(KeyEvent.VK_W);

        aboutItem.setMnemonic(KeyEvent.VK_A);
        home.setMnemonic(KeyEvent.VK_H);
        allAccounts.setMnemonic(KeyEvent.VK_A);
        trdAccounts.setMnemonic(KeyEvent.VK_T);
        perAccounts.setMnemonic(KeyEvent.VK_P);
        addAccount.setMnemonic(KeyEvent.VK_A);

        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                ActionEvent.CTRL_MASK));
        home.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
                ActionEvent.CTRL_MASK));
        allAccounts.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                ActionEvent.CTRL_MASK));
        trdAccounts.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
                ActionEvent.CTRL_MASK));
        perAccounts.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                ActionEvent.CTRL_MASK));
        addAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK));
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
                ActionEvent.CTRL_MASK));

        exitItem.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0)
                {
                    MainFrame.closeOperation();
                }
            });
    }

    /**
     * A setter to add an action listener to the 'Home' button from another class
     * @author Carr
     * @param listener the ActionListener that will listen to the button 
     */
    public void addHomeListener(ActionListener listener)
    {
        home.addActionListener(listener);
    }

    /**
     * A setter to add an action listener to the 'All Accounts' button from another class
     * @author Carr
     * @param listener the ActionListener that will listen to the button 
     */
    public void addAllAccountsListener(ActionListener listener)
    {
        allAccounts.addActionListener(listener);
    }

    /**
     * A setter to add an action listener to the 'Trade Accounts' button from another class
     * @author Carr
     * @param listener the ActionListener that will listen to the button 
     */
    public void addTrdAccountsListener(ActionListener listener)
    {
        trdAccounts.addActionListener(listener);
    }

    /**
     * A setter to add an action listener to the 'Personal Accounts' button from another class
     * @author Carr
     * @param listener the ActionListener that will listen to the button 
     */
    public void addPerAccountsListener(ActionListener listener)
    {
        perAccounts.addActionListener(listener);
    }

    /**
     * A setter to add an action listener to the 'Add New Account' button from another class
     * @author Carr
     * @param listener the ActionListener that will listen to the button 
     */
    public void addNewAccountListener(ActionListener listener)
    {
        addAccount.addActionListener(listener);
    }
}