package Controller.Dialogs;

import Controller.CustomControls.ListCells.ItemListCell;
import Controller.CustomControls.ListCells.SimpleCommandListCell;
import Model.Category;
import Model.CategoryManager;
import Model.Command;
import Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.io.IOException;

/**
 * Created by jules on 4/11/2016.
 */
public class DetailedCommandDialog extends Dialog {
    //region FXML variables
    @FXML private TextField nameTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextArea detailsTextArea;
    @FXML private ListView<Item> optionsListView;
    @FXML private TextField optionNameTextField;
    @FXML private TextField optionDescriptionTextField;
    @FXML private TextField formatTextField;
    @FXML private ListView<String> formatsListView;
    @FXML private ListView<Item> examplesListVIew;
    @FXML private TextField exampleNameTextField;
    @FXML private TextField exampleDescriptionTextField;
    //endregion

    //region variable
    private Command command;
    private boolean isNew;
    private ObservableList<Item> newOptions = FXCollections.observableArrayList();
    private ObservableList<String> newFormats = FXCollections.observableArrayList();
    private ObservableList<Item> newExamples = FXCollections.observableArrayList();

    //endregion

    //region Constructors

    /**
     * Initializes a new instance of this class
     * @param command The command context for the pane
     * @param isNew tells whether this is a new command or just editing one
     */
    public DetailedCommandDialog(Command command, boolean isNew){
        super();
        this.command = command;
        this.isNew = isNew;
        loadFXMLFile();
        customizeLisviews();
    }
    //endregion

    //FXML Methods for Event Handling
    @FXML private void onCancelButtonClick(){
        exitDialog();
    }

    @FXML private void onSaveButtonClick(){
        command.setName(nameTextField.getText().trim());
        command.setDescription(descriptionTextField.getText().trim());
        command.setDetails(detailsTextArea.getText().trim());
        command.getOptions().addAll(newOptions);
        command.getFormats().addAll(newFormats);
        command.getExamples().addAll(newExamples);
        if(isNew){
            CategoryManager.getInstance().addCommand(command);
        }
        super.exitDialog();
    }

    @FXML private void onAddNewOptionButtonClick(){
        String opName = optionNameTextField.getText().trim();
        String opDesc = optionDescriptionTextField.getText().trim();
        Item item = new Item(opName, opDesc);
        newOptions.add(item);
        optionsListView.getItems().add(item);
        optionsListView.setPrefHeight(optionsListView.getItems().size() * 25);
        optionNameTextField.clear();
        optionDescriptionTextField.clear();
    }

    @FXML private void onAddNewFormatButtonClick(){
        String fm = formatTextField.getText().trim();
        newFormats.add(fm);
        formatsListView.getItems().add(fm);
        formatsListView.setPrefHeight(formatsListView.getItems().size() * 25);
        formatTextField.clear();
    }

    @FXML private void onAddNewExampleButtonClick(){
        String exName = exampleNameTextField.getText().trim();
        String exDesc = exampleDescriptionTextField.getText().trim();
        Item item = new Item(exName, exDesc);
        newExamples.add(item);
        examplesListVIew.getItems().add(item);
        examplesListVIew.setPrefHeight(examplesListVIew.getItems().size() * 25);
        exampleNameTextField.clear();
        exampleDescriptionTextField.clear();
    }
    //endregion

    //Helper Methods
    /**
     * Loads the view portion(fxml file) for this class
     */
    private void loadFXMLFile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/Dialogs/DetailedCommandDialog.fxml"));
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
     * changes the cell factories of some of the listviews
     */
    private void customizeLisviews(){
        optionsListView.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>() {
            @Override
            public ListCell<Item> call(ListView<Item> param) {
                return new ItemListCell();
            }
        });

        examplesListVIew.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>() {
            @Override
            public ListCell<Item> call(ListView<Item> param) {
                return new ItemListCell();
            }
        });

    }

    /**
     * Shows this dialog
     */
    @Override
    public void showDialog(){
        this.nameTextField.setText(command.getName());
        this.descriptionTextField.setText(command.getDescription());
        this.detailsTextArea.setText(command.getDetails());
        this.optionsListView.getItems().setAll(command.getOptions());
        optionsListView.setPrefHeight(optionsListView.getItems().size() * 25);
        this.formatsListView.getItems().setAll(command.getFormats());
        formatsListView.setPrefHeight(formatsListView.getItems().size() * 25);
        this.examplesListVIew.getItems().setAll(command.getExamples());
        examplesListVIew.setPrefHeight(examplesListVIew.getItems().size() * 25);
        super.showDialog();
    }
    //endregion
}
