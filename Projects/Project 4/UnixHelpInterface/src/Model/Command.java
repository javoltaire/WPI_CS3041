package Model;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.*;
import java.util.UUID;

/**
 * @Author Jules Voltaire on 3/31/2016.
 * This class represent a Command and what the command does.
 * In addition, the command has is part of category
 * For example, ls
 * code wise it is represented as
 * Command ls = new Command("ls", "list	directory contents", fileAndFolderCat);
 */
@XmlType(propOrder = { "name", "description", "details", "options", "formats", "examples", "sourceLink"})
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
    private ListProperty<String> formats = new SimpleListProperty<>(this, "formats", FXCollections.observableArrayList());

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
     * Boolean indicating whether the command was recently used
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

    private Command(){}
    //endregion

    //region Getters and Setters

    //region Name
    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    @XmlElement (name = "Command_Name")
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

    @XmlElement (name = "Command_Description")
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

    @XmlElement (name = "Command_Details")
    public void setDetails(String details) {
        this.details.set(details);
    }
    //endregion

    //region Options
    public ListProperty<Item> optionsProperty() {
        return options;
    }

    public ObservableList<Item> getOptions() {
        return options.get();
    }
    @XmlElementWrapper(name = "Command_Options")
    @XmlElement (name = "Command_Option")
    public void setOptions(ObservableList<Item> options) {
        this.options.set(options);
    }
    //endregion

    //region Formats
    public ListProperty<String> formatsProperty() {
        return formats;
    }

    public ObservableList<String> getFormats() {
        return formats.get();
    }
    @XmlElementWrapper(name = "Command_Formats")
    @XmlElement (name = "Command_Format")
    public void setFormats(ObservableList<String> formats) {
        this.formats.set(formats);
    }
    //endregion

    //region Examples
    public ListProperty<Item> examplesProperty() {
        return examples;
    }

    public ObservableList<Item> getExamples() {
        return examples.get();
    }

    @XmlElementWrapper(name = "Command_Examples")
    @XmlElement (name = "Command_Example")
    public void setExamples(ObservableList<Item> examples) {
        this.examples.set(examples);
    }
    //endregion

    //region Source Link
    public StringProperty sourceLinkProperty() {
        return sourceLink;
    }

    public String getSourceLink() {
        return sourceLink.get();
    }

    @XmlElement (name = "Command_Source_Link")
    public void setSourceLink(String sourceLink) {
        this.sourceLink.set(sourceLink);
    }
    //endregion

    //region Parent category
    public Category getParentCategory() {
        return parentCategory;
    }

    @XmlTransient
    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
    //endregion

    //region RecentlyUsed
    public boolean isRecentlyUsed() {
        return isRecentlyUsed;
    }

    @XmlAttribute(name = "isRecentlyUsed")
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
        for(String format : this.formats){
            result.append("\t\t\t" + format + "\n");
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
