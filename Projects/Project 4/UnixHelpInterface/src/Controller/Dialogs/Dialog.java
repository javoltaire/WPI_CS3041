package Controller.Dialogs;

import Controller.ControllerSingleton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * Created by jules on 4/10/2016.
 */
public abstract class Dialog extends VBox{
    //region Properties
    public StringProperty title = new SimpleStringProperty(this, "title", "Title");
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
