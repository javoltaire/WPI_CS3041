package Controller.ContentPages;

import Controller.CustomControls.CategoryListCell;
import Controller.CustomControls.CommandListCell;
import Model.Category;
import Model.Command;
import Model.Enums.CATEGORIES;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
    @FXML private GridPane categoryDetailsContainer;
   private ListView<Command> commandsListView;
    @FXML private Label noCatSelectedLabel;
    @FXML private Label emptyCategoryLabel;
    @FXML private Button addNewButton;
    //endregion

    //region Variables and properties
    private ListProperty<Category> categories = new SimpleListProperty<>();

    private BooleanProperty isListEmpty = new SimpleBooleanProperty(this, "isListEmpty", true);

    private BooleanProperty noCatSelected = new SimpleBooleanProperty(this, "noCatSelected", true);

    private BooleanProperty isCommandsListEmpty = new SimpleBooleanProperty(this, "isCommandsListEmpty", false);

    private BooleanProperty canAddNew = new SimpleBooleanProperty(this, "canAddNew", false);

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

    public void setIsCommandsListEmpty(boolean isCommandsListEmpty) {
        this.isCommandsListEmpty.set(isCommandsListEmpty);
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

//        commandsListView.setCellFactory(new Callback<ListView<Command>, ListCell<Command>>() {
//            @Override
//            public ListCell call(ListView<Command> param) {
//                return new CommandListCell();
//            }
//        });
    }

    private void bind(){
        emptyListLabel.visibleProperty().bind(isListEmpty);
        contentPane.visibleProperty().bind(isListEmpty.not());
        categoriesListView.itemsProperty().bind(categories);
        noCatSelectedLabel.visibleProperty().bind(noCatSelected);
        emptyCategoryLabel.visibleProperty().bind(isCommandsListEmpty);
        addNewButton.visibleProperty().bind(canAddNew);
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

        categoriesListView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if(newValue != null) {
                if(CATEGORIES.CUSTOM.isEqualTo(newValue.getName()))
                    canAddNew.setValue(true);
                else
                    canAddNew.setValue(false);

                if (newValue.getCommands().size() > 0) {
                    if(commandsListView != null) {
                        categoryDetailsContainer.getChildren().remove(commandsListView);
                        commandsListView.visibleProperty().unbind();
                    }
                    commandsListView = new ListView<Command>(newValue.getCommands());
                    commandsListView.setCellFactory(new Callback<ListView<Command>, ListCell<Command>>() {
                        @Override
                        public ListCell call(ListView<Command> param) {
                            return new CommandListCell();
                        }
                    });
                    commandsListView.visibleProperty().bind(noCatSelected.not().and(isCommandsListEmpty.not()));
                    categoryDetailsContainer.getChildren().add(commandsListView);



                    isCommandsListEmpty.set(false);
                }
                else{
                    isCommandsListEmpty.setValue(true);
                }
                noCatSelected.set(false);
            }
            else{
                noCatSelected.set(true);
            }

        });
    }

    public void setCategoriesList(ObservableList<Category> categories){
        this.categories.set(categories);

    }
    //endregion

}
