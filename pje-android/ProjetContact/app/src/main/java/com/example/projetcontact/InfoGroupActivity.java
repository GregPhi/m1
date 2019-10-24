package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Groups;
import com.example.projetcontact.view.contactgroup.ContactGroupViewModel;
import com.example.projetcontact.view.contactgroup.CtcListAdapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InfoGroupActivity extends AppCompatActivity {
    public static ContactGroupViewModel mJoinViewModel;

    private EditText mEditGroupView;
    private String nom = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_group);
        final Groups current = GroupActivity.updateGroup;
        nom = current.getNom();
        mEditGroupView = findViewById(R.id.info_group);
        mEditGroupView.setText(nom);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_grp);
        final CtcListAdapter adapter = new CtcListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mJoinViewModel = ViewModelProviders.of(this).get(ContactGroupViewModel.class);
        mJoinViewModel.getContactsForGroup(current.getId()).observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable final List<Contact> contacts) {
                // Update the cached copy of the words in the adapter.
                adapter.setContacts(contacts);
            }
        });

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reply = new Intent();
                if(!TextUtils.isEmpty(mEditGroupView.getText())){
                    nom = mEditGroupView.getText().toString();
                }
                GroupActivity.updateGroup.setNom(nom);
                setResult(RESULT_OK, reply);
                finish();
            }
        });
    }
}
