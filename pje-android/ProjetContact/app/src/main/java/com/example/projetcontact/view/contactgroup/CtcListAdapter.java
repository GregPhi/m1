package com.example.projetcontact.view.contactgroup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.projetcontact.InfoGroupActivity;
import com.example.projetcontact.R;
import com.example.projetcontact.objet.Contact;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CtcListAdapter extends RecyclerView.Adapter<CtcListAdapter.ContactViewHolder>{
    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomItemView;
        private final TextView prenomItemView;
        private final TextView ageItemView;
        private final Button bD;
        private final Button bI;

        private ContactViewHolder(final View itemView) {
            super(itemView);
            nomItemView = itemView.findViewById(R.id.nom);
            prenomItemView = itemView.findViewById(R.id.prenom);
            ageItemView = itemView.findViewById(R.id.age);
            bD = itemView.findViewById(R.id.delete);
            bD.setActivated(true);
            bI = itemView.findViewById(R.id.infos);
            bI.setActivated(true);
        }
    }

    private final InfoGroupActivity contextContact;
    private final LayoutInflater mInflater;
    private List<Contact> mAllContacts;

    public CtcListAdapter(InfoGroupActivity context) {
        mInflater = LayoutInflater.from(context);
        contextContact = context;
    }

    @Override
    public CtcListAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new CtcListAdapter.ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CtcListAdapter.ContactViewHolder holder, int position) {
        if (mAllContacts != null) {
            final Contact current = mAllContacts.get(position);
            holder.nomItemView.setText(current.getNom());
            holder.prenomItemView.setText(current.getPrenom());
            holder.ageItemView.setText(current.getAge());
            holder.bD.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    contextContact.removeContact(current);
                }
            });
            holder.bI.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    contextContact.infosContact(current);
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
        mAllContacts = contacts;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mAllContacts != null )
            return mAllContacts.size();
        else return 0;
    }

}
