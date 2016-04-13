package Model;

import Model.Enums.CATEGORIES;

import javax.xml.bind.*;
import java.io.File;

/**
 * Created by jules on 4/13/2016.
 */
public class CategoryManagerSingleton {
    //region Variables and Properties
    private static CategoryManagerSingleton categoryManagerSingleton = new CategoryManagerSingleton();

    private CategoryManager categoryManager;

    private  Category recent = new Category(CATEGORIES.RECENT.getFormalName());

    private File XMLfile = new File("src/Resources/Data/CommandsList.xml");
    //endregion

    //region Constructors
    private CategoryManagerSingleton(){
        categoryManager = readXMLFile();
        addRecentAndSetParent();
    }
    //endregion

    //region Getters and Setters
    public static CategoryManagerSingleton getInstance(){
        return categoryManagerSingleton;
    }

    public CategoryManager getCategoryManager() {
        return categoryManager;
    }

    //endregion

    //region Methods
    public void saveData(){
        try {
            // Removing the recent category so that commands are not saved twice
            categoryManager.getCategories().remove(recent);

            // Creating the Marshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(CategoryManager.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // Formatting the output, Indentation and such
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Writing to XML file
            jaxbMarshaller.marshal(CategoryManagerSingleton.getInstance().getCategoryManager(), XMLfile);

            //Adding the recent category back
            categoryManager.getCategories().add(0,recent);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private CategoryManager readXMLFile(){
        try {
            // Creating the Marshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(CategoryManager.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Creatinga CategoryManager object from the file.
            return (CategoryManager) jaxbUnmarshaller.unmarshal(XMLfile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addRecentAndSetParent(){
        for(Category category : this.categoryManager.getCategories()){
            for(Command command : category.getCommands()){
                command.setParentCategory(category);
                if(command.isRecentlyUsed()){
                    recent.getCommands().add(command);
                }
            }
        }
        categoryManager.getCategories().add(0,recent);
    }
    //endregion
}
