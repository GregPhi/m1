package com.example.projetcontact.view.contact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.projetcontact.MainContactActivity;
import com.example.projetcontact.R;
import com.example.projetcontact.objet.Contact;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

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

    private final MainContactActivity mContext;
    private final LayoutInflater mInflater;
    private List<Contact> mContacts; // Cached copy of words

    public ContactListAdapter(MainContactActivity context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

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
            holder.prenomItemView.setText(current.getPrenom());
            holder.ageItemView.setText(current.getAge());
            holder.bD.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    mContext.removeContact(current);
                }
            });
            holder.bI.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    mContext.infosContact(current);
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
