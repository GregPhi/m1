package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewNumeroActiviry extends AppCompatActivity {

    public static final int RETOUR_INFO_ACTIVITY_REQUEST_CODE = 42;

    private EditText mEditNumView;
    private  EditText mEditCatView;
    private String num = "";
    private String categorie = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_numero);

        mEditNumView = findViewById(R.id.edit_num);
        mEditNumView.setHint(getString(R.string.hint_numero, "Numero : ..."));
        mEditCatView = findViewById(R.id.edit_cat);
        mEditCatView.setHint(getString(R.string.hint_categotie, "Categorie : ..."));
        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditNumView.getText()) && TextUtils.isEmpty(mEditCatView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    if (!TextUtils.isEmpty(mEditNumView.getText())) {
                        num = mEditNumView.getText().toString();
                    }
                    if (!TextUtils.isEmpty(mEditCatView.getText())) {
                        categorie = mEditCatView.getText().toString();
                    }
                    InfoContactActivity.newNumero.setNumero(num);
                    InfoContactActivity.newNumero.setCategorie(categorie);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

        final Button buttonR = findViewById(R.id.retour_contact);
        buttonR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent reply = new Intent();
                setResult(RETOUR_INFO_ACTIVITY_REQUEST_CODE, reply);
                finish();
            }
        });
    }
}
