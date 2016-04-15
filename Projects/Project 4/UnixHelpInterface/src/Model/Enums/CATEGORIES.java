package Model.Enums;

/**
 * @Author Jules Votaire on 4/2/2016.
 */
public enum CATEGORIES {
    //region Enum Declarations
    RECENT("Recent"),
    FILEANDFOLDER("File System"),
    PROCESSMANAGEMENT("Process Management"),
    NETWORK("Network"),
    CUSTOM("Custom");
    //endregion

    //region variables
    private final String categoryName;
    //endregion

    //region Constructors

    /**
     * Inistializes a new Category enum with the given value
     * @param categoryName The name of the category
     */
    CATEGORIES(final String categoryName){
        this.categoryName = categoryName;
    }
    //endregion
    //region Methods
    @Override
    public String toString(){
        return categoryName;
    }
    //endregion


}
