package Controller.CustomControls;

import Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by jules on 4/9/2016.
 */
public class CategoryListCell extends ListCell<Category>{
    //region FXML variables
    @FXML private AnchorPane root;
    @FXML private Label label;
    //endregion

    //region Constructor
    public CategoryListCell(){
        loadFXMLFile();
    }
    //endregion


    private void loadFXMLFile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/CustomControls/CategoryListCell.fxml"));
        loader.setController(this);
        try{
            loader.load();
        }
        catch(IOException excpt){
            throw new RuntimeException(excpt);
        }
    }

    @Override
    public void updateItem(Category category, boolean empty)
    {
        super.updateItem(category,empty);
        if(category != null)
        {
            label.setText(category.getName());
            setGraphic(root);

        }
    }
}
