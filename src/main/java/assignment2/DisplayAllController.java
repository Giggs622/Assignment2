/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
 *
 * @author Matt6
 */
public class DisplayAllController implements Initializable
{


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
        
        data = App.getDataHandler();
        memberList = data.getArrayList();
        
        for (Member m : memberList)
        {
            tableView.getItems().add(m);
        }
    }    
    
    
}