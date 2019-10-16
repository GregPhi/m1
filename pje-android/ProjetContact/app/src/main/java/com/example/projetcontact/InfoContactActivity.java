package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;

public class InfoContactActivity extends AppCompatActivity {
    private EditText mEditNomView;
    private  EditText mEditPrenomView;
    private  EditText mEditAgeView;
    private  EditText mEditStreetContactView;
    private  EditText mEditZipcodeContactView;
    private  EditText mEditTowContactView;

    private String nom = "";
    private String prenom = "";
    private String age = "";
    private String rue = "";
    private String ville = "";
    private String cd = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_contact);

        nom = MainActivity.updateContact.getNom();
        prenom = MainActivity.updateContact.getPrenom();
        age = MainActivity.updateContact.getAge();
        rue = MainActivity.updateContact.getAddr().getStreet();
        ville = MainActivity.updateContact.getAddr().getTown();
        cd = MainActivity.updateContact.getAddr().getZipcode();

        mEditNomView = findViewById(R.id.info_nom);
        mEditNomView.setHint(getString(R.string.hint_nom,nom));

        mEditPrenomView = findViewById(R.id.info_prenom);
        mEditPrenomView.setHint(getString(R.string.hint_prenom,prenom));

        mEditAgeView = findViewById(R.id.info_age);
        mEditAgeView.setHint(getString(R.string.hint_age,age));

        mEditStreetContactView = findViewById(R.id.info_street);
        if(rue==""){
            mEditStreetContactView.setHint(getString(R.string.hint_street,"Rue : ..."));
        }else{
            mEditStreetContactView.setHint(getString(R.string.hint_street,rue));
        }

        mEditTowContactView = findViewById(R.id.info_town);
        if(ville==""){
            mEditTowContactView.setHint(getString(R.string.hint_town,"Ville : ..."));
        }else{
            mEditTowContactView.setHint(getString(R.string.hint_town,ville));
        }

        mEditZipcodeContactView = findViewById(R.id.info_zip);
        if(cd==""){
            mEditZipcodeContactView.setHint(getString(R.string.hint_zip,"Code postal : ..."));
        }else{
            mEditZipcodeContactView.setHint(getString(R.string.hint_zip,cd));
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reply = new Intent();
                /*if ((TextUtils.isEmpty(mEditNomView.getText()) || TextUtils.isEmpty(mEditPrenomView.getText())) && TextUtils.isEmpty(mEditAgeView.getText())) {
                    setResult(RESULT_CANCELED, reply);
                } else {*/
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
                    if(!TextUtils.isEmpty(mEditNomView.getText())){
                        nom = mEditNomView.getText().toString();
                    }
                    if(!TextUtils.isEmpty(mEditPrenomView.getText())){
                        prenom = mEditPrenomView.getText().toString();
                    }
                    if(!TextUtils.isEmpty(mEditAgeView.getText())){
                        age = mEditAgeView.getText().toString();
                    }
                    MainActivity.updateContact.setNom(nom);
                    MainActivity.updateContact.setPrenom(prenom);
                    MainActivity.updateContact.setAge(age);
                    MainActivity.updateContact.setAddr(adr);
                    setResult(RESULT_OK, reply);
                //}
                finish();
            }
        });
    }
}
