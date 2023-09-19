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

    private String fileName;
    private ArrayList<Member> memberList;

    public DataHandler(String fileName) throws FileNotFoundException
    {
        this.fileName = fileName;
        //initialise memberList ArrayList
        this.memberList = new ArrayList<Member>();
        //reading all saved members
        readDataFile();
    }

    //Private method for reading all saved members from the text file 
    //and uploading to the memberList ArrayList
    //Not accessible to the external classes
    private void readDataFile() throws FileNotFoundException
    {
        try
        {
            Path path = Paths.get(fileName);
            if (Files.size(path) == 0)
            {
                errorMessage();
            }
            else
            {
                Scanner fileReader = new Scanner(new FileReader(fileName)); //open file
                String myEntry = "";  //hold whole line text       
                int memberID = 0;
                String memberName = "";
                String uniName = "";
                String memberEmail = "";
                String memberPhone = "";
                float registerFee = -1;
                float discount = Values.MEMBER_DISCOUNT;
                String speechTopic = null;

                while (fileReader.hasNextLine())
                {
                    myEntry = fileReader.nextLine();
                    StringTokenizer st = new StringTokenizer(myEntry, ",");

                    while (st.hasMoreTokens())
                    {
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
                        // Create member type
                        Speaker speaker = new Speaker(memberID, memberName, uniName, memberEmail, memberPhone, registerFee, speechTopic);
                        // Add the member to the ArrayList
                        addMember(speaker);
                    }
                    else
                    {
                        // Create member type
                        Student student = new Student(memberID, memberName, uniName, memberEmail, memberPhone, registerFee, discount);
                        // Add the member to the ArrayList
                        addMember(student);
                    }
                }// end of while loop

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
        catch (IOException ex)
        {
            errorMessage();
        }
    }//end of the readDataFile method

    //Method for saving all members from the memberList ArrayList to the text file
    public void saveData()
    {
        try
        {
            Formatter out = new Formatter(fileName);    //open file

            for (Member curMember : memberList)
            {
                out.format("%s\n", curMember.toString());
            }

            out.close();//close file
        }
        catch (SecurityException ex)
        {

        }
        catch (FileNotFoundException ex)
        {

        }

    }//end of the SaveData method 

    // Method for adding a member to the member ArrayList
    public void addMember(Member m)
    {
        memberList.add(m);
    }

    public ArrayList<Member> getArrayList()
    {
        return memberList;
    }
    
    //Method for creating a String containing all member entries
    //The String would be displayed fileReader the text area on the display scene  
    public String getDisplayOutput()
    {
        String allMembers = "";     // to store all members fileReader a single String literal 

        //TODO - get all members' information from the arraylist and add them together to a single string
        return allMembers + "\n" + "Total number of member entry is: " + "a count value";
    }

    public float getTotalFee()
    {
        float totalFee = 0.0f;
        // Get total registration fee
        for (Member curMember : memberList)
        {
            totalFee += curMember.getRegisterFee();
        }
        return totalFee;
    }
    
    private void errorMessage()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "No member records have been stored");
        alert.showAndWait();
    }

    //TODO - add more methods here whenever needed. 
}
