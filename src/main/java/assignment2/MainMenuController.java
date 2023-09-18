/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
 *
 * @author Matt6
 */
public class MainMenuController implements Initializable
{

    @FXML
    private BorderPane borderPane;

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
            // TODO
            welcomePage = loadScene("welcomePage.fxml");
            borderPane.setCenter(welcomePage);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    @FXML
    private void homeAction(ActionEvent event) throws IOException
    {
        App.normalStageWidth();
        welcomePage = loadScene("welcomePage.fxml");
        borderPane.setCenter(welcomePage);
    }

    @FXML
    private void addMemberAction(ActionEvent event) throws IOException
    {
        App.normalStageWidth();
        memberRecord = loadScene("memberRecord.fxml");
        borderPane.setCenter(memberRecord);
    }

    @FXML
    private void searchAction(ActionEvent event) throws IOException
    {
        App.normalStageWidth();
        memberSearch = loadScene("memberSearch.fxml");
        borderPane.setCenter(memberSearch);
    }

    @FXML
    private void viewAllMemberAction(ActionEvent event) throws IOException
    {
        App.extendStageSize();
        displayAll = loadScene("displayAll.fxml");
        borderPane.setCenter(displayAll);
    }

    @FXML
    private void viewTotalFeeAction(ActionEvent event) throws IOException
    {
        App.normalStageWidth();
        displayTotal = loadScene("displayTotal.fxml");
        borderPane.setCenter(displayTotal);
    }

    @FXML
    private void exitAction(ActionEvent event)
    {
        System.out.println("You clicked on Exit!");

        App.getDataHandler().saveData();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close?");
        alert.showAndWait().ifPresent(response ->
        {
            if (response == ButtonType.OK)
            {
                Platform.exit();
            }
        });
    }

    private Parent loadScene(String sc) throws IOException
    {
        return FXMLLoader.load(getClass().getResource(sc));
    }

}
