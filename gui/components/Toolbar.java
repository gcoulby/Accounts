/**
 * Package for GUI components
 */
package gui.components;

import gui.Constants;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The Class for building the navigation toolbar.
 * @author Barber, Carr, Coulby
 */
public class Toolbar extends JPanel
{
    private JButton allAccButton;
    private static JButton trdAccButton;
    private static JButton perAccButton;
    private JButton homeButton;
    private JButton plusButton;
    private JPanel jPW = new JPanel(new FlowLayout());
    private JPanel jPC = new JPanel(new FlowLayout());
    private JPanel jPE = new JPanel(new FlowLayout());

    /**
     * The ToolBar constructor for building the navigation toolbar.
     * @author Barber, Coulby
     */
    public Toolbar(){
        setBorder(BorderFactory.createEtchedBorder());

        allAccButton = new JButton("All Accounts", Constants.ALL_ICON);
        trdAccButton = new JButton("Trade Accounts", Constants.TRD_ICON);
        perAccButton = new JButton("Personal Accounts", Constants.PER_ICON);

        homeButton = Constants.button(Constants.HOME_ICON, "Home");
        plusButton = Constants.button(Constants.PLUS_ICON, "Plus");

        setBackground(Constants.MIDRANGE_COLOR);
        setForeground(Constants.FOREGROUND_COLOR);

        jPW.add(homeButton);
        jPC.add(allAccButton);
        jPC.add(trdAccButton);
        jPC.add(perAccButton);

        jPE.add(plusButton);

        setLayout(new BorderLayout());
        add(jPW, BorderLayout.WEST);
        add(jPC, BorderLayout.CENTER);
        add(jPE, BorderLayout.EAST);
    }

    /**
     * A getter to get the 'Trade Account' button.
     * @author Carr
     * @return perAccountButton The Personal Account button to get.
     */
    public static JButton getTrdButton()
    {
        return trdAccButton;
    }

    /**
     * A getter to get the 'Personal Account' button.
     * @author Carr
     * @return perAccountButton The Personal Account button to get.
     */
    public static JButton getPerButton()
    {
        return perAccButton;
    }

    /**
     * A setter to add an action listener to the 'Home' button from another class
     * @author Carr
     * @param listener the ActionListener that will listen to the button 
     */
    public void addHomeListener(ActionListener listener){
        homeButton.addActionListener(listener);
    }

    /**
     * A setter to add an action listener to the 'All Accounts' button from another class
     * @author Carr
     * @param listener the ActionListener that will listen to the button 
     */
    public void addAllAccountListener(ActionListener listener){
        allAccButton.addActionListener(listener);
    }

    /**
     * A setter to add an action listener to the 'Trade Accounts' button from another class
     * @author Carr
     * @param listener the ActionListener that will listen to the button 
     */
    public void addTrdAccountListener(ActionListener listener){
        trdAccButton.addActionListener(listener);
    }

    /**
     * A setter to add an action listener to the 'Personal Accounts' button from another class
     * @author Carr
     * @param listener the ActionListener that will listen to the button 
     */
    public void addPerAccountListener(ActionListener listener){
        perAccButton.addActionListener(listener);
    }

    /**
     * A setter to add an action listener to the 'Plus' button from another class
     * @author Carr
     * @param listener the ActionListener that will listen to the button 
     */
    public void addPlusListener(ActionListener listener){
        plusButton.addActionListener(listener);
    }

}
