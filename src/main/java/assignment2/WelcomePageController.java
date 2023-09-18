/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author Matt6
 */
public class WelcomePageController implements Initializable
{

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
    @FXML
    private void welcomePageAction(ActionEvent event) {
        System.out.println("You clicked on Member Registration!");
        // Switch to the Member Registration scene 
        App.changeScene(0);        
    }
    
    @FXML
    private void addMemberAction(ActionEvent event) {
        System.out.println("You clicked on Member Registration!");
        // Switch to the Member Registration scene 
        App.changeScene(1);        
    }

    @FXML
    private void searchAction(ActionEvent event) {
        System.out.println("You clicked on Search Member!");
         //Switch to the Search member scene 
        App.changeScene(2);
    }

    @FXML
    private void viewAllMemberAction(ActionEvent event) {
        System.out.println("You clicked on View all members!");
        // Switch to the View all scene              
        App.changeScene(3);         
    }

    @FXML
    private void viewTotalFeeAction(ActionEvent event) {
        System.out.println("You clicked on ViewTotalFee!");  
        // Switch to the View all scene 
        App.changeScene(4);        
    }

    @FXML
    private void exitAction(ActionEvent event) {
        System.out.println("You clicked on Exit!");       
       
        App.getDataHandler().saveData(); 
     
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit();
            }
        });        
    }  
}
