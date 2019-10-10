package com.example.appliccontact;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoContactActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_contact);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        TextView nom = findViewById(R.id.nom);
        String no = b.getString("nom");
        nom.setText(no);
        TextView age = findViewById(R.id.age);
        String ag = b.getString("age");
        age.setText(ag);
    }
}
