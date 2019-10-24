package com.example.projetcontact.view.group;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.projetcontact.GroupActivity;
import com.example.projetcontact.InfoContactActivity;
import com.example.projetcontact.R;
import com.example.projetcontact.objet.Groups;
import com.example.projetcontact.view.numero.NumeroListAdapter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.GroupViewHolder> {

    class GroupViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomItemView;
        private final Button bD;
        private final Button bI;

        private GroupViewHolder(final View itemView) {
            super(itemView);
            nomItemView = itemView.findViewById(R.id.nom);
            bD = itemView.findViewById(R.id.delete);
            bD.setActivated(true);
            bI = itemView.findViewById(R.id.infos);
            bI.setActivated(true);
        }
    }

    private final GroupActivity mContext;
    private final LayoutInflater mInflater;
    private List<Groups> mGroups; // Cached copy of words

    public GroupListAdapter(GroupActivity context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public GroupListAdapter.GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_group_item, parent, false);
        return new GroupListAdapter.GroupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GroupListAdapter.GroupViewHolder holder, int position) {
        if (mGroups != null) {
            final Groups current = mGroups.get(position);
            holder.nomItemView.setText(current.getNom());
            holder.bD.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    mContext.removeGroup(current);
                }
            });
            holder.bI.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    mContext.infoGroup(current);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.nomItemView.setText("Numero");
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

