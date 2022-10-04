package com.example.bloodbank;

public class BuildingData {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String email;

    public BuildingData(){}

    public BuildingData(String id, String name, String contact, String email, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
