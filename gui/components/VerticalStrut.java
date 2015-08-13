/**
 * Package for GUI components
 */
package gui.components;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JPanel;

/**
 * A Class to help with the layout of components
 * @author Barber, Coulby
 */
public class VerticalStrut
{
    /**
     * Adds rows to a GridLayout and populates them with VerticalStruts.
     * @param number The number of Rows to be added to the grid
     * @param grid The GridLayout to be added to
     * @param jp the JPanel that contains the grid.
     */
    public VerticalStrut(int number, GridLayout grid, JPanel jp)
    {
        for(int i=0;i<number;i++)
        {
            grid.setRows(grid.getRows() + 1);
            jp.add(Box.createVerticalStrut(10));
        }
    }
}