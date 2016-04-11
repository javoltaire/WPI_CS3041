package Controller;

import Controller.Content.CategoriesView;
import Controller.Content.CommandsView;
import Controller.Dialogs.Dialog;
import Controller.Settings.SettingsPage;
import Model.Category;
import Model.Command;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

/**
 * Created by jules on 3/31/2016.
 */
public class MainPage extends AnchorPane {
    //region FXML Controls
    @FXML private ToggleButton settingsToggleButton;
    @FXML private StackPane contentRoot;
    @FXML private CategoriesView categoriesView;
    @FXML private SettingsPage settingsPage;
    @FXML private GridPane dialogRoot;
    //endregion

    //region Variables
    private Pane currentPage = null;
    //endregion

    //region Constructors
    public MainPage(){
        loadFXMLFile();
        addListeneers();
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

    public void showDialog(Dialog dialog){
        dialogRoot.getChildren().add(dialog);
        dialogRoot.setVisible(true);

    }

    public void exitDialog(Dialog dialog){
        dialogRoot.getChildren().remove(dialog);
        dialogRoot.setVisible(false);
    }

    public void navigateToCategoriesView(ObservableList<Category> categories){
        if(currentPage != null)
            contentRoot.getChildren().remove(currentPage);
        CategoriesView categoriesView = new CategoriesView();
        categoriesView.setCategoriesList(categories);
        contentRoot.getChildren().add(0,categoriesView);
        currentPage = categoriesView;
    }

    public void navigateToCommandsView(Command command){
        if(currentPage != null)
            contentRoot.getChildren().remove(currentPage);
        CommandsView commandsView = new CommandsView();

        contentRoot.getChildren().add(0,commandsView);
        currentPage = commandsView;
    }

    public void navigateToSearchResult(){

    }
    //endregion
}
