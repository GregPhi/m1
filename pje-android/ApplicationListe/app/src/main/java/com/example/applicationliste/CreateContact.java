package com.example.applicationliste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CreateContact extends AppCompatActivity {
    private Contact newContact = null;

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.add_contact);

        Intent intent = getIntent();
        newContact = (Contact) intent.getSerializableExtra(getString(R.string.nC));
    }

    public void addContact(View view){
        EditText pre = findViewById(R.id.prenom);
        EditText a = findViewById(R.id.age);
        String prenom = "";
        String age = "";
        if(a.toString() != null){
            age = a.getText().toString();
        }
        if(pre.toString() != null){
            prenom = pre.getText().toString();
        }
        newContact = new Contact(prenom,age);
        Intent main = new Intent(this, MainActivity.class);
        main.putExtra(getString(R.string.nC),newContact);
        startActivity(main);
    }

    public void clear(View view){
        EditText pre = findViewById(R.id.prenom);
        EditText age = findViewById(R.id.age);
        pre.setText("");
        age.setText("");
    }

    public void retourMain(View view){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
}
