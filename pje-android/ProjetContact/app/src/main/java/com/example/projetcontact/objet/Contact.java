package com.example.projetcontact.objet;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String nom;
    private String prenom;
    private String age;

    //private ArrayList<Numero> num = new ArrayList<Numero>();

    @Embedded public Address addr;


    public Contact(){
        this.nom = "";
        this.prenom = "";
        this.age = "";
        this.addr = new Address();

    }

    public Contact(String nom,String prenom, String age, Numero num, Address addr){
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.addr = addr;
        //this.num.add(num);
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
    //public void setNum(ArrayList<Numero> n){ this.num = n;}

    public int getId(){ return this.id;}
    public String getNom(){
        return this.nom;
    }
    public String getAge(){
        return this.age;
    }
    public String getPrenom(){ return this.prenom; }
    //public ArrayList<Numero> getNum(){ return this.num; }
    public Address getAddr(){ return this.addr; }

    /*public void addNumero(Numero n){
        if(!this.num.contains(n)){
            this.num.add(n);
        }
    }*/

    @NonNull
    @Override
    public String toString(){
        return "Nom -> "+nom+" || Prenom -> "+prenom+" || Age -> "+age + "";
    }
}
