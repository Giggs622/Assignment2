// Programmer: Matt Jones S0201735
// File: DataHandler.java
// Date: 17 Sept 2023
// Purpose: COIT11134 Assignment 2
package assignment2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.StringTokenizer;

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
            Scanner in = new Scanner(new FileReader(fileName)); //open file
            String myEntry = "";  //hold whole line text       
            int memberID = 0;
            String memberName = "";
            String uniName = "";
            String memberEmail = "";
            String memberPhone = "";
            float registerFee = -1;
            float discount = Values.MEMBER_DISCOUNT;
            String speechTopic = null;

            while (in.hasNextLine())
            {
                myEntry = in.nextLine();
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

            in.close();//close file
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            //JOptionPane.showMessageDialog(null, "ArrayOutOfBoundsException: " + ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (FileNotFoundException ex)
        {
            //JOptionPane.showMessageDialog(null, "FileNotFoundException: " + ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
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
            //JOptionPane.showMessageDialog(null,"SecurityException: "+ ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
        }
        catch (FileNotFoundException ex)
        {
            //JOptionPane.showMessageDialog(null, "FileNotFoundException: " + ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }//end of the SaveData method 

    // Method for adding a member to the member ArrayList
    public void addMember(Member m)
    {
        memberList.add(m);
    }

    //Method for creating a String containing all member entries
    //The String would be displayed in the text area on the display scene  
    public String getDisplayOutput()
    {
        String allMembers = "";     // to store all members in a single String literal 

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

    //TODO - add more methods here whenever needed. 
}
