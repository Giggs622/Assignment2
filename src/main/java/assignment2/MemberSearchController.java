/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
 *
 * @author Matt6
 */
public class MemberSearchController implements Initializable
{

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

    private ArrayList<Member> memberList;
    private DataHandler data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        colMemberId.setCellValueFactory(new PropertyValueFactory<Member, Integer>("MemberId"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<Member, String>("MemberName"));
        colMemberUni.setCellValueFactory(new PropertyValueFactory<Member, String>("UniName"));
        colMemberEmail.setCellValueFactory(new PropertyValueFactory<Member, String>("MemberEmail"));
        colMemberPhone.setCellValueFactory(new PropertyValueFactory<Member, String>("MemberPhone"));
        colMemberFee.setCellValueFactory(new PropertyValueFactory<Member, Float>("RegisterFee"));
        colMemberDiscount.setCellValueFactory(new PropertyValueFactory<Member, Float>("StudentDiscount"));
        colMemberTopic.setCellValueFactory(new PropertyValueFactory<Member, String>("SpeechTopic"));
    }

    @FXML
    private void btnSearchAction(ActionEvent event)
    {
        int memberId;
        int index;

        if (checkIfEmpty(textMemberIdSearch.getText()))
        {
            errorMessageBlank();
        }
        else
        {
            try
            {
                memberId = Integer.parseInt(textMemberIdSearch.getText());
            }
            catch (NumberFormatException ex)
            {
                errorMessageInteger();
                return;
            }
            data = App.getDataHandler();
            memberList = data.getArrayList();

            index = data.findMemberRecord(memberId);

            // If member ID found, print member information
            if (index > -1)
            {
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

    private boolean checkIfEmpty(String s)
    {
        boolean check = false;

        if (s.isEmpty() || s.isBlank())
        {
            check = true;
        }

        return check;
    }

    private void errorMessageBlank()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all text fields");
        alert.showAndWait();
    }

    private void errorMessageInteger()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Member ID must be an integer");
        alert.showAndWait();
    }
    
    private void errorMessageNotFound()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Member ID not found");
        alert.showAndWait();
    }

}
