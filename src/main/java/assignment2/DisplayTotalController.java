// Programmer: Matt Jones S0201735
// File: DisplayTotalController.java
// Date: 17 Sept 2023
// Purpose: COIT11134 Assignment 2

package assignment2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 */
public class DisplayTotalController implements Initializable
{
    // Declare variables for elements in scene
    @FXML
    private Label labelTotal;
    
    // Declare initial variables
    private DataHandler data; //Created to hold object reference to data handler
    private String feeTotal; //Hold the total registration fee
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // Get object reference from data handler
        data = App.getDataHandler();
        // Get the total registration fee from the data handler
        feeTotal = String.format("$%s",Float.toString(data.getTotalFee()));
        // Set the label element to the total fee
        labelTotal.setText(feeTotal);
    }
}
