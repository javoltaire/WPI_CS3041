package Controller.CustomControls.ListCells;

import Model.Command;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by jules on 4/11/2016.
 */
public class SimpleCommandListCell extends ListCell<Command> {
    //region Constructor
    public SimpleCommandListCell(){}
    //endregion

    @Override
    public void updateItem(Command command, boolean empty)
    {
        super.updateItem(command,empty);
        if(empty || command == null)
        {
            setText(null);
        }
        else{
            setText(command.getName());
        }
        setAlignment(Pos.CENTER);
    }
}
