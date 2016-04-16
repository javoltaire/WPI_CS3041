package Controller.Dialogs;

import Model.CategoryManager;
import Model.Command;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by jules on 4/11/2016.
 */
public class DetailedCommandDialog extends Dialog {
    @FXML private TextField nameTextField;

    //region variable
    private Command command;
    //endregion

    //region Constructors
    public DetailedCommandDialog(Command command){
        this.command = command;
        loadFXMLFile();
    }
    //endregion

    //FXML Methods
    @FXML public void onCancelButtonClick(){
        exitDialog();
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

    @Override
    public void showDialog(){
        this.nameTextField.setText(command.getName());
        super.showDialog();
    }
    //endregion
}
