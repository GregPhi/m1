package com.example.applicationliste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contacts = new ArrayList<Contact>();

    public MainActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Contact c1 = new Contact("Prenom 1","20");
        Contact c2 = new Contact("Prenom 2","15");
        contacts.add(c1);
        contacts.add(c2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView rv = (RecyclerView) findViewById(R.id.listView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new ContactListAdapter(contacts));
    }


}
