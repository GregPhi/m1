package com.example.appliccontact;

public class Address {
    public String street;
    public String zip;
    public String town;

    public Address(){
        this.street = "";
        this.town = "";
        this.zip = "";
    }

    public Address(String s, String t, String z){
        this.street = s;
        this.town = t;
        this.zip = z;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
