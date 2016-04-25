package Controller.Content;
import Controller.CustomControls.ListCells.CommandListCell;
import Model.Command;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by jules on 4/15/2016.
 */
public class SearchResultView extends StackPane {
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

    //region FXML Methods
    @FXML private void onSearchOnlineClicked(){
        if(searchCommand != null){
            try {
                Desktop desktop = Desktop.getDesktop();
                String toSearch = "https://www.google.com/search?q=" + searchCommand + "+" + "unix+command";
                desktop.browse(new URI(toSearch));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
    //endregion

    //region Getters and setters
    public String getTitle(){
        return "Result";
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

    public void showResult(ObservableList<Command> resultCommand){
        this.resultListView.setItems(resultCommand);
        customizeLisviews();
        this.resultListView.setVisible(true);
        this.noResultPane.setVisible(false);
    }

    public void showNoResultError(String searchCommand){
        this. searchCommand = searchCommand;
        this.errorLabel.setText("Sorry, \"" + searchCommand + "\" command was not found.");
        this.resultListView.setVisible(false);
        this.noResultPane.setVisible(true);
    }

    private void customizeLisviews() {
        resultListView.setCellFactory(new Callback<ListView<Command>, ListCell<Command>>() {
            @Override
            public ListCell<Command> call(ListView<Command> param) {
                return new CommandListCell();
            }
        });
    }
    //endregion
}
