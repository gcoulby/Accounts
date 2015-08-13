/**
 * Package for GUI Window
 */
package gui.windows;

import gui.Constants;
import gui.components.Row;
import gui.components.Toolbar;
import gui.components.VerticalStrut;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.AccountHandler;

/**
 * Class for building the slide in panel which is
 * used for adding accounts to the system and the
 * database tables.
 * @author Barber, Coulby
 *
 */
public class AddAccountWindow extends JPanel
{
    // Buttons
    private JButton trdAccountBtn = new JButton("Trade Account");
    private JButton trdAccountBtn2 = new JButton("Trade Account");
    private JButton perAccountBtn = new JButton("Personal Account");
    private JButton perAccountBtn2 = new JButton("Personal Account");
    private JButton submitBtnPer = new JButton("Submit");
    private JButton submitBtnTrd = new JButton("Submit");
    private JButton cancelBtnPer = new JButton("Cancel");
    private JButton cancelBtnTrd = new JButton("Cancel");

    private static String currentForm = "";

    // Form Fields
    private JLabel trdFirstNameLabel = new JLabel("First Name: " + addSpace(16));
    private JTextField trdFirstNameField = new JTextField(20);
    private JLabel perFirstNameLabel = new JLabel("First Name: " + addSpace(16));
    private JTextField perFirstNameField = new JTextField(20);
    private JLabel trdSurnameLabel = new JLabel("Surname: " + addSpace(19));
    private JTextField trdSurnameField = new JTextField(20);
    private JLabel perSurnameLabel = new JLabel("Surname: " + addSpace(19));
    private JTextField perSurnameField = new JTextField(20);
    private JLabel vatNoLabel = new JLabel("VAT Number: " + addSpace(13));
    private JTextField vatNoField = new JTextField(20);
    private JLabel phoneLabel = new JLabel("Phone Number: " + addSpace(9));
    private JTextField phoneField = new JTextField(20);
    private JLabel credCardTypeLabel = new JLabel("Credit Card Type: " + addSpace(6));
    private JComboBox<String> credCardTypeField = new JComboBox<String>(new String[]
            { "AMEX", "MCard", "VISA" });
    private JLabel credCardNoLabel = new JLabel("Credit Card Number: ");
    private JTextField creditCardNoField = new JTextField(20);
    private CardLayout cardLayout = new CardLayout();
    private JPanel forms = new JPanel();
    private JLabel trdTitleLabel = new JLabel("Trade Account");
    private JLabel perTitleLabel = new JLabel("Personal Account");
    private final JFrame frame;

    /**
     * Constructor for AddAccountWindow.
     * Creates a JPanel calls the init() method and 
     * adds the components to the panel
     * @param frame The parent JFrame for this window to be added to.
     */
    public AddAccountWindow(JFrame frame)
    {
        this.frame = frame;
        init();
        add(new JPanel(), BorderLayout.NORTH); // Add a little space to the top
        add(forms, BorderLayout.CENTER);
    }

    /**
     * Initialises the Constructor, builds the cards and sets action commands
     * @author Barber, Coulby
     */
    private void init()
    {
        setVisible(false);
        setBorder(BorderFactory.createLoweredBevelBorder());
        buildCards();
        setLayout(new BorderLayout());
        setActionListeners();
        cancelBtnPer.setActionCommand("Plus");
        cancelBtnTrd.setActionCommand("Plus");
        submitBtnPer.setActionCommand("SubmitPer");
        submitBtnTrd.setActionCommand("SubmitTrd");
        trdTitleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        perTitleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
    }

    /**
     * Sets the action listeners for all the buttons to use the BtnListener Class
     * Allows switching between the card layouts
     * @author Carr
     */
    private void setActionListeners()
    {
        trdAccountBtn.addActionListener(new FormListener());
        trdAccountBtn2.addActionListener(new FormListener());
        perAccountBtn.addActionListener(new FormListener());
        perAccountBtn2.addActionListener(new FormListener());
        submitBtnPer.addActionListener(new FormListener());
        submitBtnTrd.addActionListener(new FormListener());
        Toolbar.getTrdButton().addActionListener(new BtnListener());
        Toolbar.getPerButton().addActionListener(new BtnListener());
    }

    /**
     * Set the CardLayout Panel and add the forms to the cards for switching
     * @author Barber, Coulby
     */
    public void buildCards()
    {
        forms.setLayout(cardLayout);
        forms.add("Trade Account", new Form(true));
        forms.add("Personal Account", new Form(false));
    }

    /**
     * The Class for building the Form Slide in Panel
     * @author Barber, Carr, Coulby
     */
    class Form extends JPanel
    {
        private GridLayout grid = new GridLayout(5, 1);
        private JPanel gridPanel = new JPanel(grid);

        /**
         * Method for building the forms to be added to the add account panel 
         * @author Barber, Coulby
         * @param isTradeAccount A boolean to determine which form to show
         */
        public Form(boolean isTradeAccount)
        {
            setLayout(new BorderLayout());
            new VerticalStrut(4, grid, this);
            if (isTradeAccount)
            {
                add(Constants.label(Constants.TRD_ICON_LRG), BorderLayout.NORTH);
                gridPanel.add(new Row(trdTitleLabel, FlowLayout.CENTER, false));
                gridPanel.add(new Row(trdAccountBtn, perAccountBtn, FlowLayout.CENTER, false));
                gridPanel.add(new Row(trdFirstNameLabel, trdFirstNameField, FlowLayout.LEFT, false));
                gridPanel.add(new Row(trdSurnameLabel, trdSurnameField, FlowLayout.LEFT, false));
                gridPanel.add(new Row(vatNoLabel, vatNoField, FlowLayout.LEFT, false));
                gridPanel.add(new Row(phoneLabel, phoneField, FlowLayout.LEFT, false));
                gridPanel.add(new Row(cancelBtnTrd, submitBtnTrd, FlowLayout.CENTER, false));
            }
            else
            {
                add(Constants.label(Constants.PER_ICON_LRG), BorderLayout.NORTH);
                gridPanel.add(new Row(perTitleLabel, FlowLayout.CENTER, false));
                gridPanel.add(new Row(trdAccountBtn2, perAccountBtn2, FlowLayout.CENTER, false));
                gridPanel.add(new Row(perFirstNameLabel, perFirstNameField, FlowLayout.LEFT, false));
                gridPanel.add(new Row(perSurnameLabel, perSurnameField, FlowLayout.LEFT, false));
                gridPanel.add(new Row(credCardTypeLabel, credCardTypeField, FlowLayout.LEFT, false));
                gridPanel.add(new Row(credCardNoLabel, creditCardNoField, FlowLayout.LEFT, false));
                gridPanel.add(new Row(cancelBtnPer, submitBtnPer, FlowLayout.CENTER, false));
            }
            new VerticalStrut(4, grid, this);

            add(gridPanel, BorderLayout.CENTER);
        }
    }

    /**
     * A getter to return the current form
     * @author Carr
     */
    public static String getCurrentForm()
    {
        return currentForm;
    }

    /**
     * A setter to add an action listener to the 'Cancel' button from another class
     * @author Carr
     * @param listener the ActionListener that will listen to the button 
     */
    public void addCancelListener(ActionListener listener)
    {
        cancelBtnPer.addActionListener(listener);
        cancelBtnTrd.addActionListener(listener);
    }

    /**
     * A setter to add an action listener to the 'Trade Account' button from another class
     * @author Carr
     * @param listener the ActionListener that will listen to the button 
     */
    public void addTradeListener(ActionListener listener)
    {
        trdAccountBtn.addActionListener(listener);
        trdAccountBtn2.addActionListener(listener);
    }

    /**
     * A setter to add an action listener to the 'Personal Account' button from another class
     * @author Carr
     * @param listener the ActionListener that will listen to the button 
     */
    public void addPersonalListener(ActionListener listener)
    {
        perAccountBtn.addActionListener(listener);
        perAccountBtn2.addActionListener(listener);
    }

    /**
     * Creates a String of white space to pad out the form
     * @author Barber, Coulby
     * @param numberOfSpaces The Number of white spaces to add
     * @return str String of empty space
     */
    public String addSpace(int numberOfSpaces)
    {
        String str = "";
        for (int i = 0; i < numberOfSpaces; i++)
        {
            str += " ";
        }
        return str;
    }

    /**
     * Checks to see if all the Text fields have a value.
     * @author Barber, Coulby
     * @param field1 First Text Field
     * @param field2 Second Text Field
     * @param field3 Third Text Field
     * @param field4 Forth Text Field
     * @return boolean
     */
    private boolean errorHandling(String field1, String field2, String field3, String field4)
    {
        return (!field1.equals("") && !field2.equals("") && !field3.equals("") && !field4.equals("")) ? true : false;
    }

    /**
     * Does error handling to ensure the number entered into the VAT field
     * is either 8 characters in length starting with "GB" or 6 characters
     * in length and does NOT start with "GB"
     * @author Barber, Coulby
     * @return String A String of 8 characters in length; starting with GB
     */
    private String vatStringErrorHandling()
    {
        String vatString = null;
        if (vatNoField.getText().substring(0, 2).equalsIgnoreCase("GB"))
        {

            vatString = vatNoField.getText().substring(2);
        }
        else
        {
            vatString = vatNoField.getText();
            if (vatString.length() != 6)
            {
                JOptionPane.showMessageDialog(frame,
                    "VAT Number must start with GB and be 8 characters long e.g. GB123456",
                    "Invalid Value", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return "GB" + vatString;
    }

    /**
     * Returns a boolean based on whether the value entered into the
     * phone number field is 11 characters long.
     * @author Barber, Coulby
     * @return boolean
     */
    private boolean phoneStringErrorHandling()
    {
        if (phoneField.getText().length() != 11)
        {
            JOptionPane.showMessageDialog(frame,
                "Phone number must be 11 digits long!",
                "Invalid Value", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Returns a boolean based on whether the value entered into the
     * credit card field is 16 characters long.
     * @author Barber, Coulby
     * @return boolean
     */
    private boolean credCardErrorHandling()
    {
        if (creditCardNoField.getText().length() != 16)
        {
            JOptionPane.showMessageDialog(frame,
                "Phone number must be 16 digits long!",
                "Invalid Value", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Inner Class: BtnListener is an ActionListener for buttons in other windows
     * such as Toolbar, or MenuBar. Used to switch between CardLayouts.
     * @author Barber, Coulby
     */
    class BtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            cardLayout.show(forms, e.getActionCommand().substring(0, e.getActionCommand().length() - 1));
            currentForm = e.getActionCommand().substring(0, e.getActionCommand().length() - 1);
        }
    }

    /**
     * This Action Listener handles a lot of the functionality of this class
     * When a form is submitted the form is ran through a series of error handling
     * methods and if all the methods come back true the Table is updated and the
     * Account is added to the database.
     * This Listener also handles which form to show, depending on what form navigation
     * button is pressed.
     * @author Barber, Coulby
     */
    class FormListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().substring(0, 3).equals("Sub"))
            {
                if (e.getActionCommand().substring(6, 9).equals("Trd"))
                {
                    boolean noEmptyFields = errorHandling
                        (
                            trdFirstNameField.getText(),
                            trdSurnameField.getText(),
                            vatNoField.getText(),
                            phoneField.getText()
                        );
                    if (noEmptyFields)
                    {
                        String vatString = vatStringErrorHandling();
                        boolean phoneOK = phoneStringErrorHandling();
                        if (vatString != null && phoneOK)
                        {
                            AccountHandler.addTradeAccount
                            (
                                trdFirstNameField.getText(),
                                trdSurnameField.getText(),
                                vatString,
                                phoneField.getText()
                            );
                            MainFrame.getTrdAccountWindow().updateStatusBar(AccountHandler.getNoOfTradeAccounts());

                            trdFirstNameField.setText("");
                            trdSurnameField.setText("");
                            vatNoField.setText("");
                            phoneField.setText("");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame,
                            "All fields must have a value!",
                            "Invalid Form Submission", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    boolean noEmptyFields = errorHandling
                        (
                            perFirstNameField.getText(),
                            perSurnameField.getText(),
                            credCardTypeField.getSelectedItem().toString(),
                            creditCardNoField.getText()
                        );
                    if (noEmptyFields)
                    {
                        if (credCardErrorHandling())
                        {
                            AccountHandler.addPersonalAccount
                            (
                                perFirstNameField.getText(),
                                perSurnameField.getText(),
                                credCardTypeField.getSelectedItem().toString(),
                                creditCardNoField.getText()
                            );
                            MainFrame.getPerAccountWindow().updateStatusBar(AccountHandler.getNoOfPersonalAccounts());
                            perFirstNameField.setText("");
                            perSurnameField.setText("");
                            credCardTypeField.setSelectedIndex(0);
                            creditCardNoField.setText("");
                        }
                    }

                    else
                    {
                        JOptionPane.showMessageDialog(frame,
                            "All fields must have a value!",
                            "Invalid Form Submission", JOptionPane.ERROR_MESSAGE);
                    }
                }
                MainFrame.getAllAccountWindow().updateStatusBar();
                AccountHandler.updateTable();

            }
            else
            {
                cardLayout.show(forms, e.getActionCommand());
                currentForm = e.getActionCommand();
            }
        }
    }
}