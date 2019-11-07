package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.projetcontact.objet.Groups;
import androidx.appcompat.app.AppCompatActivity;

public class NewGroupActivity extends AppCompatActivity {
    public static final int RETOUR_MAIN_ACTIVITY_REQUEST_CODE = 42;

    private Groups newGroup = new Groups();

    private EditText mEditNomView;
    private String nom = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_group);

        mEditNomView = findViewById(R.id.edit_grp);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditNomView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    if (!TextUtils.isEmpty(mEditNomView.getText())) {
                        nom = mEditNomView.getText().toString();
                    }
                    newGroup.setNom(nom);
                    replyIntent.putExtra("Group",newGroup);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

        final Button buttonR = findViewById(R.id.retour_grp);
        buttonR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent reply = new Intent();
                setResult(RETOUR_MAIN_ACTIVITY_REQUEST_CODE, reply);
                finish();
            }
        });
    }
}
