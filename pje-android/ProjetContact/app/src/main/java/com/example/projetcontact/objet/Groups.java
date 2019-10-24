package com.example.projetcontact.objet;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "groupe_table")
public class Groups implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Groups createFromParcel(Parcel in) {
            return new Groups(in);
        }

        public Groups[] newArray(int size) {
            return new Groups[size];
        }
    };

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

    public Groups(Parcel in){
        this.nom = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nom);
    }
}
