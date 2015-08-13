/**
 * Package for GUI Windows
 */
package gui.windows;

import gui.Constants;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.AccountHandler;

/**
 * AllAccountWindow Class builds a window with
 * two JTables in showing both the Trade and Personal
 * Accounts, along with a status bar to show data for
 * all types of accounts.
 * @author Barber, Coulby
 */
public class AllAccountWindow extends JPanel
{
    private PersonalAccountWindow personal = new PersonalAccountWindow(false);
    private TradeAccountWindow trade = new TradeAccountWindow(false);
    private JLabel tradeAccounts = new JLabel();
    private JLabel personalAccounts = new JLabel();
    private JLabel allAccounts = new JLabel();

    /**
     * Constructor for AllAccountWindow, builds a 
     * simple JPanel which nests the more complex panels
     */
    public AllAccountWindow()
    {
        setLayout(new BorderLayout());
        add(buildGrid(), BorderLayout.CENTER);
        add(statusBar(), BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * Displays both the Trade Account and Personal Account
     * tables in a GridLayout; thus ensuring equal spacing
     * there is a small gap between the two tables
     * @return jp The GridLayout Panel which displays the two tables
     */
    public JPanel buildGrid()
    {
        JPanel jp = new JPanel();
        GridLayout grid = new GridLayout(2, 1);
        jp.setLayout(grid);
        grid.setVgap(5);
        jp.setBackground(Constants.BACKGROUND_COLOR);
        jp.add(trade);
        jp.add(personal);
        return jp;
    }

    /**
     * Builds the status bar for both containing the total number
     * of trade accounts, personal accounts and total number
     * of accounts.
     * @return jp The Status bar panel to be added to the Frame
     */
    public JPanel statusBar()
    {
        JPanel jp = new JPanel();
        jp.setBorder(BorderFactory.createEtchedBorder());
        jp.setLayout(new BorderLayout());
        JLabel jl = new JLabel("Total Number of Trade Accounts: ");
        JLabel jl2 = new JLabel("Total Number of Accounts: ");
        JLabel jl3 = new JLabel("Total Number of Personal Accounts: ");
        tradeAccounts.setText(String.valueOf(AccountHandler.getNoOfTradeAccounts()));
        personalAccounts.setText(String.valueOf(AccountHandler.getNoOfPersonalAccounts()));
        allAccounts.setText(String.valueOf(AccountHandler.getNoOfAccounts()));

        JPanel fl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fl.add(jl);
        fl.add(tradeAccounts);

        JPanel fl2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fl2.add(jl2);
        fl2.add(allAccounts);

        JPanel fl3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        fl3.add(jl3);
        fl3.add(personalAccounts);

        jp.add(fl, BorderLayout.WEST);
        jp.add(fl2, BorderLayout.CENTER);
        jp.add(fl3, BorderLayout.EAST);
        return jp;
    }

    /**
     * Updates the status bar with the new amount of accounts
     * this is called each time and account is added
     */
    public void updateStatusBar()
    {
        tradeAccounts.setText(String.valueOf(AccountHandler.getNoOfTradeAccounts()));
        personalAccounts.setText(String.valueOf(AccountHandler.getNoOfPersonalAccounts()));
        allAccounts.setText(String.valueOf(AccountHandler.getNoOfAccounts()));
    }

}