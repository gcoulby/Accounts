/**
 * Model Package for Core Functionality
 */
package model;

/**
 * Class for instantiating names
 * @author Carr
 */
public class Name
{
    private String name;
    /**
     * Constructor for name class 
     */
    public Name(String name)
    {
        setName(name);
    }

    /**
     * Changes name
     * @param name to be set as a string
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the name as a string
     * @return name as a string
     */
    public String getName()
    {
        return name;
    }
}