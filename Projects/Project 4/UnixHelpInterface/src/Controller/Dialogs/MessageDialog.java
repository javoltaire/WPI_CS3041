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

    /**
     * Initializes a new instance of this class
     */
    public MessageDialog(){
        super();
        loadFXMLFile();
    }

    /**
     * Initializes a new instance of this class with the given arguments
     * @param title the title of the dialog
     * @param message the message of the dialod
     */
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

    /**
     * Sets the Title of the dialog
     * @param title the new title of the dialog
     */
    @Override
    public void setTitle(String title){
        titleLabel.setText(title);
    }

    /**
     * Sets the message of the dialog
     * @param message the new message of the dialog
     */
    public void setMessage(String message){
        messageLabel.setText(message);
    }

    /**
     * Sets the text of the primary button
     * @param buttonText the new text for the primary button
     */
    public void setButtonText(String buttonText){
        okayButton.setText(buttonText);
    }
    //endregion
}
