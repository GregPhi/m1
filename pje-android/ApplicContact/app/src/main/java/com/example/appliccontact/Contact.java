package com.example.appliccontact;

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

    public Contact(@NonNull String n, @NonNull String a){
        this.nom = n;
        this.age = a;
    }

    public String getNom(){
        return this.nom;
    }

    public String getAge(){
        return this.age;
    }
}
