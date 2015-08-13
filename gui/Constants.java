/**
 * Package for GUI
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Custom Constants Class which has constants for Colors, Dimensions and ImageIcons
 * Also has builder methods that build JLabels and Buttons to be added directly with
 * the correct dimensions and padding.
 * @author Coulby
 */
public class Constants
{
    // Colours
    public static final Color BACKGROUND_COLOR = new Color(255, 255, 255);
    public static final Color MIDRANGE_COLOR = new Color(220, 220, 220);
    public static final Color FOREGROUND_COLOR = new Color(0, 0, 0);
    public static final Font DEFAULT_FONT = new Font("Serif", Font.PLAIN, 15);

    // Dimensions
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    // Icons
    public static final ImageIcon HOME_ICON = new ImageIcon(Constants.class.getResource("/res/home.png"));
    public static final ImageIcon ALL_ICON = new ImageIcon(Constants.class.getResource("/res/people.png"));
    public static final ImageIcon TRD_ICON = new ImageIcon(Constants.class.getResource("/res/briefcase.png")); // http://findicons.com/icon/13481/briefcase?id=13481
    public static final ImageIcon TRD_ICON_LRG = new ImageIcon(Constants.class.getResource("/res/briefcaseLrg.png"));
    public static final ImageIcon PER_ICON = new ImageIcon(Constants.class.getResource("/res/person.png"));
    public static final ImageIcon PER_ICON_LRG = new ImageIcon(Constants.class.getResource("/res/personLrg.png"));
    public static final ImageIcon SPLASH_SCREEN = new ImageIcon(Constants.class.getResource("/res/Splash.png")); //
    public static final ImageIcon LOGO = new ImageIcon(Constants.class.getResource("/res/logo.png"));
    public static final ImageIcon PLUS_ICON = new ImageIcon(Constants.class.getResource("/res/plus.png"));

    /**
     * Creates a JLabel based on the size of the ImageIcon that is added.
     * @param image The ImageIcon to be added to the JLabel
     * @return label The Label the ImageIcon will be added to
     */
    public static final JLabel label(ImageIcon image)
    {
        JLabel label = new JLabel(image);
        setLabelSize(label, image);
        return label;
    }

    /**
     * Creates a JButton for buttons that have no text. The
     * size is determined by the ImageIcon's size.
     * @param image The ImageIcon to be added to the Button
     * @param actionCommand The String that will be sent to the ActionListener
     * @return button The JButton that the ImageIcon will be added to
     */
    public static final JButton button(ImageIcon image, String actionCommand)
    {
        JButton button = new JButton(image);
        button.setActionCommand(actionCommand);
        button.setPreferredSize(new Dimension(image.getIconWidth() + 10, image.getIconHeight() + 10));
        return button;
    }

    /**
     * Returns the size of and ImageIcon as a dimension(width,height)
     * @param image The ImageIcon in which to the dimensions from
     * @return Dimension returned from the getIconWidth and getIconHeight methods 
     */
    public static final Dimension getImageSize(ImageIcon image)
    {
        return new Dimension(image.getIconWidth(), image.getIconHeight());
    }

    /**
     * Sets the size of a label based on the dimensions of the ImageIcon
     * @param label The Label in which to manipulate
     * @param image The ImageIcon to get Dimension from
     */
    private static void setLabelSize(JLabel label, ImageIcon image)
    {
        label.setSize(getImageSize(image));
    }
}