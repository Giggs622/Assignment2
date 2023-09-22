// Programmer: Matt Jones S0201735
// File: MainMenuController.java
// Date: 17 Sept 2023
// Purpose: COIT11134 Assignment 2

package assignment2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 */
public class MainMenuController implements Initializable
{
    // Declare variables for elements in scene
    @FXML
    private BorderPane borderPane;

    // Declare parent objects to hold each scene for the application
    private Parent welcomePage;
    private Parent memberRecord;
    private Parent memberSearch;
    private Parent displayAll;
    private Parent displayTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            // Load the welcome page scene when the main menu is initally called
            welcomePage = loadScene("welcomePage.fxml");
            borderPane.setCenter(welcomePage);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // Method for Home button action to set scene to home page
    @FXML
    private void homeAction(ActionEvent event) throws IOException
    {
        App.normalStageWidth();
        welcomePage = loadScene("welcomePage.fxml");
        borderPane.setCenter(welcomePage);
    }

    // Method for Add Member button action to set scene the record member page
    @FXML
    private void addMemberAction(ActionEvent event) throws IOException
    {
        App.normalStageWidth();
        memberRecord = loadScene("memberRecord.fxml");
        borderPane.setCenter(memberRecord);
    }

    // Method for Search button action to set scene to the search page
    @FXML
    private void searchAction(ActionEvent event) throws IOException
    {
        App.extendStageSize();
        memberSearch = loadScene("memberSearch.fxml");
        borderPane.setCenter(memberSearch);
    }

    // Method for View All button action to set scene to the view all page
    @FXML
    private void viewAllMemberAction(ActionEvent event) throws IOException
    {
        App.extendStageSize();
        displayAll = loadScene("displayAll.fxml");
        borderPane.setCenter(displayAll);
    }

    // Method for Total Fee button action to set scene to the total fee page
    @FXML
    private void viewTotalFeeAction(ActionEvent event) throws IOException
    {
        App.normalStageWidth();
        displayTotal = loadScene("displayTotal.fxml");
        borderPane.setCenter(displayTotal);
    }

    // Method for the Exit button action to save member records to file and
    // exit the application
    @FXML
    private void exitAction(ActionEvent event)
    {
        // Call the data handler save data method
        App.getDataHandler().saveData();

        // Display a confirmation message before exiting application
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close?");
        alert.showAndWait().ifPresent(response ->
        {
            // Exit application if user selects OK
            if (response == ButtonType.OK)
            {
                Platform.exit();
            }
        });
    }

    // Method to load a JavaFX scene
    private Parent loadScene(String sc) throws IOException
    {
        return FXMLLoader.load(getClass().getResource(sc));
    }
}
