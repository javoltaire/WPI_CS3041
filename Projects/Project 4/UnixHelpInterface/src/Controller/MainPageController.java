package Controller;

import Controller.CustomControls.CommandControl;
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
    //region FXML Controls
    @FXML private ToggleButton settingsToggleButton;
    @FXML private AnchorPane mainContentRootPane;
//    @FXML private AnchorPane settingsRootPane;
    //endregion

    //Methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        createSettingsPanes();
//        bindAll();
        //CommandControl commandControl = new CommandControl();
        //mainContentRootPane.getChildren().add(commandControl);
//        addListeneers();
    }

//    private void createSettingsPanes(){
//        try{
//            Parent mainSettingsContentPane = FXMLLoader.load(getClass().getResource("../View/SettingsPages/SettingsPage.fxml"));
//
//            settingsRootPane.getChildren().add(mainSettingsContentPane);
//            AnchorPane.setTopAnchor(mainSettingsContentPane, 0.0);
//            AnchorPane.setRightAnchor(mainSettingsContentPane, 0.0);
//            AnchorPane.setBottomAnchor(mainSettingsContentPane, 0.0);
//            AnchorPane.setLeftAnchor(mainSettingsContentPane, 0.0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * This method just add listeners to all the required controls
     */
//    private void addListeneers(){
//        settingsToggleButton.selectedProperty().addListener((v, oldValue, newValue) ->{
//            settingsRootPane.setVisible(newValue);
//        });
//    }

    private void bindAll(){
//        settingsRootPane.visibleProperty().bind(settingsToggleButton.selectedProperty());
    }





    //endregion
}
