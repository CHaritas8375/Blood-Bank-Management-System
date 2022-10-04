package com.example.bloodbank;

import com.google.firebase.database.DatabaseReference;

public class UserInfo {
    private String id;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String gender;
    private String bloodGroup;
    private String dateOfBirth;
    private String emailAddress;
    private String password;

    public UserInfo(){}
        
    public UserInfo(String id, String firstName, String lastName, String contactNumber, String gender, String bloodGroup, String dateOfBirth, String emailAddress, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
