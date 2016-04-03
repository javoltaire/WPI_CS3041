package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @Author Jules Voltaire on 4/2/2016.
 */
public class Option {
    //region Variable
    private StringProperty name = new SimpleStringProperty(this, "name", "");

    private StringProperty description = new SimpleStringProperty(this, "description", "");
    //endregion

    //region Constructors
    public Option(String name, String description){
        this.name.setValue(name);
        this.description.setValue(description);
    }
    //endregion

    //region Getters
    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getName() {
        return name.get();
    }

    public String getDescription() {
        return description.get();
    }
    //endregion

    //region Setters
    public void setName(String name) {
        this.name.set(name);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
    //endregion












}
