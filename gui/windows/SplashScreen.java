/**
 * Package for GUI Windows
 */
package gui.windows;

import gui.Constants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import model.FileHandler;

/**
 * SplashScreen Class to handle processes on startup whilst displaying a logo.
 * This Class also serves a secondary purpose. A JProgress bar was added to the
 * Frame which allows incremental progression. This allowed us to utilise it
 * to set up a connection with the file reader and read in all the data prior to
 * loading the MainFrame.
 * 
 * @author Graham Coulby
 * @version 1.0
 */
public class SplashScreen extends JFrame
{
    private JProgressBar progressBar;

    /**
     * Initialises the Splash Screen Frame
     * adds the JProgress bar as well as setting
     * the layout and the background image
     */
    public void init()
    {
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        setLayout(new BorderLayout());
        setSize(new Dimension(Constants.getImageSize(Constants.SPLASH_SCREEN).width, Constants.getImageSize(Constants.SPLASH_SCREEN).height + 20));
        setResizable(false);
        setUndecorated(true);
        setLocation(((Constants.SCREEN_SIZE.width - getWidth()) / 2), ((Constants.SCREEN_SIZE.height - getHeight()) / 2));
    }

    /**
     * Constructor for the Splash Screen.
     * Runs the load sequence, or manual loads the methods
     * in the case of a thrown exception
     */
    public SplashScreen()
    {
        init();

        add(Constants.label(Constants.SPLASH_SCREEN), BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);

        setVisible(true); // display swing frame.

        // Runs the loading bar (throws Interruption exception)
        try
        {
            load(); 
        } 
        catch (InterruptedException e)
        {
            manualLoad();
        }
    }

    /**
     * This is the method that calls the method
     * to set the JProgress bar. The method increments
     * to 100 and at each percentage increment of 20
     * certain methods are called to build a connection
     * to the FileHandler.
     * @throws InterruptedException
     */
    public void load() throws InterruptedException
    {
        int interval = 50;
        for (int i = 0; i < 101; ++i)
        {
            switch (i)
            {
                case 20:
                setProgress(i, "Checking Director");
                FileHandler.createDirectory();
                FileHandler.getLastFile();
                Thread.sleep(interval);
                break;
                case 40:
                setProgress(i, "Checking Files");
                FileHandler.createInitialFile();
                Thread.sleep(interval);
                break;
                case 60:
                setProgress(i, "Reading File Contents");
                try
                {
                    FileHandler.readFileContents();
                    FileHandler.backupFile();
                } 
                catch (IOException e)
                {
                    System.out.println("File not found. New File Created.");
                }
                Thread.sleep(interval);
                break;
                case 80:
                setProgress(i, "Making 'Write' Link");
                FileHandler.makeLink();
                Thread.sleep(interval);
                break;

                default:
                setProgress(i, "Loading...");
                // Read text file
                Thread.sleep(5);
                break;
            }
        }
    }

    /**
     * Loads the startup sequence from FileHandler
     * in case the JProgressBar throws an interruption
     * exception 
     */
    private void manualLoad()
    {
        FileHandler.createDirectory();
        FileHandler.getLastFile();
        FileHandler.createInitialFile();
        try
        {
            FileHandler.readFileContents();
            FileHandler.backupFile();
        } catch (Exception e1)
        {
            System.out.println("File not found. New File Create");
        }
        FileHandler.makeLink();
    }

    /**
     * Sets the progress of the JProgressBar
     * @param i The percentage to set
     * @param str The message to Display
     */
    private void setProgress(int i, String str)
    {
        progressBar.setValue(i);
        progressBar.setString(i + "% " + str);
    }

    /**
     * Closes the Splash Screen and disposes of the JFrame
     */
    public void closeSplash()
    {
        setVisible(false);
        dispose();
    }
}