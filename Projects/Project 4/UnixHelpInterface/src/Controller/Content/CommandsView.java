package Controller.Content;

import Controller.CustomControls.ListCells.ItemListCell;
import Controller.CustomControls.ListCells.SimpleCommandListCell;
import Controller.Dialogs.MessageDialog;
import Model.CategoryManager;
import Model.Command;
import Model.Item;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by jules on 4/11/2016.
 */
public class CommandsView extends AnchorPane {
    //region FXML Controls
    @FXML private BorderPane contentPane;
    @FXML private ListView<Command> simpleCommandsListView;
    @FXML private VBox detailsVBox;
    @FXML private Label nameLabel;
    @FXML private Label commandNameLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label commandDescriptionLabel;
    @FXML private Label detailsLabel;
    @FXML private Label commandDetailsLabel;
    @FXML private Label optionsLabel;
    @FXML private ListView<Item> optionsListView;
    @FXML private Label formatsLabel;
    @FXML private ListView<String> formatsListView;
    @FXML private Label examplesLabel;
    @FXML private ListView<Item> examplesListView;
    @FXML private Label sourceLinkLabel;
    @FXML private Hyperlink commandSourceLink;
    //endregion

    //region Variables and Properties
    private final double LABEL_WIDTH = 75;
    //endregion

    //region Constructors
    public CommandsView(){
        loadFXMLFile();
        setProperties();
        addListeners();
    }
    //endregion

    //region FXML methods
    @FXML private void onLinkClicked(){
        try {
            Desktop desktop = Desktop.getDesktop();
            String toSearch = commandSourceLink.getText().trim();
            desktop.browse(new URI(toSearch));
        } catch (IOException e) {
            MessageDialog dialog = new MessageDialog("Error", "Error has occurred while trying to navigate to this link.\n" + e.getMessage());
            dialog.showDialog();
        } catch (URISyntaxException e) {
            MessageDialog dialog = new MessageDialog("Error", "Invalid URL.\n" + e.getMessage());
            dialog.showDialog();
        }
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

    private void setProperties(){
        nameLabel.setPrefWidth(LABEL_WIDTH);
        descriptionLabel.setPrefWidth(LABEL_WIDTH);
        detailsLabel.setPrefWidth(LABEL_WIDTH);
        optionsLabel.setPrefWidth(LABEL_WIDTH);
        formatsLabel.setPrefWidth(LABEL_WIDTH);
        examplesLabel.setPrefWidth(LABEL_WIDTH);
        sourceLinkLabel.setPrefWidth(LABEL_WIDTH);

    }

    private void customizeLisviews(){
        simpleCommandsListView.setCellFactory(new Callback<ListView<Command>, ListCell<Command>>() {
            @Override
            public ListCell<Command> call(ListView<Command> param) {
                return new SimpleCommandListCell();
            }
        });

        optionsListView.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>() {
            @Override
            public ListCell<Item> call(ListView<Item> param) {
                return new ItemListCell();
            }
        });

        examplesListView.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>() {
            @Override
            public ListCell<Item> call(ListView<Item> param) {
                return new ItemListCell();
            }
        });
    }

    private void addListeners(){
        simpleCommandsListView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->{
            updateContent(newValue);
            CategoryManager.getInstance().addToRecent(newValue);
        });
    }

    public void setCommandsList(ObservableList<Command> commands){
        simpleCommandsListView.setItems(commands);
        customizeLisviews();
    }

    private void updateContent(Command command){
        commandNameLabel.setText(command.getName());
        commandDescriptionLabel.setText(command.getDescription());
        commandDetailsLabel.setText(command.getDetails());
        optionsListView.setItems(command.optionsProperty());
        optionsListView.setPrefHeight(optionsListView.getItems().size() * 25.0);
        formatsListView.setItems(command.formatsProperty());
        formatsListView.setPrefHeight(formatsListView.getItems().size() * 25.0);
        examplesListView.setItems(command.examplesProperty());
        examplesListView.setPrefHeight(examplesListView.getItems().size() * 25.0);
        commandSourceLink.setText(command.getSourceLink());
    }

    public void select(Command command){
        simpleCommandsListView.getSelectionModel().select(command);
    }


    //endregion
}
