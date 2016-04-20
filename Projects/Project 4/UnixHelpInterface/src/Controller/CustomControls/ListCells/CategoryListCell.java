package Controller.CustomControls.ListCells;

import Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by jules on 4/9/2016.
 */
public class CategoryListCell extends ListCell<Category>{
    //region Constructor
    public CategoryListCell(){}
    //endregion

    @Override
    public void updateItem(Category category, boolean empty)
    {
        super.updateItem(category,empty);
        if(empty || category == null){
            setText(null);
        }
        else{
            setText(category.getName());
        }
        setAlignment(Pos.CENTER);
    }
}
