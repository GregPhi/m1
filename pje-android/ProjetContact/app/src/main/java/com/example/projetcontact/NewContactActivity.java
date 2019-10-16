package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewContactActivity extends AppCompatActivity {

    private EditText mEditNomView;
    private  EditText mEditPrenomView;
    private  EditText mEditAgeView;
    private  EditText mEditStreetContactView;
    private  EditText mEditZipcodeContactView;
    private  EditText mEditTowContactView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact);
        MainActivity.newContact = new Contact();
        mEditNomView = findViewById(R.id.edit_nom);
        mEditNomView.setHint(getString(R.string.hint_nom,"Nom : ..."));
        mEditPrenomView = findViewById(R.id.edit_prenom);
        mEditPrenomView.setHint(getString(R.string.hint_prenom,"Prenom : ..."));
        mEditAgeView = findViewById(R.id.edit_age);
        mEditAgeView.setHint(getString(R.string.hint_age,"Age : ..."));
        mEditStreetContactView = findViewById(R.id.edit_street);
        mEditStreetContactView.setHint(getString(R.string.hint_street,"Rue : ..."));
        mEditTowContactView = findViewById(R.id.edit_town);
        mEditTowContactView.setHint(getString(R.string.hint_town,"Ville : ..."));
        mEditZipcodeContactView = findViewById(R.id.edit_zip);
        mEditZipcodeContactView.setHint(getString(R.string.hint_zip,"Code postal : ..."));

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
                    if(TextUtils.isEmpty(mEditStreetContactView.getText())){
                        rue = mEditStreetContactView.getText().toString();
                    }
                    if(TextUtils.isEmpty(mEditTowContactView.getText())){
                        ville = mEditTowContactView.getText().toString();
                    }
                    if(TextUtils.isEmpty(mEditZipcodeContactView.getText())){
                        cd = mEditZipcodeContactView.getText().toString();
                    }
                    adr.setStreet(rue);
                    adr.setTown(ville);
                    adr.setZipcode(cd);
                    MainActivity.newContact.setNom(nom);
                    MainActivity.newContact.setPrenom(pre);
                    MainActivity.newContact.setAge(age);
                    MainActivity.newContact.setAddr(adr);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}