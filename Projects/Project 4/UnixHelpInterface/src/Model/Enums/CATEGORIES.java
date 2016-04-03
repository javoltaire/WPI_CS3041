package Model.Enums;

/**
 * @Author Jules Votaire on 4/2/2016.
 */
public enum CATEGORIES {
    //region Enum Declarations
    RECENT("Recent"),
    FILEANDFOLDER("File System"),
    COMPRESSING("Compressing"),
    PROCESSMANAGEMENT("Process Management"),
    NETWORK("Network"),
    CUSTOM("Custom");
    //endregion

    //region variables
    private final String catName;
    //endregion

    //region Constructors

    /**
     * Inistializes a new Category enum with the given value
     * @param catName The name of the category
     */
    CATEGORIES(final String catName){
        this.catName = catName;
    }
    //endregion

    //region Methods

    /**
     * Converts this enum class to a string
     * @return The name of the category
     */
    @Override public String toString(){
        return catName;
    }
    //endregion


}
