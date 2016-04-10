package Controller.ContentPages;

import Controller.CustomControls.CategoryListCell;
import Model.Category;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jules on 4/9/2016.
 */
public class CategoriesView extends AnchorPane {
    //region FXML variables
    @FXML private Label emptyListLabel;
    @FXML private BorderPane contentPane;
    @FXML private ListView<Category> categoriesListView;
    //endregion

    //region Variables and properties
    private ListProperty<Category> categories = new SimpleListProperty<>();

    private BooleanProperty isListEmpty = new SimpleBooleanProperty(this, "isListEmpty", true);
    //endregion

    //region Constructor
    public CategoriesView (){
        loadFXMLFile();
        customizeControls();
        setListeners();

        Category cat = new Category("SomeCategory");
        List<Category> list = new ArrayList<>();
        list.add(cat);
        setCategoriesList(FXCollections.observableArrayList(list));
        bind();
    }
    //endregion

    //region Getters and Setters
    public BooleanProperty isListEmptyProperty(){
        return isListEmpty;
    }

    public boolean getIsListEmpty(){
        return isListEmpty.get();
    }

    public void setIsListEmpty(boolean newValue){
        isListEmpty.set(newValue);
    }

    //endregion

    //region Helper Methods
    /**
     * Loads the view portion(fxml file) for this class
     */
    private void loadFXMLFile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/ContentPages/CategoriesView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try{
            loader.load();
        }
        catch(IOException excpt){
            throw new RuntimeException(excpt);
        }
    }

    private void customizeControls(){
        categoriesListView.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {
            @Override
            public ListCell call(ListView<Category> param) {
                return new CategoryListCell();
            }
        });
    }

    private void bind(){
        emptyListLabel.visibleProperty().bind(isListEmpty);
        contentPane.visibleProperty().bind(isListEmpty.not());
        categoriesListView.itemsProperty().bind(categories);
    }

    private void setListeners(){
        categories.addListener(new ListChangeListener<Category>() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                if (categories.size() > 0)
                    setIsListEmpty(false);
                else
                    setIsListEmpty(true);
            }
        });
    }

    public void setCategoriesList(ObservableList<Category> categories){
        this.categories.set(categories);
    }
    //endregion

}
