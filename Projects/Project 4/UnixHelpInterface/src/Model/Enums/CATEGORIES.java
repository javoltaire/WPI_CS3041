package Model.Enums;

/**
 * @Author Jules Votaire on 4/2/2016.
 */
public enum CATEGORIES {
    //region Enum Declarations
    RECENT("Recent", "recent"),
    FILEANDFOLDER("File System", "fileanddirectory"),
    PROCESSMANAGEMENT("Process Management", "processmanagement"),
    NETWORK("Network", "network"),
    CUSTOM("Custom", "custom");
    //endregion

    //region variables
    private final String formalName;

    private final String xmlName;
    //endregion

    //region Constructors

    /**
     * Inistializes a new Category enum with the given value
     * @param formalName The nicely formatted name of the category
     * @param xmlName The formatted name to be save as an xml tag
     */
    CATEGORIES(final String formalName, final String xmlName){
        this.formalName = formalName;
        this.xmlName = xmlName;
    }
    //endregion

    //region Getters
    public String getFormalName(){
        return formalName;
    }

    public String getXmlName(){
        return xmlName;
    }
    //endregion

    //region Methods
    /**
     * Compares the given string to the xml name or the formal name
     * @param value The string to compare
     * @return true if the value mathches the xml name of the formal name, false otherwise
     */
    public boolean isEqualTo(String value){
        return formalName.equals(value) || xmlName.equals(value);
    }
    //endregion


}
