/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Matt6
 */
public class DisplayTotalController implements Initializable
{
    @FXML
    private Label labelTotal;
    
    private DataHandler data;
    private String feeTotal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        data = App.getDataHandler();
        feeTotal = String.format("$%s",Float.toString(data.getTotalFee()));
        
        labelTotal.setText(feeTotal);
    }

}
