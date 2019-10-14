package com.example.appliccontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InfoContactActivity extends AppCompatActivity {
    private EditText nomEdit;
    private EditText ageEdit;
    private String nom = "";
    private String age = "";

    private Bundle currentBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_contact);

        nom = MainActivity.updateContact.getNom();
        age = MainActivity.updateContact.getAge();

        nomEdit = findViewById(R.id.info_nom);
        nomEdit.setText(getString(R.string.info_nom,nom));

        ageEdit = findViewById(R.id.info_age);
        ageEdit.setText(getString(R.string.info_age,age));

        final Button button = findViewById(R.id.update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reply = new Intent();
                if (TextUtils.isEmpty(nomEdit.getText()) && TextUtils.isEmpty(ageEdit.getText())) {
                    setResult(RESULT_CANCELED, reply);
                } else {
                    nom = nomEdit.getText().toString();
                    age = ageEdit.getText().toString();
                    MainActivity.updateContact.setNom(nom);
                    MainActivity.updateContact.setAge(age);
                    setResult(RESULT_OK, reply);
                }
                finish();
            }
        });
    }

    public void retourMain(View view){
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
    }
}
