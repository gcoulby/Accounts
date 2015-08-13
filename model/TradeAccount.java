/**
 * Model Package for Core Functionality
 */
package model;

/**
 * Sub Class of Account for Trade Accounts
 * @author Carr
 */
public class TradeAccount extends Account
{

    private String vatNumber;
    private String telNumber;

    /**
     * Empty constructor for PersonalAccount
     */
    public TradeAccount()
    {
    }

    /**
     * Constructor for TradeAccount. Instantiates class and initialises all variables.
     * @param firstName First Name as a String
     * @param surname Surname as a String
     * @param vatNumber VAT number as a string.
     * @param telNumber Telephone number as a string.
     */
    public TradeAccount(String firstName, String surname, String vatNumber, String telNumber)
    {
        super(firstName, surname);

        this.vatNumber = vatNumber;
        this.telNumber = telNumber;
    }

    /**
     * Constructor for TradeAccount. Instantiates class and initialises all variables.
     * @param accountNo Account Number as a String
     * @param firstName First Name as a String
     * @param surname Surname as a String
     * @param vatNumber VAT number as a string.
     * @param telNumber Telephone number as a string.
     */
    public TradeAccount(String accountNo, String firstName, String surname, String vatNumber, String telNumber)
    {
        super(accountNo, firstName, surname);

        this.vatNumber = vatNumber;
        this.telNumber = telNumber;
    }

    /**
     * Get the Vat Number
     * @return vatNumber VAT Number as a String
     */
    public String getVatNumber()
    {
        return vatNumber;
    }

    /**
     * Set the VAT Number
     * @param vatNumber VAT Number as a String
     */
    public void setVatNumber(String vatNumber)
    {
        this.vatNumber = vatNumber;
    }

    /**
     * Get the Telephone Number
     * @return telNumber Telephone Number as a String
     */
    public String getTelNumber()
    {
        return telNumber;
    }

    /**
     * Set the Telephone Number
     * @param telNumber Telephone Number as a String
     */
    public void setTelNumber(String telNumber)
    {
        this.telNumber = telNumber;
    }

    @Override
    public String toString()
    {
        return getAccountNo() + " - " + getFirstName() + " - " + getSurname() + " - " + vatNumber + " - " + telNumber + "\r\n";
    }

    @Override
    public void generateAccountNumber()
    {
        nextTrdAccountNo++;
        accountNo = "TRD" + addZeros(nextTrdAccountNo) + nextTrdAccountNo;
    }

}