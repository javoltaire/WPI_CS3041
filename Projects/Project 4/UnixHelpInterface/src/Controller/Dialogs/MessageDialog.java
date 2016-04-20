package Controller.Dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by jules on 4/16/2016.
 */
public class MessageDialog extends Dialog{
    //region FXML variables
    @FXML private Label titleLabel;
    @FXML private Label messageLabel;
    @FXML private Button okayButton;
    //endregion

    //region Contructors
    public MessageDialog(){
        super();
        loadFXMLFile();
    }

    public MessageDialog(String title, String message){
        super();
        loadFXMLFile();
        titleLabel.setText(title);
        messageLabel.setText(message);
    }
    //endregion

    //region FXML Methods
    @FXML private void onOkayButtonClick(){
        super.exitDialog();
    }
    //endregion

    //region Methods
    /**
     * Loads the view portion(fxml file) for this class
     */
    private void loadFXMLFile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/Dialogs/MessageDialog.fxml"));
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
    public void setTitle(String title){
        titleLabel.setText(title);
    }

    public void setMessage(String message){
        messageLabel.setText(message);
    }

    public void setButtonText(String buttonText){
        okayButton.setText(buttonText);
    }
    //endregion
}
