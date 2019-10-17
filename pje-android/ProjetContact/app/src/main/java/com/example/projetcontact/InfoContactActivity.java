package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InfoContactActivity extends AppCompatActivity {
    public static NumeroViewModel mNumeroViewModel;

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
        Contact current = MainActivity.updateContact;
        nom = current.getNom();
        prenom = current.getPrenom();
        age = current.getAge();
        rue = current.getAddr().getStreet();
        ville = current.getAddr().getTown();
        cd = current.getAddr().getZipcode();

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

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final NumeroListAdapter adapter = new NumeroListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNumeroViewModel = ViewModelProviders.of(this).get(NumeroViewModel.class);
        mNumeroViewModel.getAllNumeroForAContact(current.getId()).observe(this, new Observer<List<Numero>>() {
            @Override
            public void onChanged(@Nullable final List<Numero> numeros) {
                // Update the cached copy of the words in the adapter.
                adapter.setNumeros(numeros);
            }
        });

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