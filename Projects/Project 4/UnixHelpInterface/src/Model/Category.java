package Model;

import Model.Enums.CATEGORIES;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

/**
 * @Author Jules Voltaire on 3/31/2016.
 * This class represent a Category of commands
 * For example, for files and folders related commands
 * We would create a new category and add commands to the list of commands in the category
 * Code wise
 * Category fileAndFolder = new Category("Files & Folders");
 * fileAndFolder.getCommands().add(new Command("ls", "list	directory contents", fileAndFolder));
 */
public class Category {
    //region Variables
    /**
     * String property to hold the name.
     */
    private StringProperty name = new SimpleStringProperty(this, "name", "");

    /**
     * list property to hold the commands that are part of this category
     */
    private ListProperty<Command> commands = new SimpleListProperty<>(this, "commands", FXCollections.observableArrayList());
    //endregion

    //region Constructors
    /**
     * Initializes a new instance of a category with the given value
     * @param name The name for this new Category
     */
    public Category (String name){
        this.name.setValue(name);
    }
    //endregion

    //region Getters and Setters
    //region Name

    /**
     * Gets the name property
     * @return the name property object
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Gets the name of the category
     * @return The value of the name property
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the value of the name property to a new one
     * @param name the new value for the name
     */
    public void setName(String name) {
        this.name.set(name);
    }

    //endregion

    //region Commands
    /**
     * Gets the list of commands in this category
     * @return The list of commands in this category
     */
    public ListProperty<Command> getCommands() {
        return commands;
    }
    //endregion

    //endregion

    //region Methods

    /**
     * Creates a formatted String with all the data in this class including all the commands
     * @return The formatted String with all data in this class.
     */
    public String toPrintableString(){
        StringBuilder result = new StringBuilder();

        result.append("Category: " + this.getName() + "\n");
        for(Command command : this.commands){
            result.append(command.toPrintableString());
        }

        return result.toString();
    }
    //endregion

}

