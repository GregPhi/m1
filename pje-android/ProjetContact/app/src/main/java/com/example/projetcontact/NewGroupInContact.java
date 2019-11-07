package com.example.projetcontact;

import android.content.Intent;
import android.os.Bundle;

import com.example.projetcontact.view.group.GroupViewModel;
import com.example.projetcontact.view.contactgroup.GroupInContactListAdapter;
import com.example.projetcontact.objet.Groups;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewGroupInContact extends AppCompatActivity {
    public static GroupViewModel mGroupViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_group_in_contact);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_grp_in_contact);
        final GroupInContactListAdapter adapter = new GroupInContactListAdapter(this);
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

    public void addGroup(int id, Groups groups){
        Intent intent = new Intent(NewGroupInContact.this, InfoContactActivity.class);
        intent.putExtra("Id",id);
        intent.putExtra("Groups",groups);
        setResult(RESULT_OK, intent);
        finish();
    }
}
