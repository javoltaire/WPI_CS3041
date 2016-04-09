package Controller;

import Controller.ContentPages.CategoriesView;
import Controller.SettingsPages.SettingsPage;
import Model.Category;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by jules on 3/31/2016.
 */
public class MainPage extends AnchorPane {
    //region FXML variables
    @FXML private ToggleButton settingsToggleButton;
    @FXML private CategoriesView categoriesView;
    @FXML private SettingsPage settingsPage;

    //endregion

    //region Constructors
    public MainPage(ObservableList<Category> categories){
        loadFXMLFile();
        addListeneers();
        categoriesView.setCategoriesList(categories);
    }
    //endregion

    //Methods
    /**
     * Loads the view portion(fxml file) for this class
     */
    private void loadFXMLFile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainPage.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try{
            loader.load();
        }
        catch(IOException excpt){
            throw new RuntimeException(excpt);
        }
    }

    /**
     * This method just add listeners to all the required controls
     */
    private void addListeneers(){
        settingsToggleButton.selectedProperty().addListener((v, oldValue, newValue) -> {
            settingsPage.setVisible(newValue);
        });
    }

    private void bindAll(){

    }
    //endregion
}
