package Model;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.*;

import java.util.*;

/**
 * @Author Jules Voltaire on 3/31/2016.
 * This class represent a Command and what the command does.
 * In addition, the command has is part of category
 * For example, ls
 * code wise it is represented as
 * Command ls = new Command("ls", "list	directory contents", fileAndFolderCat);
 */
public class Command {

    //region Variables
    /**
     * String property to hold the name.
     */
    private StringProperty name = new SimpleStringProperty(this, "name", "");

    /**
     * String property to hold the description.
     * A short description of what the command does
     */
    private StringProperty description = new SimpleStringProperty(this, "description", "");

    /**
     * String property to hold the details.
     * A more detailed description
     */
    private StringProperty details = new SimpleStringProperty(this, "details", "");

    /**
     * List to hold all the Options of the command
     */
    private ListProperty<Item> options = new SimpleListProperty<>(this, "options", FXCollections.observableArrayList());

    /**
     * List to hold the different formats of the command
     */
    private ListProperty<Item> formats = new SimpleListProperty<>(this, "formats", FXCollections.observableArrayList());

    /**
     * List property to hold the different examples.
     * An example of how the command is used
     */
    private ListProperty<Item> examples = new SimpleListProperty(this, "examples", FXCollections.observableArrayList());

    /**
     * String property to hold the sourceLink.
     */
    private StringProperty sourceLink = new SimpleStringProperty(this, "sourceLink", "");

    /**
     * The parent category of this command
     */
    private Category parentCategory;

    /**
     * Boolean indicating wheter the command was recently used
     */
    private boolean isRecentlyUsed = false;
    //endregion

    //region Constructors
    /**
     * Initializes a new instance of this class with the given values
     * @param name The name of the Command
     * @param description A short description of what the command does
     * @param parentCategory The parent category of this command
     */
    public Command(String name, String description, Category parentCategory){
        this.name.setValue(name);
        this.description.setValue(description);
        this.parentCategory = parentCategory;
    }

    /**
     * Initializes a new instance of this class with the given values
     * @param name The name of the Command
     * @param description A short description of what the command does
     * @param details A more detailed description
     * @param sourceLink A link to read more about the command
     * @param isRecentlyUsed Boolean indicating wheter this command was recently used
     */
    public Command(String name, String description, Category parentCategory, String details, String sourceLink, boolean isRecentlyUsed) {
        this.name.setValue(name);
        this.description.setValue(description);
        this.parentCategory = parentCategory;

        this.details.setValue(details);
        this.sourceLink.setValue(sourceLink);
        this.isRecentlyUsed = isRecentlyUsed;
    }
    //endregion

    //region Getters and Setters
    //region Name
    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    //endregion

    //region Description
    public StringProperty descriptionProperty() {
        return description;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
    //endregion

    //region Details
    public StringProperty detailsProperty() {
        return details;
    }

    public String getDetails() {
        return details.get();
    }

    public void setDetails(String details) {
        this.details.set(details);
    }
    //endregion

    //region Options
    public ListProperty<Item> optionsProperty() {
        return options;
    }
    //endregion

    //region Formats
    public ListProperty<Item> formatsProperty() {
        return formats;
    }
    //endregion

    //region Examples
    public ListProperty<Item> examplesProperty() {
        return examples;
    }
    //endregion

    //region Source Link
    public StringProperty sourceLinkProperty() {
        return sourceLink;
    }

    public String getSourceLink() {
        return sourceLink.get();
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink.set(sourceLink);
    }
    //endregion

    //region Parent category
    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
    //endregion

    //region RecentlyUsed
    public boolean isRecentlyUsed() {
        return isRecentlyUsed;
    }

    public void setRecentlyUsed(boolean recentlyUsed) {
        isRecentlyUsed = recentlyUsed;
    }
    //endregion
    //endregion

    //region Methods

    /**
     * Creates a formatted String with the data in this class
     * @return The formatted string to be printed out
     */
    public String toPrintableString(){
        StringBuilder result = new StringBuilder();

        result.append("\t\tCommand: " + this.getName() + "\n");
        result.append("\t\tDescription: " + this.getDescription() + "\n");
        result.append("\t\tDetails: " + this.getDetails() + "\n");
        result.append("\t\tOptions: " + "\n");
        for(Item option : this.options){
            result.append("\t\t\t" + option.toPrintableString());
        }
        result.append("\t\tFormats: " + "\n");
        for(Item format : this.formats){
            result.append("\t\t\t" + format.toPrintableString());
        }
        result.append("\t\tExamples: " + "\n");
        for(Item example : this.examples){
            result.append("\t\t\t" + example.toPrintableString());
        }
        result.append("\t\tSource Link: " + this.getSourceLink() + "\n");
        result.append("\t\tRecently Used?: " + this.isRecentlyUsed + "\n");

        return result.toString();

    }
    //endregion
















}
