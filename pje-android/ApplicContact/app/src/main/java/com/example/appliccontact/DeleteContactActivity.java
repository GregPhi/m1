package com.example.appliccontact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_contact);
        String nom = MainActivity.removeContact.getNom();
        String age = MainActivity.removeContact.getAge();

        TextView nomTView = findViewById(R.id.text_nom);
        nomTView.setText(getString(R.string.text_nom,nom));

        TextView ageTView = findViewById(R.id.text_age);
        ageTView.setText(getString(R.string.text_age,age));

        final Button button = findViewById(R.id.delete_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reply = new Intent();
                setResult(RESULT_OK, reply);
                finish();
            }
        });
    }

}
