package com.example.applicationliste;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactViewHolder>{

    private List<Contact> contacts = new ArrayList<Contact>();

    public ContactListAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_layout, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.display(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
