/**
 * Model Package for Core Functionality
 */
package model;

/**
 * Personal account holds the values for credCardNo and credCardType
 * @author Carr
 */
public class PersonalAccount extends Account
{

    private String credCardNo;
    private String credCardType;

    /**
     * Empty constructor for PersonalAccount
     */
    public PersonalAccount()
    {
    }

    /**
     * Constructor for PersonalAccount calls the super class of Account
     * This constructor is used when adding from the GUI
     * @param firstName First Name as a String
     * @param surname Surname as a String
     * @param credCardType Credit Card Type as a String 
     * @param credCardNo Credit Card No as a String
     */
    public PersonalAccount(String firstName, String surname, String credCardType, String credCardNo)
    {
        super(firstName, surname);
        this.credCardNo = credCardNo;
        this.credCardType = credCardType;
    }

    /**
     * Constructor for PersonalAccount calls the super class of Account
     * This constructor is used when adding from a text file on startup
     * @param accountNo Account Number as a String
     * @param firstName First Name as a String
     * @param surname Surname as a String
     * @param credCardType Credit Card Type as a String 
     * @param credCardNo Credit Card No as a String
     */
    public PersonalAccount(String accountNo, String firstName, String surname, String credCardType, String credCardNo)
    {
        super(accountNo, firstName, surname);
        this.credCardNo = credCardNo;
        this.credCardType = credCardType;
    }

    /**
     * Get the Credit Card Number
     * @return credCardNo Credit card number as a String
     */
    public String getCredCardNo()
    {
        return credCardNo;
    }

    /**
     * Set the credit card number
     * @param credCardNo Credit Card Number as String
     */
    public void setCredCardNo(String credCardNo)
    {
        this.credCardNo = credCardNo;
    }

    /**
     * Get the Credit Card Type
     * @return credCardType Credit Card Type as a String
     */
    public String getCredCardType()
    {
        return credCardType;
    }

    /**
     * Set the credit card Type
     * @param credCardType Credit Card Type as a String
     */
    public void setCredCardType(String credCardType)
    {
        this.credCardType = credCardType;
    }

    @Override
    public String toString()
    {
        return getAccountNo() + " - " + getFirstName() + " - " + getSurname() + " - " + credCardType + " - " + credCardNo + "\r\n";
    }

    @Override
    public void generateAccountNumber()
    {
        nextPerAccountNo++;
        accountNo = "PER" + addZeros(nextPerAccountNo) + nextPerAccountNo;
    }

}