// Programmer: Matt Jones S0201735
// File: Student.java
// Date: 17 Sept 2023
// Purpose: COIT11134 Assignment 2

package assignment2;

public class Student extends Member
{
    // Instance variables
    private float studentDiscount;
    
    // Constructor
    public Student(int memberId, String memberName, String uniName, String memberEmail, String memberPhone, float registerFee, float studentDiscount)
    {
        super(memberId, memberName, uniName, memberEmail, memberPhone, registerFee);
        this.studentDiscount = studentDiscount;
    }
    
    // Student discount getter and setter
    public float getStudentDiscount()
    {
        return studentDiscount;
    }

    public void setStudentDiscount(float studentDiscount)
    {
        this.studentDiscount = studentDiscount;
    }
    
    // Register Fee Getter for Student class
    @Override
    public float getRegisterFee()
    {
        float baseRegisterFee = super.getRegisterFee();
        return baseRegisterFee - (baseRegisterFee * (this.studentDiscount / 100));
    }

    // toString method for Student class
    @Override
    public String toString()
    {
        return String.format("%-12.12s%-20.20s%-20.20s%-20.20s%-14.14s$%-13.2fNA", super.getMemberId(), super.getMemberName(), super.getUniName(), super.getMemberEmail(), super.getMemberPhone(), getRegisterFee());
    }
}
