/**
 * Package for GUI Windows
 */
package gui.windows;

import gui.Constants;
import gui.components.MenuBar;
import gui.components.Toolbar;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.EventListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.FileHandler;

/**
 * Constructor for MainFrame,
 * This is the program's primary GUI Class.
 * This class is also the only class, other than
 * the splacsh screen that extends JFrame.
 * This means that the MainFrame JFrame
 * contains the whole application and when closed
 * the program terminates
 * @author Barber, Carr, Coulby
 *
 */
public class MainFrame extends JFrame implements EventListener
{
    private final JFrame mainFrame = this;
    private MenuBar menuBar = new MenuBar(this);
    private Toolbar toolbar = new Toolbar();
    private HomeScreen homeScreen = new HomeScreen(this);
    private static TradeAccountWindow trdAccount = new TradeAccountWindow(true);
    private static PersonalAccountWindow perAccount = new PersonalAccountWindow(true);
    private static AllAccountWindow allAccount = new AllAccountWindow();
    private AddAccountWindow addAccount = new AddAccountWindow(this);
    private CardLayout cardLayout = new CardLayout();
    private String currentCardLayout = "";
    private JPanel windows = new JPanel();
    private WindowListener exitListener;

    /**
     * Initialises with a titlebar name and size
     * of 1024x768 although it can be resized.
     * There is also a custom closeOperation called by the
     * WindowAdaptor, this allows us to save to the text file
     * before the application is terminated.
     */
    public MainFrame()
    {
        super("Accounts Manager Plus");
        currentCardLayout = "Home";
        setSize(new Dimension(1024, 768));
        setResizable(true);
        exitListener = new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                closeOperation();
            }
        };
        this.addWindowListener(exitListener);
        setLocation(((Constants.SCREEN_SIZE.width - getWidth()) / 2), ((Constants.SCREEN_SIZE.height - getHeight()) / 2));
        setListeners();
        buildLayout();
        setVisible(true); // display swing frame.
    }

    /**
     * Builds the layout  using borderLayout and adds
     * the window cards the toolbar and the addAccount Panel
     * @author Barber, Coulby
     */
    public void buildLayout()
    {
        setJMenuBar(menuBar);
        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        buildCard();
        add(windows, BorderLayout.CENTER);

        add(addAccount, BorderLayout.EAST);
    }

    /**
     * Builds the 'Cards' (windows) to be displayed
     * Cards can be switched with showCard method by passing
     * in a string that corresponds to the card's name
     * @author Barber, Coulby
     */
    public void buildCard()
    {
        windows.setLayout(cardLayout);
        windows.add("Home", homeScreen);
        windows.add("Trade Accounts", trdAccount);
        windows.add("Personal Accounts", perAccount);
        windows.add("All Accounts", allAccount);
    }

    /**
     * Sets all of the button's listeners to the BtnListener inner class
     * @author Carr
     */
    public void setListeners()
    {
        toolbar.addHomeListener(new BtnListener());
        toolbar.addAllAccountListener(new BtnListener());
        toolbar.addTrdAccountListener(new BtnListener());
        toolbar.addPerAccountListener(new BtnListener());
        toolbar.addPlusListener(new BtnListener());
        menuBar.addHomeListener(new BtnListener());
        menuBar.addAllAccountsListener(new BtnListener());
        menuBar.addTrdAccountsListener(new BtnListener());
        menuBar.addPerAccountsListener(new BtnListener());
        menuBar.addNewAccountListener(new BtnListener());
        addAccount.addCancelListener(new BtnListener());
        addAccount.addTradeListener(new BtnListener());
        addAccount.addPersonalListener(new BtnListener());
    }

    /**
     * The listener for controlling which 'Card' (window) to show
     * @author Barber, Coulby
     */
    class BtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if ((e.getActionCommand() == "Trade Account"
                || e.getActionCommand() == "Personal Account")
            && currentCardLayout != "All Accounts")
            {
                cardLayout.show(windows, e.getActionCommand() + "s");
            }
            if (e.getActionCommand() == "Home")
            {
                addAccount.setVisible(false); // Ternary Operator Switch
            }

            if (e.getActionCommand() == "Plus")
            {
                addAccount.setVisible((addAccount.isVisible() == false) ? true : false); // Ternary Operator
                // Switch
                if (currentCardLayout != "All Accounts")
                {
                    if (AddAccountWindow.getCurrentForm() == "Trade Account" || currentCardLayout == "Home")
                    {
                        cardLayout.show(windows, "Trade Accounts");
                    }
                }
            }

            else
            {
                cardLayout.show(windows, e.getActionCommand());
                if ((e.getActionCommand() != "Trade Account"
                    && e.getActionCommand() != "Personal Account"))
                {
                    currentCardLayout = e.getActionCommand();
                }
            }
        }
    }

    /**
     * Returns the TradeAccountWindow
     * @author Carr
     * @return allAccount TradeAccountWindow
     */
    public static TradeAccountWindow getTrdAccountWindow()
    {
        return trdAccount;
    }

    /**
     * Returns the PersonalAccountWindow
     * @author Carr
     * @return allAccount PersonalAccountWindow
     */
    public static PersonalAccountWindow getPerAccountWindow()
    {
        return perAccount;
    }

    /**
     * Returns the AllAccountWindow
     * @author Carr
     * @return allAccount AllAccountWindow
     */
    public static AllAccountWindow getAllAccountWindow()
    {
        return allAccount;
    }

    /**
     * Custom method for overriding the DEFAULT_CLOSE_OPERATION
     * writes the accounts to the text file
     * closes the filewriter
     * the exits using System.exit(0)
     * @author Barber, Coulby
     */
    public static void closeOperation()
    {
        FileHandler.writeToFile();
        FileHandler.closeLink();
        System.exit(0);
    }

}