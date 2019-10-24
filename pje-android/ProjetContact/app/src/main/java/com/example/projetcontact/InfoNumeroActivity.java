package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetcontact.objet.Numero;

import androidx.appcompat.app.AppCompatActivity;

public class InfoNumeroActivity extends AppCompatActivity {
    private EditText mEditNumeroView;
    private EditText mEditCategorieView;
    private String numero = "";
    private String categorie = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_numero);
        final Numero current = InfoContactActivity.updateNumero;
        numero = current.getNumero();
        categorie = current.getCategorie();

        mEditNumeroView = findViewById(R.id.info_numero);
        mEditNumeroView.setText(numero);

        mEditCategorieView = findViewById(R.id.info_cat);
        mEditCategorieView.setText(categorie);
        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reply = new Intent();
                if(!TextUtils.isEmpty(mEditNumeroView.getText())){
                    numero = mEditNumeroView.getText().toString();
                }
                if(!TextUtils.isEmpty(mEditCategorieView.getText())){
                    categorie = mEditCategorieView.getText().toString();
                }
                InfoContactActivity.updateNumero.setNumero(numero);
                InfoContactActivity.updateNumero.setCategorie(categorie);
                setResult(RESULT_OK, reply);
                finish();
            }
        });
    }
}
