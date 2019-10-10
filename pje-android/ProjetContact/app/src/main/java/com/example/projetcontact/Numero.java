package com.example.projetcontact;

import androidx.annotation.NonNull;

public class Numero {
    private String numero;
    private String categorie;

    public Numero (String num, String category){
        this.numero = num;
        this.categorie = category;
    }
    public Numero (String num){
        this.numero = num;
        this.categorie = "";
    }
    public Numero (){
        this.numero = "";
        this.categorie = "";
    }


    public String getCategory() {
        return categorie;
    }

    public String getNumero() {
        return numero;
    }

    public void setCategory(String category) {
        this.categorie = category;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @NonNull
    @Override
    public String toString() {
        return "numero : " + this.numero + "category : " + this.categorie;
    }
}
