package com.example.appliccontact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoContactActivity extends AppCompatActivity {

    private EditText nomEdit;
    private EditText ageEdit;

    private Contact current;
    private Bundle currentBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_contact);

        Intent in = getIntent();
        currentBundle = in.getExtras();
        int id = currentBundle.getInt("id");
        String nom = currentBundle.getString("nom");
        String age = currentBundle.getString("age");
        current = new Contact(nom,age);
        current.setId(id);

        nomEdit = findViewById(R.id.info_nom);
        nomEdit.setText(getString(R.string.info_nom,nom));

        ageEdit = findViewById(R.id.info_age);
        ageEdit.setText(getString(R.string.info_age,age));
    }

    public void updateContact(View view){
        String nom = nomEdit.getText().toString();
        String age = ageEdit.getText().toString();
        current.setNom(nom);
        current.setAge(age);
        MainActivity.mContactViewModel.updateContact(current);
        retourMain(view);
    }

    public void retourMain(View view){
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
    }
}
