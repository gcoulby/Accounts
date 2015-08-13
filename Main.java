import gui.windows.MainFrame;
import gui.windows.SplashScreen;

/**
 * The Main Method Class for the program
 * @author Carr
 */
public class Main
{
    /**
     * main method for the program
     * loads the splash screen
     * then loads the programs MainFrame
     * @param args String[] of args to be passed in 
     * to the main methods. (Required by all java programs)
     */
    public static void main(String[] args)
    {
        SplashScreen ss = new SplashScreen();
        ss.closeSplash();
        new MainFrame();
    }
}