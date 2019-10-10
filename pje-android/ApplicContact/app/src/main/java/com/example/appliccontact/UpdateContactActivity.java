package com.example.appliccontact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateContactActivity extends AppCompatActivity {

    private Contact uC;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        EditText eN = findViewById(R.id.update_nom);
        EditText eA = findViewById(R.id.update_age);
        String n = eN.toString();
        String a = eA.toString();
        uC = new Contact(n,a);
        bundle.putInt("id",uC.getId());
        bundle.putString("nom",uC.getNom());
        bundle.putString("age",uC.getAge());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void saveUpdate(View view){
        Intent in = new Intent(this,InfoContactActivity.class);
        MainActivity.mContactViewModel.updateContact(uC);
        in.putExtras(bundle);
        startActivity(in);
    }
}
