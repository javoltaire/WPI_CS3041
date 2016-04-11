package Controller.Content;

import Model.Command;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by jules on 4/11/2016.
 */
public class CommandsView extends AnchorPane {
    //region FXML Controls
    @FXML private ListView<Command> commandsListView;
    //endregion

    //region Constructors
    public CommandsView(){
        loadFXMLFile();
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

    public void setCommandsList(ObservableList<Command> commands){
        commandsListView.setItems(commands);
    }
    //endregion
}
