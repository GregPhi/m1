package com.example.appliccontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewContactActivity extends AppCompatActivity {
    private static final String EXTRA_NOM = "nom";
    private static final String EXTRA_AGE = "0";

    private Contact newContact = null;

    private  EditText mEditNomView;
    private  EditText mEditAgeView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact);
        mEditNomView = findViewById(R.id.edit_prenom);
        mEditAgeView = findViewById(R.id.edit_age);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditNomView.getText()) && TextUtils.isEmpty(mEditAgeView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String pre = mEditNomView.getText().toString();
                    String age = mEditAgeView.getText().toString();
                    MainActivity.prenom = pre;
                    MainActivity.age = age;
                    newContact.setAge(age);
                    newContact.setNom(pre);
                    replyIntent.putExtra(EXTRA_NOM, pre);
                    replyIntent.putExtra(EXTRA_AGE, age);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

}
