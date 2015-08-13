/**
 * Package for GUI Windows
 */
package gui.windows;

import gui.Constants;
import gui.components.Row;
import gui.components.SearchBox;
import gui.components.VerticalStrut;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Account;
import model.AccountHandler;
import model.PersonalAccount;
import model.TradeAccount;

/**
 * Class for HomeScreen shows a logo and a search form for finding accounts
 * @author Barber, Coulby
 *
 */
public class HomeScreen extends JPanel
{

    private JLabel findAccount = new JLabel("Find Account...");
    private JTextField searchBox = new JTextField("Enter a five digit Account Number: e.g. 45634");
    private JCheckBox trdCheck = new JCheckBox("Trade Account");
    private JCheckBox perCheck = new JCheckBox("Personal Account");
    private JButton searchBtn = new JButton("Search");
    private JPanel flowPanel = new JPanel();
    private JPanel gridPanel = new JPanel();
    private final JFrame frame;

    /**
     * Initialises the HomeScreen frame, sets colors builds the gridLayout panel
     * to be added to the flowLayout panel and sets the listener to the search
     * button.
     */
    public void init()
    {
        searchBox.setColumns(40);
        trdCheck.setBackground(Constants.BACKGROUND_COLOR);
        perCheck.setBackground(Constants.BACKGROUND_COLOR);
        setLayout(new BorderLayout());
        buildGrid();
        buildFlow();
        searchBox.setForeground(new Color(100, 100, 100));

        searchBox.addFocusListener(new FocusListener()
            {
                @Override
                public void focusLost(FocusEvent e)
                {
                }

                @Override
                public void focusGained(FocusEvent e)
                {
                    searchBox.setForeground(Constants.FOREGROUND_COLOR);
                    searchBox.setText("");
                }
            });
        searchBtn.addActionListener(new SearchListener());
    }

    /**
     * Constructor for HomeScreen builds windows and calls the init() method to
     * initialise the panel. Adds the company Logo to the home screen and adds
     * the nested panels below it.
     * 
     * @param frame The JFrame to display messages onto
     */
    public HomeScreen(final JFrame frame)
    {
        this.frame = frame;
        init();

        add(Constants.label(Constants.LOGO), BorderLayout.NORTH);
        add(flowPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * builds a flow panel to hold the grid
     */
    public void buildFlow()
    {
        flowPanel.setBackground(Constants.BACKGROUND_COLOR);
        flowPanel.setLayout(new FlowLayout());
        flowPanel.add(gridPanel);
    }

    /**
     * builds the search form onto a grid
     */
    public void buildGrid()
    {
        gridPanel.setBackground(Constants.BACKGROUND_COLOR);
        GridLayout grid = new GridLayout(4, 0);
        gridPanel.setLayout(grid);
        setBackground(Constants.BACKGROUND_COLOR);
        setForeground(Constants.FOREGROUND_COLOR);
        gridPanel.add(new Row(findAccount, FlowLayout.LEFT, true));
        gridPanel.add(new Row(searchBox, FlowLayout.CENTER, true));
        gridPanel.add(new Row(perCheck, trdCheck, searchBtn, FlowLayout.RIGHT, true));
        new VerticalStrut(6, grid, gridPanel);
    }

    /**
     * Action listener for the search button, when the search button is
     * pressed the actionPerformed method checks to see if which check boxes
     * are pressed and searches the appropriate account database. if neither
     * or both are selected it checks both.
     * 
     * Accepted search terms are TRD + 5 digits, PER + 5 digits or simply just
     * the 5 digit number
     * 
     * The search results are then returned in JPanels and are added to a
     * dialog box.
     * 
     * If both or neither check boxes are selected, the search term is only a
     * 5 digits (minus the prefix) and two accounts are found to contain that
     * number, both accounts will be displayed in a dialog box.
     * 
     * @author Barber, Coulby
     */
    class SearchListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            TradeAccount trdAccount = null;
            PersonalAccount perAccount = null;
            ArrayList<Account> accounts = new ArrayList<Account>();
            String accountNo = searchBox.getText();
            searchBox.setText("");
            boolean trade = trdCheck.isSelected();
            boolean personal = perCheck.isSelected();
            if (personal == trade)
            {
                accounts = AccountHandler.searchAllAccounts(accountNo);
                if (accounts.get(0) == null || accounts.get(1) == null) // 1 or 0
                // accounts
                // found
                {
                    if (accounts.get(0) == null ^ accounts.get(1) == null) // only 1
                    // account
                    // found
                    {
                        if (accounts.get(0) != null)
                        {
                            JOptionPane.showMessageDialog(frame,
                                SearchBox.buildSearchReturnBox((TradeAccount) accounts.get(0)),
                                "Trade Account Found", JOptionPane.PLAIN_MESSAGE);
                            personal = false;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(frame,
                                SearchBox.buildSearchReturnBox((PersonalAccount) accounts.get(1)),
                                "Personal Account Found", JOptionPane.PLAIN_MESSAGE);
                            trade = false;
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame,
                            "No account found with that account number",
                            "No Account Found", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(frame,
                        SearchBox.buildSearchReturnBox(accounts),
                        "Multiple Accounts Found", JOptionPane.PLAIN_MESSAGE);
                }
            }
            else if (trade == true)
            {
                trdAccount = AccountHandler.searchTradeAccounts(accountNo);
                if (trdAccount != null)
                {
                    JOptionPane.showMessageDialog(frame,
                        SearchBox.buildSearchReturnBox(trdAccount),
                        "Trade Account Found", JOptionPane.PLAIN_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame,
                        "No 'Trade' account found with that account number",
                        "No Account Found", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (personal == true)
            {
                perAccount = AccountHandler.searchPersonalAccounts(accountNo);
                if (perAccount != null)
                {
                    JOptionPane.showMessageDialog(frame,
                        SearchBox.buildSearchReturnBox(perAccount),
                        "Personal Account Found", JOptionPane.PLAIN_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame,
                        "No 'Personal' account found with that account number",
                        "No Account Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}