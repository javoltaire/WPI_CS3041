package Controller;

import sun.applet.Main;

/**
 * Created by jules on 4/11/2016.
 */
public class ControllerSingleton {
    //region Variables and Properties
    /**
     * Singleton Instance of this class
     */
    private static ControllerSingleton instace = new ControllerSingleton();

    /**
     * Single instance of the MainPage class
     */
    private MainPage mainPage = new MainPage();
    //endregion

    //region Constructor

    /**
     * Private constructor to prevent other classs from instantiating this class
     */
    private ControllerSingleton(){}
    //endregion

    //region Getters and Setters

    /**
     * Gets the single instance of this class
     * @return The only instance of this class
     */
    public static ControllerSingleton getInstace(){
        return instace;
    }

    /**
     * Get the mainPage instance to be used in this program
     * @return The main page instance for this program.
     */
    public MainPage getMainPage() {
        return mainPage;
    }
    //endregion
}
