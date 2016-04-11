package Controller.Dialogs;

import Controller.ControllerSingleton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by jules on 4/10/2016.
 */
public abstract class Dialog extends VBox{
    //region FXML Controls
    @FXML private Label titleLabel;
    //endregion

    //region Getters and Setters
    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    public String getTitle() {
        return titleProperty().get();
    }

    public void setTitle(String title) {
        titleProperty().set(title);
    }

    //endregion

    //FXML Methods
    @FXML public void onCancelButtonClick(){
        exitDialog();
    }
    //endregion

    //region Methods
    public void showDialog(){
        ControllerSingleton.getInstace().getMainPage().showDialog(this);
    }

    public void exitDialog(){
        ControllerSingleton.getInstace().getMainPage().exitDialog(this);
    }
    //endregion
}
