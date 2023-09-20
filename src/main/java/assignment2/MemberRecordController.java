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
            errorMessageBlank();
        }
        else
        {
            int index;
            int memberId;
            float registerFee;
            String[] array = createMemberArray();
            
            try
            {
                memberId = Integer.parseInt(array[0]);
                registerFee = Float.parseFloat(array[5]);
            }
            catch (NumberFormatException ex)
            {
                errorMessageInteger();
                return;
            }
            
            if (registerFee < 0F)
            {
                errorMessageRegisterLow();
                return;
            }
            
            DataHandler data = App.getDataHandler();
            index = data.findMemberRecord(memberId);

            // If member ID found, print member information
            if (index > -1)
            {
                errorMessageMemberExists();
            }
            else
            // Display "Member not found" message
            {
                Member member = new Member(memberId, array[1], array[2], array[3], array[4], registerFee);
                data.addMember(member);
                
                confirmMessage();
            } 
        }
    }

    @FXML
    private void btnSubmitStudentAction(ActionEvent event)
    {
        if (checkIfEmptyMember() || checkIfEmpty(textStudentDiscount.getText()))
        {
            errorMessageBlank();
        }
        else
        {
            int memberId;
            float registerFee;
            float studentDiscount;
            String[] array = createMemberArray();
            
            try
            {
                memberId = Integer.parseInt(array[0]);
                registerFee = Float.parseFloat(array[5]);
                studentDiscount = Float.parseFloat(textStudentDiscount.getText());
            }
            catch (NumberFormatException ex)
            {
                errorMessageInteger();
                return;
            }
            
            if (registerFee < 0F)
            {
                errorMessageRegisterLow();
                return;
            }
            
            if (studentDiscount < Values.STUDENT_DISCOUNT_MIN || studentDiscount > Values.STUDENT_DISCOUNT_MAX)
            {
                errorMessageDiscountRange();
                return;
            }
            
            DataHandler data = App.getDataHandler();
            int index = data.findMemberRecord(memberId);

            // If member ID found, print member information
            if (index > -1)
            {
                errorMessageMemberExists();
            }
            else
            // Display "Member not found" message
            {
                Student student = new Student(memberId, array[1], array[2], array[3], array[4], registerFee, studentDiscount);
                student.setRegisterFee(registerFee);
                
                data.addMember(student);
                
                confirmMessage();
            } 
        }
    }

    @FXML
    private void btnSubmitSpeakerAction(ActionEvent event)
    {
        if (checkIfEmptyMember() || checkIfEmpty(textSpeakerTopic.getText()))
        {
            errorMessageBlank();
        }
        else
        {
            int memberId;
            float registerFee;
            String speakerTopic;
            String[] array = createMemberArray();
            
            try
            {
                memberId = Integer.parseInt(array[0]);
                registerFee = Float.parseFloat(array[5]);
            }
            catch (NumberFormatException ex)
            {
                errorMessageInteger();
                return;
            }
            
            if (registerFee < 0F)
            {
                errorMessageRegisterLow();
                return;
            }
            
            speakerTopic = textSpeakerTopic.getText();
            DataHandler data = App.getDataHandler();
            int index = data.findMemberRecord(memberId);

            // If member ID found, print member information
            if (index > -1)
            {
                errorMessageMemberExists();
            }
            else
            // Display "Member not found" message
            {
                Speaker speaker = new Speaker(memberId, array[1], array[2], array[3], array[4], registerFee, speakerTopic);
                speaker.setRegisterFee(registerFee);
                
                data.addMember(speaker);
                
                confirmMessage();
            } 
        }
    }

     private String[] createMemberArray()
    {
        String a = textMemberId.getText();
        String b = textMemberName.getText();
        String c = textMemberUni.getText();
        String d = textMemberEmail.getText();
        String e = textMemberPhone.getText();
        String f = textMemberFee.getText();

        String[] array = {a, b, c, d, e, f};

        return array;
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

    private boolean checkIfEmptyMember()
    {
        boolean check = false;

        String[] array = createMemberArray();

        for (String s : array)
        {
            if(checkIfEmpty(s))
            {
                check = true;
            }
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
        Alert alert = new Alert(Alert.AlertType.ERROR, "Member ID, Registration Fee & Discount must be an number");
        alert.showAndWait();
    }
    
    private void errorMessageRegisterLow()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Registration Fee must be a positive number");
        alert.showAndWait();
    }
    
     private void errorMessageDiscountRange()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Discount must be between %.1f%% and %.1f%%", Values.STUDENT_DISCOUNT_MIN, Values.STUDENT_DISCOUNT_MAX));
        alert.showAndWait();
    }
    
    private void errorMessageMemberExists()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Member ID already exists. Please enter another Member ID");
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
