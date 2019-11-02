package com.example.projetcontact.objet;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Address implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    private String street;
    private String zipcode;
    private String town;

    public Address(String s,String t,String z){
        this.street = s;
        this.zipcode = z;
        this.town = t;
    }

    public Address(){
        this.street = "";
        this.zipcode = "";
        this.town = "";
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }


    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @NonNull
    @Override
    public String toString() {
        return "rue : " + this.street + "ville : " + this.getTown() + "numéro de département : " + this.getZipcode();
    }

    public Address(Parcel in) {
        this.street = in.readString();
        this.zipcode = in.readString();
        this.town = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.street);
        dest.writeString(this.zipcode);
        dest.writeString(this.town);
    }
}
