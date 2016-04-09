package Controller;

import Controller.CustomControls.CommandControl;
import Controller.SettingsPages.SettingsPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jules on 3/31/2016.
 */
public class MainPageController implements Initializable {
    //region FXML variables
    @FXML private ToggleButton settingsToggleButton;
    @FXML private SettingsPage settingsPage;
    //endregion

    //Methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeneers();
    }

    /**
     * This method just add listeners to all the required controls
     */
    private void addListeneers(){
        settingsToggleButton.selectedProperty().addListener((v, oldValue, newValue) -> {
            settingsPage.setVisible(newValue);
        });
    }

    private void bindAll(){

    }
    //endregion
}
