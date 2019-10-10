package com.example.projetcontact;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "contact_table")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nom;
    private String prenom;
    private String age;
    private ArrayList<Numero> num;
    private Address addr;


    public Contact(){
        this.nom = "";
        this.prenom = "";
        this.age = "";
        Numero nm = new Numero();
        this.num.add(nm);
        this.addr = new Address();

    }

    public Contact(String nom,String prenom, String age, Numero num, Address addr){
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.addr = addr;
        this.num.add(num);
    }

    public Contact(String nom, Numero num){
        this.nom = nom;
        this.addr = new Address();
        this.num.add(num);
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

    @NonNull
    @Override
    public String toString(){
        return "Prenom -> "+nom+" || Age -> "+age;
    }
}
