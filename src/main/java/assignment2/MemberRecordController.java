/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Matt6
 */
public class MemberRecordController implements Initializable
{

    @FXML
    private TextField textStudentDiscount;
    @FXML
    private TextField textSpeakerTopic;
    @FXML
    private TextField textMemberId;
    @FXML
    private TextField textMemberName;
    @FXML
    private TextField textMemberUni;
    @FXML
    private TextField textMemberEmail;
    @FXML
    private TextField textMemberPhone;
    @FXML
    private TextField textMemberFee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void btnSubmitMemberAction(ActionEvent event)
    {
        if (checkIfEmptyMember())
        {
            errorMessage();
        }
        else
        {
            try
            {
                String[] array = createMemberArray();
                DataHandler data;
                int memberId = Integer.parseInt(array[0]);
                float registerFee = Float.parseFloat(array[5]);

                // Add search for member ID
                
                Member member = new Member(memberId, array[1], array[2], array[3], array[4], registerFee);
                data = App.getDataHandler();
                data.addMember(member);
                
                confirmMessage();
            }
            catch (NumberFormatException ex)
            {
                errorMessageInteger();
            }
        }

    }

    @FXML
    private void btnSubmitStudentAction(ActionEvent event)
    {
        if (checkIfEmptyMember() || checkIfEmpty(textStudentDiscount.getText()))
        {
            errorMessage();
        }
        else
        {
            try
            {
                String[] array = createMemberArray();
                DataHandler data;
                int memberId = Integer.parseInt(array[0]);
                float registerFee = Float.parseFloat(array[5]);
                float studentDiscount = Float.parseFloat(textStudentDiscount.getText());

                // Add search for member ID
                Student student = new Student(memberId, array[1], array[2], array[3], array[4], registerFee, studentDiscount);
                student.setRegisterFee(registerFee);
                
                data = App.getDataHandler();
                data.addMember(student);
                
                confirmMessage();
            }
            catch (NumberFormatException ex)
            {
                errorMessageInteger();
            }
        }
    }

    @FXML
    private void btnSubmitSpeakerAction(ActionEvent event)
    {
        if (checkIfEmptyMember() || checkIfEmpty(textSpeakerTopic.getText()))
        {
            errorMessage();
        }
        else
        {
            try
            {
                String[] array = createMemberArray();
                DataHandler data;
                int memberId = Integer.parseInt(array[0]);
                float registerFee = Float.parseFloat(array[5]);
                String speakerTopic = textSpeakerTopic.getText();

                // Add search for member ID
                Speaker speaker = new Speaker(memberId, array[1], array[2], array[3], array[4], registerFee, speakerTopic);
                speaker.setRegisterFee(registerFee);
                
                data = App.getDataHandler();
                data.addMember(speaker);
                
                confirmMessage();
            }
            catch (NumberFormatException ex)
            {
                errorMessageInteger();
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

    private String[] createMemberArray()
    {
        String a = textMemberId.getText();
        String b = textMemberName.getText();
        String c = textMemberUni.getText();
        String d = textMemberEmail.getText();
        String e = textMemberPhone.getText();
        String f = textMemberFee.getText();

        String[] array =
        {
            a, b, c, d, e, f
        };

        return array;
    }

    private boolean checkIfEmptyMember()
    {
        boolean check = false;

        String[] array = createMemberArray();

        for (String s : array)
        {
            check = checkIfEmpty(s);
        }

        return check;
    }

    private void errorMessage()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all text fields");
        alert.showAndWait();
    }

    private void errorMessageInteger()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Member ID must be an integer");
        alert.showAndWait();
    }
    
    private void confirmMessage()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Member successfully added");
        
        textMemberId.clear();
        textMemberName.clear();
        textMemberUni.clear();
        textMemberEmail.clear();
        textMemberPhone.clear();
        textMemberFee.clear();
        textStudentDiscount.clear();
        textSpeakerTopic.clear();
        
        alert.showAndWait();
    }
}
