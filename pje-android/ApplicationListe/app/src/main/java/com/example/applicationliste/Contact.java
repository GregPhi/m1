package com.example.applicationliste;

import java.io.Serializable;

public class Contact implements Serializable {
    private String nom;
    private String age;

    public Contact(){
        this.nom = "";
        this.age = "";
    }

    public Contact(String n, String a){
        this.nom = n;
        this.age = a;
    }

    public String getNom(){
        return this.nom;
    }
    public String getAge(){
        return this.age;
    }

    public void setNom(String n){
        this.nom = n;
    }

    public void setAge(String a){
        this.age = a;
    }
}
