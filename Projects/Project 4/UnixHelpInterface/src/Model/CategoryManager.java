package Model;

import Controller.Dialogs.MessageDialog;
import Model.Enums.CATEGORIES;
import com.sun.xml.internal.bind.v2.TODO;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.*;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author Jules Voltaire on 4/13/2016.
 * This class represents a manager for all the categories
 * This class is a singleton class to make sure that there is only one category manager class to be used
 * throughout the program
 */
public class CategoryManager {
    //region Variables and Properties
    /**
     * The instance of this class to be used
     */
    private static CategoryManager categoryManager = new CategoryManager();

    /**
     * Object that contains the data read from xml file
     */
    private CategoriesListWrapper categoriesListWrapper;

    /**
     * The category that will contain all the recently used/viewed commands
     */
    private  Category recent = new Category(CATEGORIES.RECENT.toString());

    /**
     * File object that contains the location of the xml file to be read from or saved to
     */
    private File XMLfile = new File("src/Resources/Data/CommandsList.xml");

    /**
     * Hashmap to hold all the command objects
     * This will make it easy to search through the commands.
     */
    private HashMap<String, Command> commandHashMap = new HashMap<>();
    //endregion

    //region Constructors

    /**
     * Initializes a new instance of this class.
     */
    private CategoryManager(){
        categoriesListWrapper = readXMLFile();
        populateHashTable();
    }
    //endregion

    //region Getters and Setters
    /**
     * Gets the instance of this class to be used
     * @return
     */
    public static CategoryManager getInstance(){
        return categoryManager;
    }

    /**
     * gets the list of categories
     * @return the list categories
     */
    public ObservableList<Category> getCategories() {
        return categoriesListWrapper.getCategories();
    }
    //endregion

    //region Methods

    /**
     * Saves all the objects to an xml file
     */
    public void saveData(){
        try {
            // Removing the recent category so that commands are not saved twice
            categoriesListWrapper.getCategories().remove(recent);

            // Creating the Marshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(CategoriesListWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // Formatting the output, Indentation and such
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Writing to XML file
            jaxbMarshaller.marshal(categoriesListWrapper, XMLfile);

            //Adding the recent category back
            categoriesListWrapper.getCategories().add(0,recent);

        } catch (JAXBException e) {
            MessageDialog dialog = new MessageDialog("Error", "Error has occurred while trying to save XML File\n" + e.getMessage());
            dialog.showDialog();
        }
    }

    /**
     * Reads the xml file
     * @return A categoryListWrapper object that contains all the categories and commands and etc
     */
    private CategoriesListWrapper readXMLFile(){
        try {
            // Creating the Marshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(CategoriesListWrapper.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Creating a CategoriesListWrapper object from the file.
            return (CategoriesListWrapper) jaxbUnmarshaller.unmarshal(XMLfile);
        } catch (JAXBException e) {
            MessageDialog dialog = new MessageDialog("Error", "Error has occurred while trying to read XML File\n" + e.getMessage());
            dialog.showDialog();
        }
        return new CategoriesListWrapper();
    }

    /**
     * Adds all the commands in all the categories to the hashtable
     */
    private void populateHashTable(){
        for(Category category : this.categoriesListWrapper.getCategories()){
            for(Command command : category.getCommands()){
                command.setParentCategory(category);
                commandHashMap.put(command.getName(), command);
                if(command.isRecentlyUsed()){
                    recent.getCommands().add(command);
                }
            }
        }
        getCategories().add(0,recent);
    }

    /**
     * Checks if a key is in the hashtable
     * @param key the key to check for
     * @return true if the hashtable contains the key, false otherwise
     */
    public boolean containsCommandKey(String key){
        return commandHashMap.containsKey(key);
    }

    /**
     * returns a command from the hashmap
     * @param key
     * @return
     */
    public Command getCommand(String key){
        return commandHashMap.get(key);
    }

    /**
     * Finds the custom category
     * @return the custom category object
     */
    public Category getCustomCategory(){
        Category customCategory = getCategories().get(getCategories().size() - 1);
        if(!customCategory.getName().equals(CATEGORIES.CUSTOM.toString())){
            for(Category category : getCategories()){
                if(category.getName().equals(CATEGORIES.CUSTOM.toString())){
                    return category;
                }
                else{
                    return null;
                }
            }
        }
        return customCategory;
    }

    /**
     * Finds all the commands that starts with the given string
     * @param sim string to search for
     * @return A list containing all the commands that starts with the argument string
     */
    public ObservableList<Command> getSimilar(String sim){
        ObservableList<Command> result = FXCollections.observableArrayList();
        Iterator<Map.Entry<String, Command>> iterator = commandHashMap.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String, Command> pair = (Map.Entry) iterator.next();
            if(pair.getKey().startsWith(sim)){
                result.add(pair.getValue());
            }
        }

        return result;
    }

    /**
     * Adds a command to the recent category
     * @param command the command to be added
     */
    public void addToRecent(Command command){
        if(recent.getCommands().contains(command))
            recent.getCommands().remove(command);
        command.setRecentlyUsed(true);
        recent.getCommands().add(0, command);
    }

    /**
     * Adds the command to its parent category list and to the hash map
     * @param command the command to be added
     */
    public void addCommand(Command command){
        command.getParentCategory().getCommands().add(command);
        commandHashMap.put(command.getName(), command);
    }

    /**
     * Clears the list of commands that are in the recent category
     */
    public void clearRecent(){
        int numRecentItem = recent.getCommands().size();
        for(int i = 0; i < numRecentItem; i++){
            Command firstCommand = recent.getCommands().get(0);
            firstCommand.setRecentlyUsed(false);
            recent.getCommands().remove(firstCommand);
        }
    }
    //endregion
}
