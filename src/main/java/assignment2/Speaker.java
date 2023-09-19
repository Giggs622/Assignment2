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
    public void setRegisterFee(float registerFee)
    {
        float finalRegisterFee = registerFee - (registerFee * (Values.SPEAKER_DISCOUNT / 100));
        super.setRegisterFee(finalRegisterFee);
    }

    // toString method for Speaker class
    @Override
    public String toString()
    {
        return String.format("%d,%s,%s,%s,%s,%.2f,%.2f,%s,", super.getMemberId(), super.getMemberName(), super.getUniName(), super.getMemberEmail(), super.getMemberPhone(), getRegisterFee(), Values.SPEAKER_DISCOUNT, this.speechTopic);
    }
}
