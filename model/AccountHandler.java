/**
 * Model Package for Core Functionality
 */
package model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import gui.windows.MainFrame;

import javax.swing.table.DefaultTableModel;

/**
 * Handles all account related methods
 * 
 * @author Barber, Carr, Coulby
 */

public class AccountHandler
{
    private static ArrayList<TradeAccount> tradeAccounts = new ArrayList<TradeAccount>();
    private static ArrayList<PersonalAccount> personalAccounts = new ArrayList<PersonalAccount>();

    // Personal Accounts Table Data
    private static Vector<Object> personalAccountsDB = new Vector<Object>();
    private static Vector<String> perAccColumnNames = new Vector<String>(Arrays.asList
            (
                "Account Number",
                "First Name",
                "Surname",
                "Card Type",
                "Card Number"
            ));
    public static DefaultTableModel perAccTableModel = new DefaultTableModel(personalAccountsDB, perAccColumnNames);

    // Trade Accounts Table Data
    private static Vector<Object> tradeAccountsDB = new Vector<Object>();
    private static Vector<String> trdAccColumnNames = new Vector<String>(Arrays.asList
            (
                "Account Number",
                "First Name",
                "Surname",
                "VAT Number",
                "Phone Number"
            ));
    public static DefaultTableModel trdAccTableModel = new DefaultTableModel(tradeAccountsDB, trdAccColumnNames);

    private static PrintWriter toFile;

    /**
     * Adds a trade account both to the TableModel and to the ArrayList of TradeAccounts
     * @author Barber, Coulby
     * @param firstName First Name as a String
     * @param surname Surname as a String
     * @param vatNumber VAT Number as a String
     * @param telNumber Telephone Number as a String
     */
    public static void addTradeAccount(String firstName, String surname, String vatNumber, String telNumber)
    {
        TradeAccount ta = new TradeAccount(firstName, surname, vatNumber, telNumber);
        tradeAccounts.add(ta);

        Vector<String> v = new Vector<String>();
        v.add(ta.getAccountNo());
        v.add(ta.getFirstName());
        v.add(ta.getSurname());
        v.add(ta.getVatNumber());
        v.add(ta.getTelNumber());
        tradeAccountsDB.add(v);
    }

    /**
     * Adds a personal account both to the TableModel and to the ArrayList of PersonalAccounts
     * @author Barber, Coulby
     * @param firstName First Name as a String
     * @param surname Surname as a String
     * @param cardType Card Type as a String
     * @param cardNo Card Number as a String
     */
    public static void addPersonalAccount(String firstName, String surname, String cardType, String cardNo)
    {
        PersonalAccount pa = new PersonalAccount(firstName, surname, cardType, cardNo);
        personalAccounts.add(pa);

        Vector<String> v = new Vector<String>();
        v.add(pa.getAccountNo());
        v.add(pa.getFirstName());
        v.add(pa.getSurname());
        v.add(pa.getCredCardType());
        v.add(pa.getCredCardNo());
        personalAccountsDB.add(v);
    }

    /**
     * Adds a trade account both to the TableModel and to the ArrayList of TradeAccounts
     * when adding from a text file.
     * @author Barber, Coulby
     * @param accountNo
     * @param firstName
     * @param surname
     * @param vatNumber
     * @param telNumber
     */
    public static void addTradeAccountFromFile(String accountNo, String firstName, String surname, String vatNumber, String telNumber)
    {
        TradeAccount ta = new TradeAccount(accountNo, firstName, surname, vatNumber, telNumber);
        tradeAccounts.add(ta);

        Vector<String> v = new Vector<String>();
        v.add(ta.getAccountNo());
        v.add(ta.getFirstName());
        v.add(ta.getSurname());
        v.add(ta.getVatNumber());
        v.add(ta.getTelNumber());
        tradeAccountsDB.add(v);
    }

    /**
     * Adds a personal account both to the TableModel and to the ArrayList of PersonalAccounts
     * when adding from a text file.
     * @author Barber, Coulby
     * @param credCardNo
     * @param credCardType
     * @param firstName
     * @param surname
     */
    public static void addPersonalAccountFromFile(String accountNo, String firstName, String surname, String cardType, String cardNo)
    {
        PersonalAccount pa = new PersonalAccount(accountNo, firstName, surname, cardType, cardNo);
        personalAccounts.add(pa);

        Vector<String> v = new Vector<String>();
        v.add(pa.getAccountNo());
        v.add(pa.getFirstName());
        v.add(pa.getSurname());
        v.add(pa.getCredCardType());
        v.add(pa.getCredCardNo());
        personalAccountsDB.add(v);
    }

    /**
     * Lists all accounts as a string
     * 
     * @author Barber, Coulby
     * @return String of all accounts
     */
    public static String listAllAccounts()
    {
        String str = "";
        for (TradeAccount tradeAccount : tradeAccounts)
        {
            str += tradeAccount.toString();
        }

        str += "Next Trade Account Number: " + Account.nextTrdAccountNo + "\r\n";
        for (PersonalAccount personalAccount : personalAccounts)
        {
            str += personalAccount.toString();
        }
        str += "Next Personal Account Number: " + Account.nextPerAccountNo;
        return str;
    }

    /**
     * Lists all trade accounts as string
     * 
     * @author Carr
     * @return String of all trade accounts
     */
    public static String listTradeAccounts()
    {
        String str = "";
        for (TradeAccount tradeAccount : tradeAccounts)
        {
            str += tradeAccount.toString();
        }
        return str;
    }

    /**
     * Lists all personal accounts as string
     * 
     * @author Carr
     * @return String of all personal accounts
     */
    public static String listPersonalAccounts()
    {
        String str = "";
        for (PersonalAccount personalAccount : personalAccounts)
        {
            str += personalAccount.toString();
        }
        return str;
    }

    /**
     * Returns the total number of accounts
     * 
     * @author Carr
     * @return total number of accounts as int
     */
    public static int getNoOfAccounts()
    {
        return (tradeAccounts.size() + personalAccounts.size());
    }

    /**
     * Returns the total number of trade accounts
     * 
     * @author Carr
     * @return total number of trade accounts as int
     */
    public static int getNoOfTradeAccounts()
    {
        return (tradeAccounts.size());
    }

    /**
     * Returns the total number of personal accounts
     * 
     * @author Carr
     * @return total number of personal accounts as int
     */
    public static int getNoOfPersonalAccounts()
    {
        return (personalAccounts.size());
    }

    /**
     * Searches through list of  Trade accounts and returns the account with the
     * matching trade account number to the parameter passed in.
     * 
     * @author Barber, Carr, Coulby
     * @param accountNo
     * @return the matching TradeAccount
     */

    public static TradeAccount searchTradeAccounts(String accountNo)
    {
        TradeAccount a = null;
        String accNo = "";
        if (accountNo.length() == 8)
        {
            if (accountNo.substring(0, 3).equalsIgnoreCase("TRD"))
            {
                accNo = accountNo;
            }
            else
            {
                return null;
            }
        }
        else
        {
            accNo = "TRD" + accountNo;
        }

        for (int i = 0; i < (tradeAccounts.size()); i++)
        {
            if (String.valueOf(tradeAccounts.get(i).getAccountNo()).equals(accNo))
            {
                a = tradeAccounts.get(i);
            }
        }
        return a;
    }

    /**
     * Searches through list of personal accounts and returns the account with the
     * matching personal account number to the parameter passed in.
     * 
     * @author Barber, Carr, Coulby
     * @param accountNo
     * @return the matching PersonalAccount
     */
    public static PersonalAccount searchPersonalAccounts(String accountNo)
    {
        PersonalAccount a = null;
        String accNo = "";
        if (accountNo.length() == 8)
        {
            if (accountNo.substring(0, 3).equalsIgnoreCase("PER"))
            {
                accNo = accountNo;
            }
            else
            {
                return null;
            }
        }
        else
        {
            accNo = "PER" + accountNo;
        }
        for (int i = 0; i < (personalAccounts.size()); i++)
        {
            if (String.valueOf(personalAccounts.get(i).getAccountNo()).equals(accNo))
            {
                a = personalAccounts.get(i);
            }
        }
        return a;
    }

    /**
     * Searches through all the accounts and adds any accounts found 
     * to an ArrayList of type Account
     * @author Barber, Carr, Coulby
     * @param accountNo Account Number a String
     * @return accounts ArrayList of Type Account
     */
    public static ArrayList<Account> searchAllAccounts(String accountNo)
    {
        ArrayList<Account> accounts = new ArrayList<Account>();
        TradeAccount ta = searchTradeAccounts(accountNo);
        PersonalAccount pa = searchPersonalAccounts(accountNo);
        accounts.add(ta);
        accounts.add(pa);
        return accounts;
    }

    /**
     * Searchs the Account ArrayLists for accounts with matching account numbers
     * and surnames then deletes the account from both the ArrayList and the JTable
     * Vector
     * @author Barber, Coulby
     * @param surname
     * @param accountNo
     */
    public static void deleteAccount(String surname, String accountNo)
    {
        if (accountNo.startsWith("TRD"))
        {
            for (int i = 0; i < (tradeAccounts.size()); i++)
            {
                if (String.valueOf(tradeAccounts.get(i).getAccountNo()).equals(accountNo))
                {
                    if (tradeAccounts.get(i).getSurname().equals(surname))
                    {
                        tradeAccounts.remove(i);
                        tradeAccountsDB.remove(i);
                        updateTable();
                        break; // breaks out of loop if true
                    }
                }
            }
        }
        else if (accountNo.startsWith("PER"))
        {
            for (int i = 0; i < (personalAccounts.size()); i++)
            {
                if (String.valueOf(personalAccounts.get(i).getAccountNo()).equals(accountNo))
                {
                    if (personalAccounts.get(i).getSurname().equals(surname))
                    {
                        personalAccounts.remove(i);
                        personalAccountsDB.remove(i);
                        updateTable();
                        break; // breaks out of loop if true
                    }
                }
            }
        }
    }

    /**
     * Getter for tradeAccountsDB
     * @author Carr
     * @return tradeAccountsDB the Vector of Trade Accounts
     * used by the JTable
     */
    public static Vector<Object> getTradeAccountsDB()
    {
        return tradeAccountsDB;
    }

    /**
     * Getter for personalAccountsDB
     * @author Carr
     * @return personalAccountsDB the Vector of Personal Accounts
     * used by the JTable
     */
    public static Vector<Object> getPersonalAccountsDB()
    {
        return personalAccountsDB;
    }

    /**
     * Fires table changes. This method is called
     * every time an account is added or deleted
     *@author Barber, Coulby 
     */
    public static void updateTable()
    {
        perAccTableModel.fireTableDataChanged();
        trdAccTableModel.fireTableDataChanged();
        MainFrame.getAllAccountWindow().updateStatusBar();
        MainFrame.getTrdAccountWindow().updateStatusBar(AccountHandler.getNoOfTradeAccounts());
        MainFrame.getPerAccountWindow().updateStatusBar(AccountHandler.getNoOfPersonalAccounts());
        // Known Bug: unable to remove table editing. Does not affect the
        // database.
    }

}