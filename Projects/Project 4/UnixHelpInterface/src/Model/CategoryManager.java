package Model;

import Model.Enums.CATEGORIES;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.ObservableList;

import javax.xml.bind.*;
import java.io.File;
import java.util.HashMap;

/**
 * Created by jules on 4/13/2016.
 */
public class CategoryManager {
    //region Variables and Properties
    private static CategoryManager categoryManager = new CategoryManager();

    private CategoriesListWrapper categoriesListWrapper;

    private  Category recent = new Category(CATEGORIES.RECENT.toString());

    private File XMLfile = new File("src/Resources/Data/CommandsList.xml");

    private HashMap<String, Command> commandHashMap = new HashMap<>();
    //endregion

    //region Constructors
    private CategoryManager(){
        categoriesListWrapper = readXMLFile();
        populateHashTable();
    }
    //endregion

    //region Getters and Setters
    public static CategoryManager getInstance(){
        return categoryManager;
    }

    public ObservableList<Category> getCategories() {
        return categoriesListWrapper.getCategories();
    }

    //endregion

    //region Methods
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
            e.printStackTrace();
        }
    }

    private CategoriesListWrapper readXMLFile(){
        try {
            // Creating the Marshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(CategoriesListWrapper.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Creatinga CategoriesListWrapper object from the file.
            return (CategoriesListWrapper) jaxbUnmarshaller.unmarshal(XMLfile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public Command getCommandByName(String commandName){
        if(commandHashMap.containsKey(commandName)){
            return commandHashMap.get(commandName);
        }
        return null;
    }

    public Category getCustom(){
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
    //endregion
}
