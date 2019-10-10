package com.example.projetcontact;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String nom;
    @NonNull
    private String age;

    public Contact(){
        this.nom = "";
        this.age = "";
    }

    public Contact(String n, String a){
        this.nom = n;
        this.age = a;
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
