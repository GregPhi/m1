package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.ContactGroup;
import com.example.projetcontact.objet.Groups;
import com.example.projetcontact.view.contact.ContactViewModel;
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
    public static ContactViewModel mContactViewModel;
    private static Groups current;

    public static final int NEW_CONTACT_IN_GROUP_ACTIVITY_REQUEST_CODE = 666;
    public static final int UPDATE_ACTIVITY_REQUEST_CODE = 2;

    private EditText mEditGroupView;
    private String nom = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_group);
        current = GroupActivity.updateGroup;
        nom = current.getNom();
        mEditGroupView = findViewById(R.id.info_group);
        mEditGroupView.setText(nom);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_grp);
        final CtcListAdapter adapter = new CtcListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mContactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        mJoinViewModel = ViewModelProviders.of(this).get(ContactGroupViewModel.class);
        mJoinViewModel.getContactsForGroup(current.getId()).observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable final List<Contact> contacts) {
                // Update the cached copy of the words in the adapter.
                adapter.setContacts(contacts);
            }
        });

        final Button bS = findViewById(R.id.button_save);
        bS.setOnClickListener(new View.OnClickListener() {
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

        final Button bM = findViewById(R.id.retour_main_group);
        bM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reply = new Intent();
                setResult(GroupActivity.RETOUR_MAIN_ACTIVITY_REQUEST_CODE, reply);
                finish();
            }
        });


        final Button bN = findViewById(R.id.button_new_numero);
        bN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoGroupActivity.this, NewContactInGroup.class);
                startActivityForResult(intent, NEW_CONTACT_IN_GROUP_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_CONTACT_IN_GROUP_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            Contact contact = data.getParcelableExtra("Contact");
            int id = data.getIntExtra("Id",0);
            contact.setId(id);
            ContactGroup cg = new ContactGroup(contact.getId(),current.getId());
            mJoinViewModel.insert(cg);
        } if (requestCode == UPDATE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            mContactViewModel.insert(MainContactActivity.updateContact);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.contact_group_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void infosContact(Contact contact){
        Intent intent = new Intent(this,InfoContactActivity.class);
        MainContactActivity.updateContact = contact;
        startActivityForResult(intent, UPDATE_ACTIVITY_REQUEST_CODE);
    }

    public void removeContact(Contact contact){
        ContactGroup cg = new ContactGroup(contact.getId(),current.getId());
        mJoinViewModel.delete(cg);
    }
}
