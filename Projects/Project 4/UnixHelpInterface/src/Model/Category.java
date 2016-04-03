package Model;

import Model.Enums.CATEGORIES;
import javafx.beans.property.*;
import java.util.*;

/**
 * @Author Jules Voltaire on 3/31/2016.
 * This class represent a Category for commands, e.g. Files and Folders or System Calls
 */
public class Category {
    //region Variables
    /**
     * Unique identifier of a category
     */
    private UUID id = UUID.randomUUID();

    /**
     * String property to hold the name.
     */
    private StringProperty name = new SimpleStringProperty(this, "name", "");

    /**
     * Observable list to hold the commands that are part of this category
     */
    private List<Command> commands = new ArrayList<>();
    //endregion

    //region Constructors
    /**
     * Initializes a new instance of a category with the given value
     * @param name The name for this new Category
     */
    public Category (String name){
        this.name.setValue(name);
    }

    /**
     * Initializes a new instance of a category with the given value
     * @param id The id for the category
     * @param name The name for this new Category
     */
    public Category (String id, String name){
        this.id = UUID.fromString(id);
        this.name.setValue(name);
    }
    //endregion

    //region Getters

    /**
     * Gets the value of the name property
     * @return The name of the Category
     */
    public String getName() {
        return name.get();
    }

    /**
     * Gets the id of the category
     * @return The id of the category
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the list of commands in this category
     * @return The list of commands in this category
     */
    public List<Command> getCommands() {
        return commands;
    }
    //endregion





    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}

