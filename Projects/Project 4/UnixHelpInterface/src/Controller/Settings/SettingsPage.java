package Controller.Settings;

import Model.CategoryManager;
import javafx.beans.property.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;

/**
 * Created by jules on 4/5/2016.
 */
public class SettingsPage extends VBox{
    //region FXML Controls Variables
    @FXML private Button settingsBackButton;
    @FXML private Label settingsHeaderLabel;
    @FXML private VBox settingsMenuContentPane;
    @FXML private VBox personalizeContentPane;
    @FXML private VBox helpContentPage;
    @FXML private VBox aboutContentPage;
    //endregion

    //region Variables and Properties
    /**
     * Boolean property indicating whether the the back button in the settings page should be visible/clickable
     */
    private BooleanProperty canGoBack = new SimpleBooleanProperty(this, "canGoBack", false);
    //endregion

    //region Constructors
    /**
     * Initializes a new instance of this class
     */
    public SettingsPage(){
        loadFXMLFile();
        addListeners();
    }
    //endregion

    //region FXML Methods
    /**
     * Back Button Click
     * shows the settings menu pane
     */
    @FXML private void onSettingsBackButtonClick(){
        if(personalizeContentPane.isVisible()){                 // If the personalize pane is visible
            personalizeContentPane.setVisible(false);               // Then hide it
        }
        else if(helpContentPage.isVisible()){                   // If the help pane is visible
            helpContentPage.setVisible(false);                      // Then hide it
        }
        else if(aboutContentPage.isVisible()){                  // If the about pane is visible
            aboutContentPage.setVisible(false);                     // Then hide it
        }
        settingsMenuContentPane.setVisible(true);               // Show the settings menu pane
        settingsHeaderLabel.setText("Settings");                // Change the header of root settings pane
        canGoBack.setValue(false);                              // Show the back button
    }

    /**
     * Personalize Button Click
     * This method shows the personalization settings page/pane
     */
    @FXML private void onPersonalizationButtonClick(){
        goToSettingsPage(personalizeContentPane, "Personalization");
    }

    /**
     * Help Button Click
     * This method shows the help settings page/pane
     */
    @FXML private void onHelpButtonClick(){
        goToSettingsPage(helpContentPage, "Help");
    }

    /**
     * About Button Click
     * This method shows the about settings page/pane
     */
    @FXML private void onAboutButtonClick(){
        goToSettingsPage(aboutContentPage, "About");
    }

    @FXML private void onClearRecentButtonClick(){
        CategoryManager.getInstance().clearRecent();
    }
    //endregion

    //region Helper Methods
    /**
     * Loads the view portion(fxml file) for this class
     */
    private void loadFXMLFile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/Settings/SettingsPage.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try{
            loader.load();
        }
        catch(IOException excpt){
            throw new RuntimeException(excpt);
        }
    }

    /**
     * Adds all the necessary listeners to properties
     */
    private void addListeners(){
        canGoBack.addListener((v, oldValue, newValue) ->{
            if(newValue){
                settingsBackButton.setPrefWidth(50.0);
            }
            else{
                settingsBackButton.setPrefWidth(0.0);
            }
        });
    }

    /**
     * Shows the proper settings content pane once the user picks one from the menu
     * @param node The pane/page to be shown
     * @param headerName The new header for the settings pane
     */
    private void goToSettingsPage(Node node, String headerName){
        settingsMenuContentPane.setVisible(false);              // Hide the menu page
        node.setVisible(true);                                  // Show the about page
        settingsHeaderLabel.setText(headerName);                // Change the header of the settings pane
        canGoBack.setValue(true);                               // show back button
    }
    //endregion

}
