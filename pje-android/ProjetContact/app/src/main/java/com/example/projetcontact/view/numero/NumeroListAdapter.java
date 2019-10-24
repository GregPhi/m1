package com.example.projetcontact.view.numero;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.projetcontact.InfoContactActivity;
import com.example.projetcontact.R;
import com.example.projetcontact.objet.Numero;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class NumeroListAdapter extends RecyclerView.Adapter<NumeroListAdapter.NumeroViewHolder> {

    class NumeroViewHolder extends RecyclerView.ViewHolder {
        private final TextView numeroItemView;
        private final TextView categorieItemView;
        private final Button bD;
        private final Button bI;

        private NumeroViewHolder(final View itemView) {
            super(itemView);
            numeroItemView = itemView.findViewById(R.id.numero);
            categorieItemView = itemView.findViewById(R.id.categorie);
            bD = itemView.findViewById(R.id.delete);
            bD.setActivated(true);
            bI = itemView.findViewById(R.id.infos);
            bI.setActivated(true);
        }
    }

    private final InfoContactActivity mContext;
    private final LayoutInflater mInflater;
    private List<Numero> mNumeros; // Cached copy of words

    public NumeroListAdapter(InfoContactActivity context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public NumeroListAdapter.NumeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_num_item, parent, false);
        return new NumeroListAdapter.NumeroViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NumeroListAdapter.NumeroViewHolder holder, int position) {
        if (mNumeros != null) {
            final Numero current = mNumeros.get(position);
            holder.numeroItemView.setText(current.getNumero());
            holder.categorieItemView.setText(current.getCategorie());
            holder.bD.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    mContext.removeNumero(current);
                }
            });
            holder.bI.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    mContext.infosNumero(current);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.numeroItemView.setText("Numero");
            holder.categorieItemView.setText("Categorie");
        }
    }



    public void setNumeros(List<Numero> numeros){
        mNumeros = numeros;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mNumeros != null)
            return mNumeros.size();
        else return 0;
    }

}

