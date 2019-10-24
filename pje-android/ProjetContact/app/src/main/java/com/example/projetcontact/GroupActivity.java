package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;

import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Groups;
import com.example.projetcontact.view.group.GroupListAdapter;
import com.example.projetcontact.view.group.GroupViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class GroupActivity extends AppCompatActivity {
    public static GroupViewModel mGroupViewModel;

    public static final int RETOUR_MAIN_ACTIVITY_REQUEST_CODE = 42;

    public static final int NEW_GROUP_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_ACTIVITY_REQUEST_CODE = 2;
    public static Groups updateGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_group);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupActivity.this, NewGroupActivity.class);
                startActivityForResult(intent, NEW_GROUP_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final GroupListAdapter adapter = new GroupListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mGroupViewModel = ViewModelProviders.of(this).get(GroupViewModel.class);
        mGroupViewModel.getmAllGroups().observe(this, new Observer<List<Groups>>() {
            @Override
            public void onChanged(@Nullable final List<Groups> groups) {
                // Update the cached copy of the words in the adapter.
                adapter.setGroups(groups);
            }
        });
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
            case R.id.action_contact:
                Intent intent = new Intent(GroupActivity.this, ContactActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Groups groups = data.getParcelableExtra("Group");
        if (requestCode == NEW_GROUP_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            mGroupViewModel.insert(groups);
        }if (requestCode == UPDATE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
                mGroupViewModel.insert(updateGroup);
        }if ( requestCode == NEW_GROUP_ACTIVITY_REQUEST_CODE && resultCode == RETOUR_MAIN_ACTIVITY_REQUEST_CODE) {
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.contact_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void infoGroup(Groups groups){
        Intent intent = new Intent(this,InfoGroupActivity.class);
        updateGroup = groups;
        startActivityForResult(intent, UPDATE_ACTIVITY_REQUEST_CODE);
    }

    public void removeGroup(Groups group){
        mGroupViewModel.delete(group);
    }
}
