/**
 * Model Package for Core Functionality
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class to handle the Text File. Handles the PrintWriter,
 * the reading and writing of Text Files, creation of Directories
 * if needed and also adding accounts to the Database
 * @author Barber, Coulby
 */
public class FileHandler
{
    private static File file;
    private static File directory;
    private static File[] listOfFiles;
    private static String filePath = "AccountsIO/";
    private static File inFile;
    private static PrintWriter output;
    public static String lastFile;
    private static String fileExtension = ".txt";
    private static int backupFileNo = 0;

    /**
     * Create a new Directory
     * @author Barber
     */
    public static void createDirectory()
    {
        new File("AccountsIO").mkdir();
        directory = new File("AccountsIO/");
    }

    /**
     * Create the initial file if one doesn't exist
     * @author Barber
     */
    public static void createInitialFile()
    {
        if (lastFile == null)
        {
            file = new File(filePath + "accounts" + fileExtension);
        }
        inFile = new File(filePath + "accounts" + fileExtension);
    }

    /**
     * Set lastFile to the name of the last file number in accounts folder
     * @author Barber
     */
    public static void getLastFile()
    {
        listOfFiles = directory.listFiles();
        for (File file : listOfFiles)
        {
            if (file.isFile())
            {
                lastFile = file.getName();
            }
        }
        createInitialFile();
    }

    /**
     * Reads the file line by line, splits the line into multiple strings
     * and adds the strings to both the ArrayList of Accounts and the JTable
     * Vector.
     * @author Barber, Coulby
     * @throws IOException
     */
    public static void readFileContents() throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(inFile));

        String strLine;
        ArrayList<String> lines = new ArrayList<String>();
        while ((strLine = in.readLine()) != null)
        {
            lines.add(strLine);
        }

        for(String line : lines)
        {
            switch (line.substring(0, 3))
            {
                case "TRD":
                ArrayList<String> trdStrings = new ArrayList<String>();
                for (String str: line.split(" - "))
                {
                    trdStrings.add(str);
                }
                AccountHandler.addTradeAccountFromFile(trdStrings.get(0), trdStrings.get(1), trdStrings.get(2), trdStrings.get(3), trdStrings.get(4));
                break;

                case "PER":
                ArrayList<String> perStrings = new ArrayList<String>();
                for (String str: line.split(" - "))
                {
                    perStrings.add(str);
                }
                AccountHandler.addPersonalAccountFromFile(perStrings.get(0), perStrings.get(1), perStrings.get(2), perStrings.get(3), perStrings.get(4));
                break;

                case "Nex":
                for (String str: line.split(" - "))
                {
                    if (str.startsWith("Next Trade"))
                    {
                        Account.nextTrdAccountNo = Integer.parseInt(str.substring(27));
                    }
                    else
                    {
                        Account.nextPerAccountNo = Integer.parseInt(str.substring(30));
                    }
                }
                break;
                case "Cur":
                backupFileNo = Integer.parseInt(line.substring(28));
                break;

                default:
                break;
            }
        }
        in.close();
    }

    /**
     * Creates the Backup File by incrementing the backupFileNo
     * @author Barber
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void backupFile() throws IOException
    {
        backupFileNo++;
        File outFile = new File(filePath + "accounts" + backupFileNo + fileExtension);
        try
        {
            output = new PrintWriter(outFile);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(new FileReader(inFile));

        String strLine;
        while ((strLine = in.readLine()) != null)
        {
            output.println(strLine);
        }
        closeLink();
    }

    /**
     * Makes a link to the PrintWriter for writing to the file
     * @author Barber
     */
    public static void makeLink()
    {
        file = inFile;
        try
        {
            output = new PrintWriter(file);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Close the link to the PrintWriter
     * @author Barber
     */
    public static void closeLink()
    {
        output.close();
    }

    /**
     * Write the list of Accounts to the Text File
     * @author Barber
     */
    public static void writeToFile()
    {
        output.println(AccountHandler.listAllAccounts().toString());
        output.println("Current Backup File Number: " + backupFileNo);
    }
}