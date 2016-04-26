package Model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author Jules Voltaire 4/12/2016.
 * This class is a wrapping class for the to save and read data to and from the xml file
 */
@XmlRootElement (name = "Categories")
public class CategoriesListWrapper {
    //region Variables and properties
    /**
     * list to hold all the categories
     */
    private ListProperty<Category> categories = new SimpleListProperty<>(this, "categories", FXCollections.observableArrayList());
    //endregion

    //region Constructors
    /**
     * Initializes a new instance of the class
     */
    public CategoriesListWrapper(){}
    //endregion

    //region Getters and Setters

    /**
     * gets the list property containing all the categories
     * @return
     */
    public ListProperty<Category> categoriesProperty() {
        return categories;
    }

    /**
     * Gets the value of all the categories from the categories ListProperty
     * @return
     */
    public ObservableList<Category> getCategories() {
        return categories.get();
    }

    /**
     * Sets the value of the categories ListProperty
     * @param categories
     */
    @XmlElement (name = "Category")
    private void setCategories(ObservableList<Category> categories) {
        this.categories.set(categories);
    }
    //endregion
}
