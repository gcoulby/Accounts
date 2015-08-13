/**
 * Package for GUI components
 */
package gui.components;

import gui.Constants;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JPanel;

/**
 * Class for building Row objects
 * Row Objects are FlowLayout JPanels that can be nested into a GridLayout
 * for more flexibility.
 * @author Barber, Coulby
 *
 */
public class Row extends JPanel
{
    /**
     * A Constructor for building the JPanel with one component
     * @param c The component to add to the JPanel
     * @param align The Alignment of the FlowLayout
     * @param setColor A boolean to say whether the background color should be set.
     */
    public Row(Component c, int align, boolean setColor)
    {
        setBackground(setColor == true ? true : false);
        setSize(40, 0);
        setLayout(new FlowLayout(align));
        add(c);
    }

    /**
     * A Constructor for building the JPanel with two components
     * @param c The component to add to the JPanel
     * @param c2 The second component to add to the JPanel
     * @param align The Alignment of the FlowLayout
     * @param setColor A boolean to say whether the background color should be set.
     */
    public Row(Component c, Component c2, int align, boolean setColor)
    {
        setBackground(setColor == true ? true : false);
        setSize(40, 0);
        setLayout(new FlowLayout(align));
        add(c);
        add(c2);
    }

    /**
     * A Constructor for building the JPanel with three components
     * @param c The component to add to the JPanel
     * @param c2 The second component to add to the JPanel
     * @param align The Alignment of the FlowLayout
     * @param setColor A boolean to say whether the background color should be set.
     */
    public Row(Component c, Component c2, Component c3, int align, boolean setColor)
    {
        setBackground(setColor == true ? true : false);
        setSize(40, 0);
        setLayout(new FlowLayout(align));
        add(c);
        add(c2);
        add(c3);
    }

    /**
     * Sets the background color of added components to the background
     * color defined in the Constants Class
     * @param setColor A boolean control over whether the color should be set. 
     */
    public void setBackground(boolean setColor)
    {
        if(setColor==true)
        {
            setBackground(Constants.BACKGROUND_COLOR);
        }
    }
}