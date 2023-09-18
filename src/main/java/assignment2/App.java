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
public class App extends Application {

   
    private static Scene sceneMain;
    private static Scene sceneAddMember;
    private static Scene sceneSearch;
    private static Scene sceneDisplayAll;
    private static Scene sceneTotalFee;
    private static Stage stage;
    private static DataHandler data;    

    @Override
    public void start(Stage stage) throws IOException {
        //Instatiates the DataHandler object
        data = new DataHandler("members.txt");  
        
        //Creates the Main and AddNumber scene 
        Parent rootMain = FXMLLoader.load(getClass().getResource("welcomePage.fxml"));
        Parent rootAddMem = FXMLLoader.load(getClass().getResource("memberRecord.fxml"));
        Parent rootSearch = FXMLLoader.load(getClass().getResource("memberSearch.fxml"));
        Parent rootDisplayAll = FXMLLoader.load(getClass().getResource("displayAll.fxml"));
        Parent rootTotalFee = FXMLLoader.load(getClass().getResource("displayTotal.fxml"));
        
        sceneMain = new Scene(rootMain);
        sceneAddMember = new Scene(rootAddMem);
        sceneSearch = new Scene(rootSearch);
        sceneDisplayAll = new Scene(rootDisplayAll);
        sceneTotalFee = new Scene(rootTotalFee);
        
        this.stage = stage;
        //set the current scene to the main scene
        
        //scene = new Scene(loadFXML("mainMenu"), 640, 480);
        stage.setScene(sceneMain);
        stage.show();
    }

    //Method for passing a reference to the data object
    public static DataHandler getDataHandler()
    {
        return data;
    }  
    
    //Method for switching scenes
    public static void changeScene(int sc)
    {
        switch(sc) { 
            case 0: stage.setScene(sceneMain); break;
            case 1: stage.setScene(sceneAddMember); break;
            case 2: stage.setScene(sceneSearch); break;
            case 3: stage.setScene(sceneDisplayAll); break;
            case 4: stage.setScene(sceneTotalFee); break;
            default:
        } 
    }
    
   //Method for exiting the application
    public static void exit()
    { 
        stage.close();
    }


    public static void main(String[] args) {
        launch();
    }

}

