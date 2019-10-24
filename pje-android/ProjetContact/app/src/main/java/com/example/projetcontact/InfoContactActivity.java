package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetcontact.objet.Address;
import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Numero;
import com.example.projetcontact.view.numero.NumeroListAdapter;
import com.example.projetcontact.view.numero.NumeroViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InfoContactActivity extends AppCompatActivity {
    public static NumeroViewModel mNumeroViewModel;

    public static final int RETOUR_INFO_ACTIVITY_REQUEST_CODE = 42;

    public static final int NEW_NUMERO_TO_CONTACT_ACTIVITY_REQUEST_CODE = 1;
    public static Numero newNumero = new Numero();

    public static final int UPDATE_ACTIVITY_REQUEST_CODE = 2;
    public static Numero updateNumero;

    private EditText mEditNomView;
    private EditText mEditPrenomView;
    private EditText mEditAgeView;
    private EditText mEditStreetContactView;
    private EditText mEditZipcodeContactView;
    private EditText mEditTowContactView;

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
        final Contact current = ContactActivity.updateContact;
        nom = current.getNom();
        prenom = current.getPrenom();
        age = current.getAge();
        rue = current.getAddr().getStreet();
        ville = current.getAddr().getTown();
        cd = current.getAddr().getZipcode();

        mEditNomView = findViewById(R.id.info_nom);
        mEditNomView.setText(nom);

        mEditPrenomView = findViewById(R.id.info_prenom);
        mEditPrenomView.setText(prenom);

        mEditAgeView = findViewById(R.id.info_age);
        mEditAgeView.setText(age);

        mEditStreetContactView = findViewById(R.id.info_street);
        mEditStreetContactView.setText(rue);

        mEditTowContactView = findViewById(R.id.info_town);
        mEditTowContactView.setText(ville);

        mEditZipcodeContactView = findViewById(R.id.info_zip);
        mEditZipcodeContactView.setText(cd);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_num);
        final NumeroListAdapter adapter = new NumeroListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNumeroViewModel = ContactActivity.mNumeroViewModel;
        mNumeroViewModel.getAllNumeroForAContact(current).observe(this, new Observer<List<Numero>>() {
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
                ContactActivity.updateContact.setNom(nom);
                ContactActivity.updateContact.setPrenom(prenom);
                ContactActivity.updateContact.setAge(age);
                ContactActivity.updateContact.setAddr(adr);
                setResult(RESULT_OK, reply);
                finish();
            }
        });

        final Button nN = findViewById(R.id.button_new_numero);
        nN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(InfoContactActivity.this, NewNumeroActiviry.class);
                newNumero.setContactId(current.getId());
                startActivityForResult(intent, NEW_NUMERO_TO_CONTACT_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_NUMERO_TO_CONTACT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            mNumeroViewModel.insert(newNumero);
        }if (requestCode == UPDATE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            mNumeroViewModel.insert(updateNumero);
        } if ( requestCode == NEW_NUMERO_TO_CONTACT_ACTIVITY_REQUEST_CODE && resultCode == RETOUR_INFO_ACTIVITY_REQUEST_CODE){
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.numero_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void removeNumero(Numero numero){
        mNumeroViewModel.delete(numero);
    }

    public void infosNumero(Numero numero){
        Intent intent = new Intent(this,InfoNumeroActivity.class);
        updateNumero = numero;
        mNumeroViewModel.delete(numero);
        startActivityForResult(intent, UPDATE_ACTIVITY_REQUEST_CODE);
    }
}