package com.example.projetcontact.objet;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String nom;
    private String prenom;
    private String age;

    @Embedded public Address addr;

    public Contact(){
        this.nom = "";
        this.prenom = "";
        this.age = "";
        this.addr = new Address();
    }

    public Contact(String nom,String prenom, String age, Address addr){
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.addr = addr;
    }

    public Contact(String nom, String p, String a){
        this.nom = nom;
        this.prenom = p;
        this.age = a;
        this.addr = new Address();
    }

    public void setId(int i){ this.id = i;}
    public void setNom(String n){ this.nom = n;}
    public void setPrenom(String p){ this.prenom = p;}
    public void setAge(String a){ this.age = a;}
    public void setAddr(Address a){ this.addr = a; }

    public int getId(){ return this.id;}
    public String getNom(){
        return this.nom;
    }
    public String getAge(){
        return this.age;
    }
    public String getPrenom(){ return this.prenom; }
    public Address getAddr(){ return this.addr; }

    @NonNull
    @Override
    public String toString(){
        return "Nom -> "+nom+" || Prenom -> "+prenom+" || Age -> "+age + "";
    }

    public Contact(Parcel in){
        this.nom = in.readString();
        this.prenom = in.readString();
        this.age = in.readString();
        this.addr = new Address();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nom);
        dest.writeString(this.prenom);
        dest.writeString(this.age);
    }
}
