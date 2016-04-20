package Controller;

import Controller.Content.CategoriesView;
import Controller.Content.CommandsView;
import Controller.Content.SearchResultView;
import Controller.Dialogs.Dialog;
import Controller.Settings.SettingsPage;
import Model.Category;
import Model.CategoryManager;
import Model.Command;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by jules on 3/31/2016.
 */
public class MainPage extends AnchorPane {
    //region FXML Controls
    @FXML private Button backButton;
    @FXML private TextField searchTextField;
    @FXML private ToggleButton settingsToggleButton;
    @FXML private StackPane contentRoot;
    @FXML private CategoriesView categoriesView;
    @FXML private SettingsPage settingsPage;
    @FXML private GridPane dialogRoot;
    //endregion

    //region Variables and Properties
    private Pane currentPage = null;
    private Stack<Pane> history = new Stack<>();
    private BooleanProperty canShowBackButton = new SimpleBooleanProperty(this, "canShowBackButton", false);
    //endregion

    //region Constructors
    public MainPage(){
        loadFXMLFile();
        bind();
        addListeneers();
    }
    //endregion

    //region Getters and Setters
    public boolean getCanShowBackButton() {
        return canShowBackButton.get();
    }

    public BooleanProperty canShowBackButtonProperty() {
        return canShowBackButton;
    }

    public void setCanShowBackButton(boolean canShowBackButton) {
        this.canShowBackButton.set(canShowBackButton);
    }
    //endregion

    //region FXML Methods
    @FXML private void onBackButtonClicked(){
        if(!history.empty()) {
            Pane pane = history.pop();
            canShowBackButton.setValue(!history.empty());
            if(currentPage != pane) {
                if(currentPage != null) {
                    contentRoot.getChildren().remove(currentPage);
                }
                contentRoot.getChildren().add(0,pane);
                currentPage = pane;
            }
        }
    }

    @FXML private void onSearchButtonClick(){
        search();
    }

    @FXML private void searchTextFieldOnAction(){
        search();
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
            getStylesheets().add(getClass().getResource("/Resources/StyleSheets/MainPage.css").toExternalForm());
            getStylesheets().add(getClass().getResource("/Resources/StyleSheets/MainPageMidBlue.css").toExternalForm());
        }
        catch(IOException excpt){
            throw new RuntimeException(excpt);
        }
    }

    private void bind(){
        backButton.visibleProperty().bind(canShowBackButton);
    }

    /**
     * This method add listeners to all the required controls
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
        dialogRoot.setVisible(false);
        dialogRoot.getChildren().remove(dialog);
    }

    public void navigateToCategoriesView(ObservableList<Category> categories){
        CategoriesView categoriesView = new CategoriesView();
        categoriesView.setCategoriesList(categories);
        navigate(categoriesView);
    }

    public void navigateToCommandsView(Command command){
        CommandsView commandsView = new CommandsView();
        commandsView.setCommandsList(command.getParentCategory().getCommands());
        commandsView.select(command);
        navigate(commandsView);

    }

    private void navigate(Pane pane){
        if(pane != null){
            if(currentPage != null) {
                history.push(currentPage);
                canShowBackButton.setValue(!history.empty());
                contentRoot.getChildren().remove(currentPage);
            }
            contentRoot.getChildren().add(0,pane);
            currentPage = pane;
        }
    }

    private void search(){
        String searchInput = searchTextField.getText().trim();
        if(!searchInput.isEmpty()){
            if(CategoryManager.getInstance().containsCommandKey(searchInput)) {
                Command command = CategoryManager.getInstance().getCommand(searchInput);
                navigateToCommandsView(command);
            }
            else{
                ObservableList<Command> result = CategoryManager.getInstance().getSimilar(searchInput);
                SearchResultView searchResultView = new SearchResultView();
                if(result.isEmpty()){
                    searchResultView.showNoResultError(searchInput);
                }
                else {
                    searchResultView.showResult(result);
                }
                navigate(searchResultView);
            }
            searchTextField.clear();
        }
    }
    //endregion
}
