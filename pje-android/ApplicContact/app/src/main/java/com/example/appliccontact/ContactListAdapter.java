package com.example.appliccontact;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import android.os.AsyncTask;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {
    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomItemView;
        private final TextView ageItemView;
        public Button bD;

        private ContactViewHolder(final View itemView) {
            super(itemView);
            nomItemView = itemView.findViewById(R.id.nom);
            ageItemView = itemView.findViewById(R.id.age);
            bD = itemView.findViewById(R.id.delete);
            bD.setActivated(true);
        }
    }

    private final LayoutInflater mInflater;
    private List<Contact> mContacts; // Cached copy of words

    ContactListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        if (mContacts != null) {
            final Contact current = mContacts.get(position);
            holder.nomItemView.setText(current.getNom());
            holder.ageItemView.setText(current.getAge());
            holder.bD.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    System.out.println("button");
                    removeContact(current);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.nomItemView.setText("Prenom");
            holder.ageItemView.setText("Age");
        }
    }

    public void removeContact(Contact contact){
        mContacts.remove(contact);
        notifyDataSetChanged();
    }

    void setContacts(List<Contact> contacts){
        mContacts = contacts;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mContacts != null)
            return mContacts.size();
        else return 0;
    }

}
