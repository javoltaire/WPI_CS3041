package Model;

import javafx.beans.property.*;

import java.util.*;

/**
 * @Author Jules Voltaire on 3/31/2016.
 * This class represent a Command, e.g ls or mkdir
 */
public class Command {

    //region Variables
    /**
     * Unique identifier of a command
     */
    private UUID id = UUID.randomUUID();

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
     * The category that the command is part of
     */
    private Category parentCategory;

    /**
     * String property to hold the details.
     * A more detailed description
     */
    private StringProperty details = new SimpleStringProperty(this, "details", "");

    /**
     * String property to hold the examples.
     * An example of how the command is used
     */
    private StringProperty example = new SimpleStringProperty(this, "example", "");

    /**
     * A list of other relevant commands related
     */
    private List<Command> seeAlso = new ArrayList<>();

    /**
     * String property to hold the sourceLink.
     */
    private StringProperty sourceLink = new SimpleStringProperty(this, "sourceLink", "");
    //endregion

    //region Constructors
    /**
     * Initializes a new instance of this class with the given values
     * @param name The name of the Command
     * @param description A short description of what the command does
     * @param parentCategory The category that this command will be part of
     */
    public Command(String name, String description, Category parentCategory){
        this.name.setValue(name);
        this.description.setValue(description);
        this.parentCategory = parentCategory;
    }

    /**
     * Initializes a new instance of this class with the given values
     * @param id The id of the command
     * @param name The name of the Command
     * @param description A short description of what the command does
     * @param parentCategory The category that the command will be part of
     * @param details A more detailed description
     * @param example An example of how the command is used
     * @param sourceLink A link to read more about the command
     */
    public Command(String id, String name, String description, Category parentCategory, String details, String example, String sourceLink) {
        this.id = UUID.fromString(id);
        this.name.setValue(name);
        this.description.setValue(description);
        this.parentCategory = parentCategory;
        this.details.setValue(details);
        this.example.setValue(example);
        this.sourceLink.setValue(sourceLink);
    }
    //endregion

    //region Getters

    /**
     * Gets the value of the name property
     * @return The name of the command
     */
    public String getName() {
        return name.get();
    }

    /**
     * Gets the id of the command
     * @return The id of the command
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the value of the short description Property of the command
     * @return The short description of the command
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Gets the value of the detailed description Property of the command
     * @return The more detailed description of the command
     */
    public String getDetails() {
        return details.get();
    }

    /**
     * Gets the value of the example Property of the command
     * @return The example of the command
     */
    public String getExample() {
        return example.get();
    }

    /**
     * Gets the list of see also commands relevant to this command
     * @return The list of seeAlso relevant commands
     */
    public List<Command> getSeeAlso() {
        return seeAlso;
    }

    /**
     * Gets the value of source link Property of the command
     * @return The source link where the user can read more about the command
     */
    public String getSourceLink() {
        return sourceLink.get();
    }
    //endregion











    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }



    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }



    public StringProperty detailsProperty() {
        return details;
    }

    public void setDetails(String details) {
        this.details.set(details);
    }



    public StringProperty exampleProperty() {
        return example;
    }

    public void setExample(String example) {
        this.example.set(example);
    }



    public StringProperty sourceLinkProperty() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink.set(sourceLink);
    }

}
