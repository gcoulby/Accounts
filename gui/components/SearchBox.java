/**
 * Package for GUI components
 */
package gui.components;

import gui.Constants;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import model.Account;
import model.PersonalAccount;
import model.TradeAccount;

/**
 * The Class for building the panels that will be added to the
 * return dialog when searching for accounts.
 * @author Barber, Coulby
 */
public class SearchBox
{
    private static Border border = BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10));
    private static JLabel title;
    private static Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);

    /**
     * Builds a JPanel and adds components in to display a TradeAccount
     * inside of a dialog box if a TradeAccount is found by the search.
     * @param account A found TradeAccount to be added to a panel
     * @return jp The JPanel the components are added to.
     */
    public static JPanel buildSearchReturnBox(TradeAccount account)
    {
        JPanel jp = new JPanel(new BorderLayout());
        jp.setBorder(border);
        jp.add(Constants.label(Constants.TRD_ICON_LRG), BorderLayout.NORTH);
        JPanel jpGrid = new JPanel();
        jpGrid.setLayout(new GridLayout(6, 1));
        addFieldsToSearchReturnBox(jpGrid, account);
        jp.add(jpGrid, BorderLayout.CENTER);
        return jp;
    }

    /**
     * Builds a JPanel and adds components in to display a PersonalAccount
     * inside of a dialog box if a PersonalAccount is found by the search.
     * @param account A found PersonalAccount to be added to a panel
     * @return jp The JPanel the components are added to.
     */
    public static JPanel buildSearchReturnBox(PersonalAccount account)
    {
        JPanel jp = new JPanel(new BorderLayout());
        jp.setBorder(border);
        jp.add(Constants.label(Constants.PER_ICON_LRG), BorderLayout.NORTH);
        JPanel jpGrid = new JPanel();
        jpGrid.setLayout(new GridLayout(6, 1));
        addFieldsToSearchReturnBox(jpGrid, account);
        jp.add(jpGrid, BorderLayout.CENTER);
        return jp;
    }

    /**
     * Builds a JPanel and adds components in to display both type of accounts
     * inside of a dialog box if both a Trade and Personal Account are found by the search.
     * @param accounts An ArrayList of type Account that holds multiple accounts
     * @return jp The JPanel the components are added to.
     */
    public static JPanel buildSearchReturnBox(ArrayList<Account> accounts)
    {
        TradeAccount trdAccount = (TradeAccount) accounts.get(0);
        PersonalAccount perAccount = (PersonalAccount) accounts.get(1);
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(buildSearchReturnBox(trdAccount), BorderLayout.WEST);
        jp.add(new JLabel("         "), BorderLayout.CENTER);
        jp.add(buildSearchReturnBox(perAccount), BorderLayout.EAST);
        return jp;
    }

    /**
     * Gets fields from the TradeAccount object found and adds them to a
     * GridLayout, which is then added to the parentPanel
     * @param parentPanel The JPanel in which the components should be added
     * @param account The TradeAccount to be added to the GridLayout
     */
    public static void addFieldsToSearchReturnBox(JPanel parentPanel, TradeAccount account)
    {
        title = new JLabel("Trade Account");
        title.setFont(titleFont);
        parentPanel.add(new Row(title, FlowLayout.CENTER, false));
        parentPanel.add(new Row(new JLabel("Account Number: " + account.getAccountNo()), FlowLayout.LEFT, false));
        parentPanel.add(new Row(new JLabel("FirstName: " + account.getFirstName()), FlowLayout.LEFT, false));
        parentPanel.add(new Row(new JLabel("Surname: " + account.getSurname()), FlowLayout.LEFT, false));
        parentPanel.add(new Row(new JLabel("VAT Number: " + account.getVatNumber()), FlowLayout.LEFT, false));
        parentPanel.add(new Row(new JLabel("Phone Number: " + account.getTelNumber()), FlowLayout.LEFT, false));
    }

    /**
     * Gets fields from the PersonalAccount object found and adds them to a
     * GridLayout, which is then added to the parentPanel
     * @param parentPanel The JPanel in which the components should be added
     * @param account The PersonalAccount to be added to the GridLayout
     */
    public static void addFieldsToSearchReturnBox(JPanel parentPanel, PersonalAccount account)
    {
        title = new JLabel("Personal Account");
        title.setFont(titleFont);
        parentPanel.add(new Row(title, FlowLayout.CENTER, false));
        parentPanel.add(new Row(new JLabel("Account Number: " + account.getAccountNo()), FlowLayout.LEFT, false));
        parentPanel.add(new Row(new JLabel("FirstName: " + account.getFirstName()), FlowLayout.LEFT, false));
        parentPanel.add(new Row(new JLabel("Surname: " + account.getSurname()), FlowLayout.LEFT, false));
        parentPanel.add(new Row(new JLabel("Card Type: " + account.getCredCardType()), FlowLayout.LEFT, false));
        parentPanel.add(new Row(new JLabel("Card Number: " + account.getCredCardNo()), FlowLayout.LEFT, false));
    }
}
