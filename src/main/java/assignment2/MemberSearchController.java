// Programmer: Matt Jones S0201735
// File: MemberSearchController.java
// Date: 17 Sept 2023
// Purpose: COIT11134 Assignment 2

package assignment2;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 */
public class MemberSearchController implements Initializable
{
    // Declare variables for elements in scene
    @FXML
    private TextField textMemberIdSearch;
    @FXML
    private TableView<Member> tableView;
    @FXML
    private TableColumn<Member, Integer> colMemberId;
    @FXML
    private TableColumn<Member, String> colMemberName;
    @FXML
    private TableColumn<Member, String> colMemberUni;
    @FXML
    private TableColumn<Member, String> colMemberEmail;
    @FXML
    private TableColumn<Member, String> colMemberPhone;
    @FXML
    private TableColumn<Member, Float> colMemberFee;
    @FXML
    private TableColumn<Member, Float> colMemberDiscount;
    @FXML
    private TableColumn<Member, String> colMemberTopic;

    // Declare objects
    private ArrayList<Member> memberList;
    private DataHandler data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // Set up columns in table view with Strings used for get methods
        colMemberId.setCellValueFactory(new PropertyValueFactory<Member, Integer>("MemberId"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<Member, String>("MemberName"));
        colMemberUni.setCellValueFactory(new PropertyValueFactory<Member, String>("UniName"));
        colMemberEmail.setCellValueFactory(new PropertyValueFactory<Member, String>("MemberEmail"));
        colMemberPhone.setCellValueFactory(new PropertyValueFactory<Member, String>("MemberPhone"));
        colMemberFee.setCellValueFactory(new PropertyValueFactory<Member, Float>("RegisterFee"));
        colMemberDiscount.setCellValueFactory(new PropertyValueFactory<Member, Float>("StudentDiscount"));
        colMemberTopic.setCellValueFactory(new PropertyValueFactory<Member, String>("SpeechTopic"));
    }

    // Method for Search button action to find member ID stored in database
    @FXML
    private void btnSearchAction(ActionEvent event)
    {
        // Delcare variables
        int memberId; //Holds the member ID number
        int index; //Holds the index number from member search

        // Check if each text field for member ID is empty and show error message
        // if it is
        if (checkIfEmpty(textMemberIdSearch.getText()))
        {
            errorMessageBlank();
        }
        else
        {
            // Parse member ID and registration fee from String text field and
            // show an error method if a number is not entered
            try
            {
                memberId = Integer.parseInt(textMemberIdSearch.getText());
            }
            catch (NumberFormatException ex) // Display an error message and exit method
            {
                errorMessageInteger();
                return;
            }
            
            // Create object reference to data handler
            data = App.getDataHandler();
            // Get memberList ArrayList from data handler
            memberList = data.getArrayList();

            // Check if member ID already exists
            index = data.findMemberRecord(memberId);

            // If member ID found, show error message
            if (index > -1)
            {
                // Get the member details from the memberList
                Member m = memberList.get(index);
                // Display member in table
                tableView.getItems().add(m);
            }
            else
            // Display "Member not found" message
            {
                errorMessageNotFound();
            }
        }
    }

    // Method to check if a String object is empty or blank
    private boolean checkIfEmpty(String s)
    {
        boolean check = false;

        if (s.isEmpty() || s.isBlank())
        {
            check = true;
        }

        return check;
    }

    // Method to show an error message if text field is blank
    private void errorMessageBlank()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all text fields");
        alert.showAndWait();
    }

    // Method to show an erroe message if text entered is not a number
    private void errorMessageInteger()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Member ID must be an integer");
        alert.showAndWait();
    }
    
    // Method to show an erroe message if the member does not exists
    private void errorMessageNotFound()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Member ID not found");
        alert.showAndWait();
    }
}
