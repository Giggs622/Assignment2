// Programmer: Matt Jones S0201735
// File: DisplayAllController.java
// Date: 17 Sept 2023
// Purpose: COIT11134 Assignment 2

package assignment2;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 */
public class DisplayAllController implements Initializable
{
    // Declare variables for elements in scene
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
    private ArrayList<Member> memberList; //Holds memberList
    private DataHandler data; //Created object to access data handler

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

        // Get object reference from data handler
        data = App.getDataHandler();
        // Store memberList from data handler
        memberList = data.getArrayList();

        // Print each member record from memberList into the table view
        for (Member m : memberList)
        {
            tableView.getItems().add(m);
        }
    }
}
