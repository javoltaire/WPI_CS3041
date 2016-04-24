package Controller.CustomControls.TextFields;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by jules on 4/20/2016.
 */
public class ErrorTextField extends VBox {
    //region FXML fields
    @FXML private TextField textField;
    @FXML private Label errorLabel;
    //endregion

    //region getters and setters
    public StringProperty textProperty() {
        return textField.textProperty();
    }

    public String getText(){
        return textProperty().get();
    }

    public void setText(String text){
        textProperty().set(text);
    }

    public StringProperty promptTextProperty() {
        return textField.promptTextProperty();
    }

    public String getPromptText(){
        return promptTextProperty().get();
    }

    public void setPromptText(String text){
        promptTextProperty().set(text);
    }

    public ObjectProperty<EventHandler<ActionEvent>> onActionProperty(){
        return textField.onActionProperty();
    }

    public EventHandler<ActionEvent> getOnAction(){
        return onActionProperty().get();
    }

    public void setOnAction(EventHandler<ActionEvent> actionEvent){
        onActionProperty().set(actionEvent);
    }
    //endregion

    private void loadFXMLFile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../View/CustomControls/TextFields/ErrorTextField.fxml"));
        loader.setController(this);
        try{
            loader.load();
        }
        catch(IOException excpt){
            throw new RuntimeException(excpt);
        }
    }

    /**
     * Shows the error label by setting the size of the error label to max height (25) and sets the text of the label
     * @param message The error message to be display
     */
    public void showErrorLabel(String message){
        errorLabel.setText(message);
        errorLabel.setPrefHeight(errorLabel.getMaxHeight());

    }

    /**
     * Hides the error label by setting its height to 0 and sets the text to an empty string;
     */
    public void hideErrorLabel(){
        errorLabel.setPrefHeight(errorLabel.getMinHeight());
        errorLabel.setText("");
    }

    /**
     * Clears the content inside the textfield
     */
    public void clear(){
        textField.clear();
    }
}
