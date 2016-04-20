package Controller.CustomControls.ListCells;

import Model.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by jules on 4/13/2016.
 */
public class ItemListCell extends ListCell<Item> {
    //region FXML variables
    @FXML private AnchorPane root;
    @FXML private Label nameLabel;
    @FXML private Label descriptionLabel;
    //endregion

    //region Constructor
    public ItemListCell(){
        loadFXMLFile();
    }
    //endregion


    private void loadFXMLFile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../View/CustomControls/Cells/ItemListCell.fxml"));
        loader.setController(this);
        try{
            loader.load();
        }
        catch(IOException excpt){
            throw new RuntimeException(excpt);
        }
    }

    @Override
    public void updateItem(Item item, boolean empty)
    {
        super.updateItem(item,empty);
        if(empty || item == null){
            nameLabel.setText(null);
            descriptionLabel.setText(null);
            setGraphic(null);
        }
        else {
            nameLabel.setText(item.getName() + ":");
            descriptionLabel.setText(item.getDescription());
            setGraphic(root);
        }
    }
}
