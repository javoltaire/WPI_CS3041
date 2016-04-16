package Model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jules on 4/12/2016.
 */
@XmlRootElement (name = "Categories")
public class CategoriesListWrapper {
    //region Variables and properties
    private ListProperty<Category> categories = new SimpleListProperty<>(this, "categories", FXCollections.observableArrayList());
    //endregion

    //region Constructors
    public CategoriesListWrapper(){}
    //endregion

    //region Getters and Setters
    public ListProperty<Category> categoriesProperty() {
        return categories;
    }

    public ObservableList<Category> getCategories() {
        return categories.get();
    }

    @XmlElement (name = "Category")
    private void setCategories(ObservableList<Category> categories) {
        this.categories.set(categories);
    }
    //endregion
}
