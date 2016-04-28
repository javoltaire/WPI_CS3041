import Controller.ControllerSingleton;
import Model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * @Author Jules Voltaire on 3/31/2016.
 * Main class
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        printData();
        primaryStage.setOnCloseRequest(e -> CategoryManager.getInstance().saveData());
        ControllerSingleton.getInstace().getMainPage().navigateToCategoriesView(CategoryManager.getInstance().getCategories());
        primaryStage.setTitle("Unix Command");
        primaryStage.setScene(new Scene(ControllerSingleton.getInstace().getMainPage(), 1920, 1080));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Prints all the data for all the categories and commands etc
     */
    private void printData(){
        System.out.println("All Categories");
        for(Category cat : CategoryManager.getInstance().getCategories()){
            if(!cat.getName().equals("Recent"))
                System.out.println("\t" + cat.toPrintableString());
        }
        System.out.println("All Categories");
    }

 //region to be Helper Methods

    /**
     * Creates dummy data oobjects to be saved to the xml file.
     */
    public void createDummyData(){
        Category category1 = new Category("Category 1");
        Category category2 = new Category("Category 2");

        Command command1 = new Command("Command 1", "Description of Command 1", category1, "This is a more detailed description of what Command 1 does. It does this and that", "https://link/to/command1_page", false);
        Command command2 = new Command("Command 2", "Description of Command 2", category1, "This is a more detailed description of what Command 2 does. It does this and that", "https://link/to/command2_page", true);

        Item item1 = new Item("Item 1", "Desctription of Item 1");
        Item item2 = new Item("Item 2", "Desctription of Item 2");
        Item item3 = new Item("Item 2", "Desctription of Item 1");
        Item item4 = new Item("Item 2", "Desctription of Item 1");
        Item item5 = new Item("Item 2", "Desctription of Item 1");
        Item item6 = new Item("Item 2", "Desctription of Item 1");
        Item item7 = new Item("Item 2", "Desctription of Item 1");
        Item item8 = new Item("Item 2", "Desctription of Item 1");
        Item item9 = new Item("Item 2", "Desctription of Item 1");
        Item item10 = new Item("Item 2", "Desctription of Item 1");

        command1.optionsProperty().addAll(item1, item2, item10);
        command1.examplesProperty().addAll(item3, item4);
        command1.formatsProperty().addAll("Format 1", "Format 2", "Format 3");

        command2.optionsProperty().addAll(item5, item6);
        command2.examplesProperty().addAll(item7, item8, item9);
        command2.formatsProperty().addAll("Format 4", "Format 5", "Format 6");

        category1.commandsProperty().addAll(command1, command2);

        CategoryManager.getInstance().getCategories().addAll(category1, category2);
    }
    //endregion
}