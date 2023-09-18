// Programmer: Matt Jones S0201735
// File: App.java
// Date: 17 Sept 2023
// Purpose: COIT11134 Assignment 2
package assignment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.application.Application.launch;

/**
 * JavaFX App
 */
public class App extends Application
{

    private static Scene sceneMain;
    private static Stage stage;
    private static DataHandler data;

    @Override
    public void start(Stage stage) throws IOException
    {
        //Instatiates the DataHandler object
        data = new DataHandler("members.txt");

        //Creates the Main and AddNumber scene 
        Parent rootMain = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

        sceneMain = new Scene(rootMain);

        this.stage = stage;

        //scene = new Scene(loadFXML("mainMenu"), 640, 480);
        stage.setScene(sceneMain);
        stage.show();
    }

    //Method for passing a reference to the data object
    public static DataHandler getDataHandler()
    {
        return data;
    }

    // Method to change scene to normal size
    public static void normalStageWidth()
    {
        stage.setWidth(Values.NORMAL_STAGE_WIDTH);
    }

    // Method to change scene to extended size 
    public static void extendStageSize()
    {
        stage.setWidth(Values.EXTEND_STAGE_WIDTH);
    }

    //Method for exiting the application
    public static void exit()
    {
        stage.close();
    }

    public static void main(String[] args)
    {
        launch();
    }

}
