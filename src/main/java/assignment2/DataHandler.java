// Programmer: Matt Jones S0201735
// File: DataHandler.java
// Date: 17 Sept 2023
// Purpose: COIT11134 Assignment 2

package assignment2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.scene.control.Alert;

public class DataHandler
{
    // Declare initialised variables
    private String fileName;                    // String to hold file name
    private ArrayList<Member> memberList;       // Array List to hold list of members

    public DataHandler(String fileName) throws FileNotFoundException
    {
        this.fileName = fileName;
        //initialise memberList ArrayList
        this.memberList = new ArrayList<Member>();
        //reading all saved members
        readDataFile();
    }

    // Method to read file and store in the member ArrayList 
    private void readDataFile() throws FileNotFoundException
    {
        try
        {
            // Get file path
            Path path = Paths.get(fileName);
            // Check if file is empty and display error message if it is
            if (Files.size(path) == 0)
            {
                errorMessage();
            }
            else
            {
                // Initialise variables
                Scanner fileReader = new Scanner(new FileReader(fileName)); //Open file
                String myEntry = "";  //Hold whole line text       
                int memberID = 0;   //Hold member ID number
                String memberName = ""; //Hold member name
                String uniName = ""; //Hold uni name
                String memberEmail = ""; //Hold email
                String memberPhone = ""; //Hold phone number
                float registerFee = -1; //Hold registration fee
                float discount = Values.MEMBER_DISCOUNT; //Hold student discount
                String speechTopic = null; //Hold speaker topic

                // Keep reading file while there is next line
                while (fileReader.hasNextLine())
                {
                    // Initialise variables
                    myEntry = fileReader.nextLine(); //Hold line from file
                    StringTokenizer st = new StringTokenizer(myEntry, ","); 

                    // Keep reading lines while there are more tokens
                    while (st.hasMoreTokens())
                    {
                        // Store each element of member record
                        memberID = Integer.parseInt(st.nextToken());
                        memberName = st.nextToken();
                        uniName = st.nextToken();
                        memberEmail = st.nextToken();
                        memberPhone = st.nextToken();
                        registerFee = Float.parseFloat(st.nextToken());
                        discount = Float.parseFloat(st.nextToken());
                        speechTopic = st.nextToken();
                    }
                    // Add member to the memberList ArrayList
                    if (discount == Values.MEMBER_DISCOUNT)  //full member
                    {
                        // Create member type
                        Member member = new Member(memberID, memberName, uniName, memberEmail, memberPhone, registerFee);
                        // Add the member to the ArrayList
                        addMember(member);
                    }
                    else if (discount == Values.SPEAKER_DISCOUNT)  // keynote member
                    {
                        // Create speaker type
                        Speaker speaker = new Speaker(memberID, memberName, uniName, memberEmail, memberPhone, registerFee, speechTopic);
                        // Add the speaker to the ArrayList
                        addMember(speaker);
                    }
                    else
                    {
                        // Create student type
                        Student student = new Student(memberID, memberName, uniName, memberEmail, memberPhone, registerFee, discount);
                        // Add the student to the ArrayList
                        addMember(student);
                    }
                }// end of while loop
                
                // Retrieve last member record
                Member lastMember = memberList.get(memberList.size() - 1);
                // Show message with details of last member recorded
                lastMemberMessage(lastMember);
                
                fileReader.close();//close file
            }
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {

        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex) //Display error message if file doesn't exist
        {
            errorMessage();
        }
    }//end of the readDataFile method

    // Method for saving all members from the memberList ArrayList to the text file
    public void saveData()
    {
        try
        {
            Formatter out = new Formatter(fileName);    //open file
            // Write each member in the memberList to the file
            for (Member curMember : memberList)
            {
                out.format("%s\n", curMember.toString());
            }

            out.close();//close file
        }
        catch (SecurityException | FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }//end of the SaveData method 

    // Method for adding a member to the member ArrayList
    public void addMember(Member m)
    {
        memberList.add(m);
    }

    // Method to get the ArrayList memberList
    public ArrayList<Member> getArrayList()
    {
        return memberList;
    }
    
    // Method to get the total registration fee
    public float getTotalFee()
    {
        // Initialise variables
        float totalFee = 0.0f; //Hold total fee
        // Add each members registration fee to total fee
        for (Member curMember : memberList)
        {
            totalFee += curMember.getRegisterFee();
        }
        return totalFee;
    }
    
    // Method to display an error mesage pop if no members have been stored
    private void errorMessage()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "No member records have been stored");
        alert.showAndWait();
    }
    
    // Method to show an information pop up with last member recorded details
    private void lastMemberMessage(Member m)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("Last member stored was: %d %s", m.getMemberId(), m.getMemberName()));
        alert.showAndWait();
    }

    // Method to search for a member ID in the memberList ArrayList
    public int findMemberRecord(int memberID)
    {
        // Declare variables
        int index = -1;                 // Store array index number if found or pass number < 0
        int arrayCounter = 0;           // Count to which array index is found in for loop
        
        // Check every element in ListArray for matching member ID
        for (Member e : memberList)
        {
            if (memberID == e.getMemberId())
            {
                index = arrayCounter;
            }
            arrayCounter++;
        }
        
        // Return the index number
        return index;
    }
}
