package Controller.CustomControls.ListCells;
import Controller.ControllerSingleton;
import Controller.Dialogs.DetailedCommandDialog;
import Model.Command;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Created by jules on 4/7/2016.
 */
public class CommandListCell extends ListCell<Command> {
    //region FXML Variables
    @FXML private GridPane root;
    @FXML private Label nameLabel;
    @FXML private Label descriptionLabel;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    //endregion

    //region Variables
    private Command command = null;
    private boolean canShowEditAndDeleteButton = false;
    //endregion

    //region Constructors
    public CommandListCell(){
        loadFXMLFile();
        setEventHandlers();
    }
    //endregion

    //region FXML Methods
    @FXML private void onMouseEntered(){
        if(canShowEditAndDeleteButton){
            this.editButton.setVisible(true);
            this.deleteButton.setVisible(true);
        }
    }

    @FXML private void onMouseExited(){
        if(canShowEditAndDeleteButton){
            this.editButton.setVisible(false);
            this.deleteButton.setVisible(false);
        }
    }

    @FXML private void onMouseClicked(){
        if(command != null)
            ControllerSingleton.getInstace().getMainPage().navigateToCommandsView(command);
    }

    @FXML private void onEditCommandButtonClick(){
        if(command != null){
            DetailedCommandDialog detailedCommandDialog = new DetailedCommandDialog(command, false);
            detailedCommandDialog.setTitle("Edit Command");
            detailedCommandDialog.showDialog();
        }
    }
    //endregion

    //region Helper Methods
    private void loadFXMLFile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../View/CustomControls/Cells/CommandListCell.fxml"));
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
        if(empty || command == null){
            this.command = null;
            nameLabel.setText(null);
            descriptionLabel.setText(null);
            setGraphic(null);
        }
        else {
            this.command = command;
            nameLabel.setText(command.getName());
            descriptionLabel.setText(command.getDescription());
            setGraphic(root);
        }
    }

    private void setEventHandlers() {
        this.setOnMouseClicked(e -> onMouseClicked());

        this.setOnMouseEntered(e -> onMouseEntered());

        this.setOnMouseExited(e -> onMouseExited());
    }

    public void setCanShowEditAndDeleteButton(boolean value){
        this.canShowEditAndDeleteButton = value;
    }
    //endregion


}
