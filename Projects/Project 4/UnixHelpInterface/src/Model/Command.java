package Model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    private ObservableList<Option> options = FXCollections.observableArrayList();

    /**
     * String property to hold the format of the command
     */
    private StringProperty format = new SimpleStringProperty(this, "format", "");

    /**
     * String property to hold the examples.
     * An example of how the command is used
     */
    private StringProperty example = new SimpleStringProperty(this, "example", "");

    /**
     * A list of other relevant commands related
     */
    private ObservableList<Command> seeAlso = FXCollections.observableArrayList();

    /**
     * String property to hold the sourceLink.
     */
    private StringProperty sourceLink = new SimpleStringProperty(this, "sourceLink", "");

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
     */
    public Command(String name, String description){
        this.name.setValue(name);
        this.description.setValue(description);
    }

    /**
     * Initializes a new instance of this class with the given values
     * @param name The name of the Command
     * @param description A short description of what the command does
     * @param details A more detailed description
     * @param format The format of the command
     * @param example An example of how the command is used
     * @param sourceLink A link to read more about the command
     * @param isRecentlyUsed Boolean indicating wheter this command was recently used
     */
    public Command(String name, String description, String details, String format, String example, String sourceLink, boolean isRecentlyUsed) {
        this.name.setValue(name);
        this.description.setValue(description);
        this.details.setValue(details);
        this.format.setValue(format);
        this.example.setValue(example);
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
    public ObservableList<Option> getOptions() {
        return options;
    }
    //endregion

    //region Format
    public StringProperty formatProperty() {
        return format;
    }

    public String getFormat() {
        return format.get();
    }

    public void setFormat(String format) {
        this.format.set(format);
    }
    //endregion

    //region Example
    public StringProperty exampleProperty() {
        return example;
    }

    public String getExample() {
        return example.get();
    }

    public void setExample(String example) {
        this.example.set(example);
    }
    //endregion

    //region See also
    public ObservableList<Command> getSeeAlso() {
        return seeAlso;
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
        for(Option option : this.options){
            result.append("\t\t\t" + option.toPrintableString());
        }
        result.append("\t\tFormat: " + this.getFormat() + "\n");
        result.append("\t\tExamples: " + this.getExample() + "\n");
        for(Command command : this.seeAlso){
            result.append("\t\tSee Also: " + command.getName() + "\n");
        }
        result.append("\t\tSource Link: " + this.getSourceLink() + "\n");
        result.append("\t\tRecently Used?: " + this.isRecentlyUsed + "\n");

        return result.toString();

    }
    //endregion
















}
