package com.example.appliccontact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoContactActivity extends AppCompatActivity {

    private Contact current;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_contact);
        Intent in = getIntent();
        bundle = in.getExtras();
        int id = bundle.getInt("id");
        TextView nom = findViewById(R.id.nom);
        String no = bundle.getString("nom");
        nom.setText(no);
        TextView age = findViewById(R.id.age);
        String ag = bundle.getString("age");
        age.setText(ag);
        current = new Contact(no,ag);
        current.setId(id);
    }

    public void updateContact(View view){
        Intent in = new Intent(this,UpdateContactActivity.class);
        in.putExtras(bundle);
        startActivity(in);
    }

    public void retourMain(View view){
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
    }
}
