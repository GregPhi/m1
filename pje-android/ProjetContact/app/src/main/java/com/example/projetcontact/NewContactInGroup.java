package com.example.projetcontact;

import android.os.Bundle;
import android.content.Intent;

import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Groups;
import com.example.projetcontact.view.contactgroup.ContactInGroupListAdapter;
import com.example.projetcontact.view.contact.ContactViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewContactInGroup extends AppCompatActivity {
    public static ContactViewModel mContactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact_in_group);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ContactInGroupListAdapter adapter = new ContactInGroupListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mContactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        mContactViewModel.getmAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable final List<Contact> contacts) {
                // Update the cached copy of the words in the adapter.
                adapter.setContacts(contacts);
            }
        });
    }

    public void addContact(int id, Contact contact){
        System.out.println("addContact id : "+id);
        Intent intent = new Intent(NewContactInGroup.this, InfoGroupActivity.class);
        intent.putExtra("Id",id);
        intent.putExtra("Contact",contact);
        setResult(RESULT_OK, intent);
        finish();
    }
}
