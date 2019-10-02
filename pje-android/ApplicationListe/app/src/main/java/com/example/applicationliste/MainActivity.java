package com.example.applicationliste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    private static ArrayList<Contact> temp = new ArrayList<Contact>();

    private Contact newContact = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent main = getIntent();
        newContact = (Contact) main.getSerializableExtra(getString(R.string.nC));
        if(newContact!=null){
            Contact c = newContact;
            temp.add(c);
            //System.out.println("pre : "+newContact.getNom()+" | age : "+newContact.getAge());
        }
        final RecyclerView rv = (RecyclerView) findViewById(R.id.listView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new ContactListAdapter(contacts));
        addNew();
    }

    public void createList(){
        Contact c1 = new Contact("Prenom 1","20");
        Contact c2 = new Contact("Prenom 2","15");
        contacts.add(c1);
        contacts.add(c2);
    }

    public void addNew(){
        if(temp.size()!=0){
            for(int i = 0; i < temp.size(); i++){
                Contact c = temp.get(i);
                if(!contacts.contains(c)){
                    contacts.add(c);
                }
            }
        }
    }

    public void createContact(View view){
        Intent create = new Intent(this, CreateContact.class);
        create.putExtra(getString(R.string.nC),newContact);
        startActivity(create);
    }
}
