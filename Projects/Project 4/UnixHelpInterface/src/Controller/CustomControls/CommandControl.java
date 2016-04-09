package Controller.CustomControls;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Created by jules on 4/7/2016.
 */
public class CommandControl extends GridPane {
    //region FXML Variables
    @FXML private Button deleteButton;
    //endregion

    //region Constructors
    public CommandControl(){
        loadFXMLFile();
        setEventHandlers();
    }
    //endregion

    //region FXML Methods
    @FXML private void onMouseEntered(){
        this.deleteButton.setVisible(true);
    }

    @FXML private void onMouseExited(){
        this.deleteButton.setVisible(false);
    }
    //endregion

    //region Helper Methods
    private void loadFXMLFile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/CustomControls/CommandControl.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try{
            loader.load();
        }
        catch(IOException excpt){
            throw new RuntimeException(excpt);
        }
    }

    private void setEventHandlers(){
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                deleteButton.setVisible(true);
            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                deleteButton.setVisible(false);
            }
        });
    }
    //endregion


}
