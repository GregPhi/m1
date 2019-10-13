package com.example.appliccontact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

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

        final Button button = findViewById(R.id.update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reply = new Intent();
                updateContact();
                Bundle bundle = new Bundle();
                bundle.putInt("id",current.getId());
                bundle.putString("nom",current.getNom());
                bundle.putString("age",current.getAge());
                reply.putExtras(bundle);
                finish();
            }
        });
    }

    public void updateContact(){
        String nom = nomEdit.getText().toString();
        String age = ageEdit.getText().toString();
        current.setNom(nom);
        current.setAge(age);
    }

    public void retourMain(View view){
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
    }
}
