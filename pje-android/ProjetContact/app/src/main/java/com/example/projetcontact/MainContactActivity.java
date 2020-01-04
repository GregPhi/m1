package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;

import com.example.projetcontact.objet.Address;
import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Numero;
import com.example.projetcontact.view.contact.ContactListAdapter;
import com.example.projetcontact.view.contact.ContactViewModel;
import com.example.projetcontact.view.contactgroup.ContactGroupViewModel;
import com.example.projetcontact.view.numero.NumeroViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainContactActivity extends AppCompatActivity {
    public static ContactViewModel mContactViewModel;
    public static NumeroViewModel mNumeroViewModel;
    public static ContactGroupViewModel mCtGrpViewModel;

    public static final int RETOUR_MAIN_ACTIVITY_REQUEST_CODE = 42;

    public static final int NEW_CONTACT_ACTIVITY_REQUEST_CODE = 1;

    public static final int UPDATE_ACTIVITY_REQUEST_CODE = 2;
    public static Contact updateContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainContactActivity.this, NewContactActivity.class);
                startActivityForResult(intent, NEW_CONTACT_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ContactListAdapter adapter = new ContactListAdapter(this);
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

        mNumeroViewModel = ViewModelProviders.of(this).get(NumeroViewModel.class);
        mCtGrpViewModel = ViewModelProviders.of(this).get(ContactGroupViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.action_group:
                Intent intent = new Intent(MainContactActivity.this, GroupActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Toast.makeText(
                    getApplicationContext(),
                    R.string.contact_not_saved,
                    Toast.LENGTH_LONG).show();
        }
        if (requestCode == NEW_CONTACT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            newContact(data);
        }  if (requestCode == UPDATE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            mContactViewModel.insert(updateContact);
        }  if ( requestCode == NEW_CONTACT_ACTIVITY_REQUEST_CODE && resultCode == RETOUR_MAIN_ACTIVITY_REQUEST_CODE){
        }
    }

    public void newContact(Intent data){
        Contact contact = data.getParcelableExtra("Contact");
        if(contact!=null){
            mContactViewModel.insert(contact);
        }
        Numero numero = data.getParcelableExtra("Numero");
        if(numero!=null){
            mNumeroViewModel.insert(numero);
        }
    }

    public void infosContact(Contact contact){
        Intent intent = new Intent(this,InfoContactActivity.class);
        updateContact = contact;
        startActivityForResult(intent, UPDATE_ACTIVITY_REQUEST_CODE);
    }

    public void removeContact(Contact contact){
        mCtGrpViewModel.deleteGroupsJoinForContact(contact);
        mNumeroViewModel.deleteNumerosForAContact(contact);
        mContactViewModel.delete(contact);
    }
}
