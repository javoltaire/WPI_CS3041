package Controller;

import sun.applet.Main;

/**
 * Created by jules on 4/11/2016.
 */
public class ControllerSingleton {
    //region Variables and Properties
    private static ControllerSingleton instace = new ControllerSingleton();

    private MainPage mainPage = new MainPage();
    //endregion

    //region Constructor
    private ControllerSingleton(){}
    //endregion

    //Getters and Setters
    public static ControllerSingleton getInstace(){
        return instace;
    }

    public MainPage getMainPage() {
        return mainPage;
    }
    //endregion
}
