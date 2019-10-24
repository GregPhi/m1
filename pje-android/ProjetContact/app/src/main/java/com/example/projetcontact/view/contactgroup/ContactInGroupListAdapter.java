package com.example.projetcontact.view.contactgroup;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projetcontact.InfoGroupActivity;
import com.example.projetcontact.NewContactInGroup;
import com.example.projetcontact.R;
import com.example.projetcontact.objet.Contact;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ContactInGroupListAdapter extends RecyclerView.Adapter<ContactInGroupListAdapter.ContactViewHolder> {

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomItemView;
        private final TextView prenomItemView;
        private final TextView ageItemView;
        private final ImageButton pict;

        private ContactViewHolder(final View itemView) {
            super(itemView);
            nomItemView = itemView.findViewById(R.id.nom);
            prenomItemView = itemView.findViewById(R.id.prenom);
            ageItemView = itemView.findViewById(R.id.age);
            pict = itemView.findViewById(R.id.avatar);
        }
    }

    private final NewContactInGroup mContext;
    private final LayoutInflater mInflater;
    private List<Contact> mContacts; // Cached copy of words

    public ContactInGroupListAdapter(NewContactInGroup context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public ContactInGroupListAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_newcontact_ingroup_item, parent, false);
        return new ContactInGroupListAdapter.ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactInGroupListAdapter.ContactViewHolder holder, int position) {
        if (mContacts != null) {
            final Contact current = mContacts.get(position);
            System.out.println("Adapt -> cId : "+current.getId());
            holder.nomItemView.setText(current.getNom());
            holder.prenomItemView.setText(current.getPrenom());
            holder.ageItemView.setText(current.getAge());
            holder.pict.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.addContact(current.getId(),current);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.nomItemView.setText("Nom");
            holder.prenomItemView.setText("Prenom");
            holder.ageItemView.setText("Age");
        }
    }

    public void setContacts(List<Contact> contacts){
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
