package com.example.projetcontact.view.contactgroup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projetcontact.NewContactInGroup;
import com.example.projetcontact.NewGroupInContact;
import com.example.projetcontact.R;
import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Groups;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class GroupInContactListAdapter extends RecyclerView.Adapter<GroupInContactListAdapter.GroupViewHolder> {

    class GroupViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomItemView;
        private final ImageButton pict;

        private GroupViewHolder(final View itemView) {
            super(itemView);
            nomItemView = itemView.findViewById(R.id.nom);
            pict = itemView.findViewById(R.id.avatar);
        }
    }

    private final NewGroupInContact mContext;
    private final LayoutInflater mInflater;
    private List<Groups> mGroups; // Cached copy of words

    public GroupInContactListAdapter(NewGroupInContact context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public GroupInContactListAdapter.GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_newcontact_ingroup_item, parent, false);
        return new GroupInContactListAdapter.GroupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GroupInContactListAdapter.GroupViewHolder holder, int position) {
        if (mGroups != null) {
            final Groups current = mGroups.get(position);
            System.out.println("Adapt -> cId : "+current.getId());
            holder.nomItemView.setText(current.getNom());
            holder.pict.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.addGroup(current.getId(),current);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.nomItemView.setText("Nom");
        }
    }

    public void setGroups(List<Groups> groups) {
        mGroups = groups;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mGroups != null)
            return mGroups.size();
        else return 0;
    }

}