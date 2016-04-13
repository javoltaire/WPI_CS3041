package Controller.Content;

import Controller.CustomControls.CategoryListCell;
import Controller.CustomControls.SimpleCommandListCell;
import Model.Category;
import Model.Command;
import Model.Item;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;

/**
 * Created by jules on 4/11/2016.
 */
public class CommandsView extends AnchorPane {
    //region FXML Controls
    @FXML private ListView<Command> commandsListView;
    @FXML private Label commandNameLabel;
    @FXML private Label commandDescriptionLabel;
    @FXML private Label commandDetailsLabel;
    @FXML private ListView<Item> optionsListView;
    @FXML private ListView<String> formatsListView;
    @FXML private ListView<Item> examplesListView;
    @FXML private Hyperlink commandSourceLink;

    //endregion

    //region Constructors
    public CommandsView(){
        loadFXMLFile();
        addListeners();
    }
    //endregion

    //region Helper Methods
    /**
     * Loads the view portion(fxml file) for this class
     */
    private void loadFXMLFile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/Contents/CommandsView.fxml"));
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
        commandsListView.setCellFactory(new Callback<ListView<Command>, ListCell<Command>>() {
            @Override
            public ListCell<Command> call(ListView<Command> param) {
                return new SimpleCommandListCell();
            }
        });
    }

    private void addListeners(){
        commandsListView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->{
            updateContent(newValue);
        });
    }

    public void setCommandsList(ObservableList<Command> commands){
        commandsListView.setItems(commands);
        customizeControls();
    }

    private void updateContent(Command command){
        commandNameLabel.setText(command.getName());
        commandDescriptionLabel.setText(command.getDescription());
        commandDetailsLabel.setText(command.getDetails());
        optionsListView.setItems(command.optionsProperty());
        formatsListView.setItems(command.formatsProperty());
        examplesListView.setItems(command.examplesProperty());
        commandSourceLink.setText(command.getSourceLink());
    }

    public void select(Command command){
        commandsListView.getSelectionModel().select(command);
    }


    //endregion
}
