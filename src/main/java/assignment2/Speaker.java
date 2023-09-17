// Programmer: Matt Jones S0201735
// File: Speaker.java
// Date: 17 Sept 2023
// Purpose: COIT11134 Assignment 2

package assignment2;

public class Speaker extends Member
{
    // Instance variables
    private String speechTopic;         // Title to presented at the conference
    
    // Constructor
    public Speaker(int memberId, String memberName, String uniName, String memberEmail, String memberPhone, float registerFee, String speechTopic)
    {
        super(memberId, memberName, uniName, memberEmail, memberPhone, registerFee);
        this.speechTopic = speechTopic;
    }
    
    // Speech Topic Getter and Setter
    public String getSpeechTopic()
    {
        return speechTopic;
    }

    public void setSpeechTopic(String speechTopic)
    {
        this.speechTopic = speechTopic;
    }
    
    // Register Fee Getter for Student class
    @Override
    public float getRegisterFee()
    {
        float baseRegisterFee = super.getRegisterFee();
        return baseRegisterFee - (baseRegisterFee * (Values.SPEAKER_DISCOUNT / 100));
    }
    
    // toString method for Speaker class
    @Override
    public String toString()
    {
        return String.format("%-12.12s%-20.20s%-20.20s%-20.20s%-14.14s$%-13.2f%-20s", super.getMemberId(), super.getMemberName(), super.getUniName(), super.getMemberEmail(), super.getMemberPhone(), getRegisterFee(), this.speechTopic);
    }
}
