package Controller.Dialogs;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Created by jules on 4/11/2016.
 */
public class DetailedCommandDialog extends Dialog {
    //region Constructors
    public DetailedCommandDialog(){
        loadFXMLFile();
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
    //endregion
}
