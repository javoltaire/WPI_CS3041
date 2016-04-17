package Controller.CustomControls.ListCells;

import Model.Category;
import Model.Command;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by jules on 4/11/2016.
 */
public class SimpleCommandListCell extends ListCell<Command> {
    //region FXML variables
    @FXML private AnchorPane root;
    @FXML private Label label;
    //endregion

    //region Constructor
    public SimpleCommandListCell(){
        loadFXMLFile();
    }
    //endregion


    private void loadFXMLFile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../View/CustomControls/SimpleCommandListCell.fxml"));
        loader.setController(this);
        try{
            loader.load();
        }
        catch(IOException excpt){
            throw new RuntimeException(excpt);
        }
    }

    @Override
    public void updateItem(Command command, boolean empty)
    {
        super.updateItem(command,empty);
        if(empty || command == null)
        {
            label.setText(null);
            setGraphic(null);
        }
        else{
            label.setText(command.getName());
            setGraphic(root);
        }
    }
}
