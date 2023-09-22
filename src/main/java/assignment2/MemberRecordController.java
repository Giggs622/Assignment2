// Programmer: Matt Jones S0201735
// File: MemberRecordController.java
// Date: 17 Sept 2023
// Purpose: COIT11134 Assignment 2

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
 */
public class MemberRecordController implements Initializable
{
    // Declare variables for elements in scene
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
        // No code to initialise
    }

    // Method Submit button action on Member tab to store member details in memberList
    @FXML
    private void btnSubmitMemberAction(ActionEvent event)
    {
        // Check if each text field on member tab is empty
        if (checkIfEmptyMember())
        {
            errorMessageBlank();
        }
        else
        {
            // Declare variables
            int index; //Holds the index number from member search
            int memberId; //Holds the member ID number
            float registerFee; //Holds the registration fee
            String[] array = createMemberArray(); //Holds each element of the members details
            
            // Parse member ID and registration fee from String text field and
            // show an error method if a number is not entered
            try
            {
                memberId = Integer.parseInt(array[0]);
                registerFee = Float.parseFloat(array[5]);
            }
            catch (NumberFormatException ex) // Display an error message and exit method
            {
                errorMessageInteger();
                return;
            }
            
            // Show an error message if the registration fee is a negative number
            // and exit method
            if (registerFee < 0F)
            {
                errorMessageRegisterLow();
                return;
            }
            
            // Create object reference to data handler
            DataHandler data = App.getDataHandler();
            
            // Check if member ID already exists
            index = data.findMemberRecord(memberId);

            // If member ID found, show error message
            if (index > -1)
            {
                errorMessageMemberExists();
            }
            else
            // Add member details into memberList ArrayList in data handler
            {
                Member member = new Member(memberId, array[1], array[2], array[3], array[4], registerFee);
                data.addMember(member);
                // Show information message that details have been stored
                confirmMessage();
            } 
        }
    }

    // Method Submit button action on Student tab to store member details in memberList
    @FXML
    private void btnSubmitStudentAction(ActionEvent event)
    {
        // Check if each text field on student tab is empty
        if (checkIfEmptyMember() || checkIfEmpty(textStudentDiscount.getText()))
        {
            errorMessageBlank();
        }
        else
        {
            // Declare variables
            int memberId; //Holds the student member ID number
            float registerFee; //Holds student registration fee
            float studentDiscount; //Holds student discount
            String[] array = createMemberArray(); //Holds each element of the members details
            
            // Parse member ID, registration fee and student discount from String text field and
            // show an error method if a number is not entered
            try
            {
                memberId = Integer.parseInt(array[0]);
                registerFee = Float.parseFloat(array[5]);
                studentDiscount = Float.parseFloat(textStudentDiscount.getText());
            }
            catch (NumberFormatException ex)  // Display an error message and exit method
            {
                errorMessageInteger();
                return;
            }
            
            // Show an error message if the registration fee is a negative number
            // and exit method
            if (registerFee < 0F)
            {
                errorMessageRegisterLow();
                return;
            }
            
            // Show an error message if the student dicount is not between the 
            // minimum and maximum limits
            if (studentDiscount < Values.STUDENT_DISCOUNT_MIN || studentDiscount > Values.STUDENT_DISCOUNT_MAX)
            {
                errorMessageDiscountRange();
                return;
            }
            
            // Create object reference to data handler
            DataHandler data = App.getDataHandler();
            
            // Check if member ID already exists
            int index = data.findMemberRecord(memberId);

            // If member ID found, show error message
            if (index > -1)
            {
                errorMessageMemberExists();
            }
            else
            // Add member details into memberList ArrayList in data handler
            {
                Student student = new Student(memberId, array[1], array[2], array[3], array[4], registerFee, studentDiscount);
                student.setRegisterFee(registerFee);
                data.addMember(student);
                // Show information message that details have been stored
                confirmMessage();
            } 
        }
    }

    // Method Submit button action on Keynote Speaker tab to store member details in memberList
    @FXML
    private void btnSubmitSpeakerAction(ActionEvent event)
    {
        // Check if each text field on keynote speaker tab is empty
        if (checkIfEmptyMember() || checkIfEmpty(textSpeakerTopic.getText()))
        {
            errorMessageBlank();
        }
        else
        {
            // Declare variables
            int memberId; //Holds the student member ID number
            float registerFee; //Holds student registration fee
            String speakerTopic; //Holds speaker topic
            String[] array = createMemberArray(); //Holds each element of the members details
            
            // Parse member ID and registration fee from String text field and
            // show an error method if a number is not entered
            try
            {
                memberId = Integer.parseInt(array[0]);
                registerFee = Float.parseFloat(array[5]);
            }
            catch (NumberFormatException ex) // Display an error message and exit method
            {
                errorMessageInteger();
                return;
            }
            
            // Show an error message if the registration fee is a negative number
            // and exit method
            if (registerFee < 0F)
            {
                errorMessageRegisterLow();
                return;
            }
            
            // Get speaker topic from text field
            speakerTopic = textSpeakerTopic.getText();
            
            // Create object reference to data handler
            DataHandler data = App.getDataHandler();
            
            // Check if member ID already exists
            int index = data.findMemberRecord(memberId);

            // If member ID found, show error message
            if (index > -1)
            {
                errorMessageMemberExists();
            }
            else
            // Add member details into memberList ArrayList in data handler
            {
                Speaker speaker = new Speaker(memberId, array[1], array[2], array[3], array[4], registerFee, speakerTopic);
                speaker.setRegisterFee(registerFee);
                data.addMember(speaker);
                // Show information message that details have been stored
                confirmMessage();
            } 
        }
    }

    // Method to get the text from each text field of the member details and
    // store in an array
    private String[] createMemberArray()
    {
        // Declare variables to store text field text
        String a = textMemberId.getText();
        String b = textMemberName.getText();
        String c = textMemberUni.getText();
        String d = textMemberEmail.getText();
        String e = textMemberPhone.getText();
        String f = textMemberFee.getText();
        // Create an array of the text field data
        String[] array = {a, b, c, d, e, f};

        return array;
    }
    
    // Method to check if a String object is empty or blank
    private boolean checkIfEmpty(String s)
    {
        // Declare variable
        boolean check = false; //Hold check boolean
        // Check if String is empty or blank
        if (s.isEmpty() || s.isBlank())
        {
            check = true;
        }

        return check;
    }

    // Method to check if member details are empty or blank
    private boolean checkIfEmptyMember()
    {
        // Declare variables
        boolean check = false; //Hold check boolean
        // Create an array of memebr details from text fields
        String[] array = createMemberArray();
        // Check if each memebr detail is empty
        for (String s : array)
        {
            // Set check to true if member detail is empty
            if(checkIfEmpty(s))
            {
                check = true;
            }
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
        Alert alert = new Alert(Alert.AlertType.ERROR, "Member ID, Registration Fee & Discount must be an number");
        alert.showAndWait();
    }
    
    // Method to show an erroe message if registration fee is a negative number
    private void errorMessageRegisterLow()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Registration Fee must be a positive number");
        alert.showAndWait();
    }
    
    // Method to show an erroe message if the student discount entered is outside of the range
     private void errorMessageDiscountRange()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Discount must be between %.1f%% and %.1f%%", Values.STUDENT_DISCOUNT_MIN, Values.STUDENT_DISCOUNT_MAX));
        alert.showAndWait();
    }
    
    // Method to show an erroe message if the member ID entered already exists
    private void errorMessageMemberExists()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Member ID already exists. Please enter another Member ID");
        alert.showAndWait();
    }
    
    // Method to show an information message that memebr details were successfully added
    // and also clears the text fields 
    private void confirmMessage()
    {
        // Information message to inform that memebr has been stored
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Member successfully added");
        
        // Clear the text fields
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
