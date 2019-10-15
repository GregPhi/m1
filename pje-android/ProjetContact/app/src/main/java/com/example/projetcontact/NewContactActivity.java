package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewContactActivity extends AppCompatActivity {
    public static final String EXTRA_NOM = "nom";
    public static final String EXTRA_PRENOM = "prenom";
    public static final String EXTRA_AGE = "age";

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
        mEditNomView = findViewById(R.id.edit_nom);
        mEditPrenomView = findViewById(R.id.edit_prenom);
        mEditAgeView = findViewById(R.id.edit_age);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditNomView.getText()) || TextUtils.isEmpty(mEditPrenomView.getText()) || TextUtils.isEmpty(mEditAgeView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String nom = mEditNomView.getText().toString();
                    String pre = mEditPrenomView.getText().toString();
                    String age = mEditAgeView.getText().toString();
                    replyIntent.putExtra(EXTRA_NOM, nom);
                    replyIntent.putExtra(EXTRA_PRENOM, pre);
                    replyIntent.putExtra(EXTRA_AGE, age);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

}