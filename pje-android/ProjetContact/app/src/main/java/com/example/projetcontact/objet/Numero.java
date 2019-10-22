package com.example.projetcontact.objet;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "numero_table",
        foreignKeys = @ForeignKey(entity = Contact.class,
                                  parentColumns = "id",
                                  childColumns = "contact_id"))
public class Numero implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Numero createFromParcel(Parcel in) {
            return new Numero(in);
        }

        public Numero[] newArray(int size) {
            return new Numero[size];
        }
    };

    @NonNull
    @PrimaryKey
    private String numero;

    private String categorie;
    @ColumnInfo(name = "contact_id") public int contactId;

    public Numero (String num, String category, int id){
        this.numero = num;
        this.categorie = category;
        this.contactId = id;
    }

    public Numero (){
        this.numero = "";
        this.categorie = "";
        this.contactId = 0;
    }

    public String getCategorie(){ return categorie; }

    public String getNumero() {
        return numero;
    }

    public void setCategorie(String category) {
        this.categorie = category;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int id) {
        this.contactId = id;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @NonNull
    @Override
    public String toString() {
        return "numero : " + this.numero + "category : " + this.categorie;
    }

    public Numero(Parcel in){
        this.numero = in.readString();
        this.categorie = in.readString();
        this.contactId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.numero);
        dest.writeString(this.categorie);
        dest.writeInt(this.contactId);
    }
}
