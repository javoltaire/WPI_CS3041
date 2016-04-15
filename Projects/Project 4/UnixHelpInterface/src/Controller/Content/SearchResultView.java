package Controller.Content;

import Controller.CustomControls.ItemListCell;
import Controller.CustomControls.SimpleCommandListCell;
import Model.Command;
import Model.Item;
import com.sun.corba.se.spi.activation.ServerAlreadyRegisteredHelper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;

/**
 * Created by jules on 4/15/2016.
 */
public class SearchResultView extends BorderPane {
    //region FXML variable
    @FXML private ListView<Command> resultListView;
    @FXML private VBox noResultPane;
    @FXML private Label errorLabel;
    @FXML private Hyperlink searchOnlineLabel;
    //endregion

    //region variables
    private String searchCommand;
    //endregion

    //region Constuctors
    public SearchResultView(){
        loadFXMLFile();
    }
    public SearchResultView(String searchCommand){
        this.searchCommand = searchCommand;
        loadFXMLFile();
    }
    //endregion

    //region Methods
    /**
     * Loads the view portion(fxml file) for this class
     */
    private void loadFXMLFile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/Contents/SearchResultView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try{
            loader.load();
        }
        catch(IOException excpt){
            throw new RuntimeException(excpt);
        }
    }

    private void showResult(ObservableList<Command> resultCommand){
        this.resultListView.setItems(resultCommand);
        customizeLisviews();
        this.resultListView.setVisible(true);
        this.noResultPane.setVisible(false);
    }

    private void showNoResultError(String searchCommand){
        this. searchCommand = searchCommand;
        this.errorLabel.setText("Sorry, " + searchCommand + "  was not found.");
    }



    public void show(){

    }
    private void customizeLisviews() {
        resultListView.setCellFactory(new Callback<ListView<Command>, ListCell<Command>>() {
            @Override
            public ListCell<Command> call(ListView<Command> param) {
                return new SimpleCommandListCell();
            }
        });
    }
    //endregion
}
