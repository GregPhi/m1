package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetcontact.objet.Address;
import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Numero;

import androidx.appcompat.app.AppCompatActivity;

public class NewContactActivity extends AppCompatActivity {

    public static final int RETOUR_MAIN_ACTIVITY_REQUEST_CODE = 42;
    private Contact newContact = new Contact();
    private Numero newNumero = null;

    private EditText mEditNomView;
    private  EditText mEditPrenomView;
    private  EditText mEditAgeView;
    private  EditText mEditStreetContactView;
    private  EditText mEditZipcodeContactView;
    private  EditText mEditTowContactView;
    private EditText mEditNumeroView;
    private EditText mEditCategorieView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact);

        mEditNomView = findViewById(R.id.edit_nom);
        mEditPrenomView = findViewById(R.id.edit_prenom);
        mEditAgeView = findViewById(R.id.edit_age);

        mEditStreetContactView = findViewById(R.id.edit_street);
        mEditTowContactView = findViewById(R.id.edit_town);
        mEditZipcodeContactView = findViewById(R.id.edit_zip);

        mEditNumeroView = findViewById(R.id.edit_num);
        mEditCategorieView = findViewById(R.id.edit_cat);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if ((TextUtils.isEmpty(mEditNomView.getText()) || TextUtils.isEmpty(mEditPrenomView.getText())) && TextUtils.isEmpty(mEditAgeView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String nom = mEditNomView.getText().toString();
                    String pre = mEditPrenomView.getText().toString();
                    String age = mEditAgeView.getText().toString();
                    String rue = "";
                    String ville = "";
                    String cd = "";
                    Address adr = new Address();
                    if(!TextUtils.isEmpty(mEditStreetContactView.getText())){
                        rue = mEditStreetContactView.getText().toString();
                    }
                    if(!TextUtils.isEmpty(mEditTowContactView.getText())){
                        ville = mEditTowContactView.getText().toString();
                    }
                    if(!TextUtils.isEmpty(mEditZipcodeContactView.getText())){
                        cd = mEditZipcodeContactView.getText().toString();
                    }
                    adr.setStreet(rue);
                    adr.setTown(ville);
                    adr.setZipcode(cd);
                    newContact.setNom(nom);
                    newContact.setPrenom(pre);
                    newContact.setAge(age);
                    newContact.setAddr(adr);
                    replyIntent.putExtra("Contact",newContact);

                    String num = "";
                    String categorie = "";
                    if(!TextUtils.isEmpty(mEditNumeroView.getText()) && !TextUtils.isEmpty(mEditCategorieView.getText())){
                        newNumero = new Numero();
                        num = mEditNumeroView.getText().toString();
                        categorie = mEditCategorieView.getText().toString();
                        newNumero.setNumero(num);
                        newNumero.setCategorie(categorie);
                        newNumero.setContactId(newContact.getId());
                    }
                    replyIntent.putExtra("Numero",newNumero);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

        final Button buttonR = findViewById(R.id.retour_main);
        buttonR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent reply = new Intent();
                setResult(RETOUR_MAIN_ACTIVITY_REQUEST_CODE,reply);
                finish();
            }
        });
    }
}