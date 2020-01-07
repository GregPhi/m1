package com.example.projetcontact.view.contactgroup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.projetcontact.GroupActivity;
import com.example.projetcontact.InfoContactActivity;
import com.example.projetcontact.R;
import com.example.projetcontact.objet.Groups;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class GrpListAdapter extends RecyclerView.Adapter<GrpListAdapter.GroupViewHolder>{
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

    private final InfoContactActivity contextGroup;
    private final LayoutInflater mInflater;
    private List<Groups> mAllGroups;

    public GrpListAdapter(InfoContactActivity context) {
        mInflater = LayoutInflater.from(context);
        contextGroup = context;
    }

    @Override
    public GrpListAdapter.GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_group_item, parent, false);
        return new GrpListAdapter.GroupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GrpListAdapter.GroupViewHolder holder, int position) {
        if (mAllGroups != null) {
            final Groups current = mAllGroups.get(position);
            holder.nomItemView.setText(current.getNom());
            holder.bD.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    contextGroup.removeGroup(current);
                }
            });
            holder.bI.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    contextGroup.infoGroup(current);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.nomItemView.setText("Nom");
        }
    }

    public void setGroups(List<Groups> groups){
        mAllGroups = groups;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mAllGroups != null )
            return mAllGroups.size();
        else return 0;
    }
}
