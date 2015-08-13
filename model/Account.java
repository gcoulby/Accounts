/**
 * Model Package for Core Functionality
 */
package model;

/**
 * Abstract class to handle information shared by trade and personal accounts.
 * @author Barber, Carr, Coulby
 */
public abstract class Account
{
    private Name firstName;
    private Name surname;
    protected String accountNo;
    protected static int nextTrdAccountNo = 0;
    protected static int nextPerAccountNo = 0;

    /**
     * Empty constructor for Account
     */
    public Account()
    {
    }

    /**
     * Constructor for Account. Generates unique account number and sets the
     * first name and surname
     * 
     * @param firstName First name as a String
     * @param surname Surname as a String
     * @author Carr
     */
    public Account(String firstName, String surname)
    {
        generateAccountNumber();
        this.firstName = new Name(firstName);
        this.surname = new Name(surname);
    }

    /**
     * Constructor for Account when accounts are added from a text file sets the
     * account number, the first name and surname.
     * 
     * @param firstName First name as a String
     * @param surname Surname as a String
     * @author Carr
     */
    public Account(String accountNo, String firstName, String surname)
    {
        this.accountNo = accountNo;
        this.firstName = new Name(firstName);
        this.surname = new Name(surname);
    }

    /**
     * Gets Full Name of the account holder
     * @return fullName Full Name as a String
     */
    public String getNames()
    {
        return firstName.getName() + " " + surname.getName();
    }

    /**
     * Gets the first name of the account holder
     * @return firstName as a String
     */
    public String getFirstName()
    {
        return firstName.getName();
    }

    /**
     * Gets the surname of the account holder
     * @return surame as a String
     */
    public String getSurname()
    {
        return surname.getName();
    }

    /**
     * Sets a new first name
     * @param newName New Name as a String
     */
    public void setFirstName(String newName)
    {
        this.firstName.setName(newName);
    }

    /**
     * Sets a new surname
     * @param newName New Name as a String
     */
    public void setSurname(String _newName)
    {
        surname.setName(_newName);
    }

    /**
     * Gets the account number of the account holder
     * @return accountNo Account Number as a String
     */
    public String getAccountNo()
    {
        return accountNo;
    }

    /**
     * Custom toString method for outputting the
     * accounts to a string
     */
    public abstract String toString();

    /**
     * Generates a unique account number 
     */
    public abstract void generateAccountNumber();

    /**
     * Adds zeros to account number if the number is
     * less than 5 digits
     * @param accountNo Account Number as a String
     * @return str String of zeros to be added to the account number
     */
    public String addZeros(int accountNo)
    {
        String str = "";
        int length = 5 - String.valueOf(accountNo).length();
        for (int i = 0; i < length; i++)
        {
            str += "0";
        }
        return str;
    }

}