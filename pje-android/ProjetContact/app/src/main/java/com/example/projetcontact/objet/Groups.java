package com.example.projetcontact.objet;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "groupe_table")
public class Groups {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String nom;

    public Groups(){
        this.nom = "";
    }

    public Groups(String n){
        this.nom = n;
    }

    public String getNom(){ return this.nom; }
    public int getId(){return this.id;}

    public void setNom(String n){this.nom = n;}
    public void setId(int id){this.id = id;}
}
