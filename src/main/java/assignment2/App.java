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
    // Declare initial variables
    private static Scene sceneMain; //Holds main scene
    private static Stage stage; //Holds stage
    private static DataHandler data; //Creates data handler object

    @Override
    public void start(Stage stage) throws IOException
    {
        //Instatiates the DataHandler object
        data = new DataHandler("members.txt");

        // Sets JavaFX parent for main menu
        Parent rootMain = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        // Creates the main menu scene 
        sceneMain = new Scene(rootMain);

        // Sets the JavaFX stage
        this.stage = stage;

        // Sets the main menu scene to the stage
        stage.setScene(sceneMain);
        stage.show();
    }

    //Method for passing a reference to the data object in other classes
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

    // Main method to launch application
    public static void main(String[] args)
    {
        launch();
    }

}
