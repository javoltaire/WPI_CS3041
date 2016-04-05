package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @Author Jules Voltaire on 4/2/2016.
 * This class represent an option for a command and a short description of what that option does to the command.
 * For example, for ls, an option is
 * -r : reverses the order of the sort.
 * Code wise, that would be represented as
 * Option lsr = new Option("r", "reverses the order of the sort");
 */
public class Option {
    //region Variable
    /**
     * String property to hold the name of the Option
     */
    private StringProperty name = new SimpleStringProperty(this, "name", "");

    /**
     * String property to hold the description of the Option
     */
    private StringProperty description = new SimpleStringProperty(this, "description", "");
    //endregion

    //region Constructors

    /**
     * Initializes a new instance of this class with the given parameters
     * @param name The name of the option i.e. r
     * @param description The description of what the command does i.e. reverses the order of the sort
     */
    public Option(String name, String description){
        this.name.setValue(name);
        this.description.setValue(description);
    }
    //endregion

    //region Getters and Setters
    //region Name
    /**
     * Gets the name property
     * @return The name property object.
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Gets the name of the option
     * @return The value of the name property
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the name of the option
     * @param name The new value for the name of the option
     */
    public void setName(String name) {
        this.name.set(name);
    }
    //endregion

    //region Description
    /**
     * Gets the description property
     * @return The description property object
     */
    public StringProperty descriptionProperty() {
        return description;
    }

    /**
     * Gets the description of the option
     * @return The value of the description property
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Sets the description of the option
     * @param description The new value for the description of the option
     */
    public void setDescription(String description) {
        this.description.set(description);
    }
    //endregion
    //endregion


}
