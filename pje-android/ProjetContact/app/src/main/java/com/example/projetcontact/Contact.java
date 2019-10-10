package com.example.projetcontact;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nom;
    private String prenom;
    private String age;
    private Numero num;
    private Address addr;


    public Contact(){
        this.nom = "";
        this.prenom = "";
        this.age = "";
        this.num = new Numero();
        this.addr = new Address();

    }

    public Contact(String nom,String prenom, String age, Numero num, Address addr){
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.addr = addr;
        this.num = num;
    }

    public Contact(String nom, Numero num){
        this.nom = nom;
        this.addr = new Address();
        this.num = num;
    }

    public void setId(int i){ this.id = i;}
    public void setNom(String n){ this.nom = n;}
    public void setAge(String a){ this.age = a;}

    public String getNom(){
        return this.nom;
    }
    public int getId(){ return this.id;}
    public String getAge(){
        return this.age;
    }

    @Override
    public String toString(){
        return "Prenom -> "+nom+" || Age -> "+age;
    }
}
