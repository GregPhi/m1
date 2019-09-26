package com.example.applicationliste;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    final private View itemView;
    private Contact associatedContact;
    public TextView nomView;
    public TextView ageView;

    public int click = 0;

    public ContactViewHolder(final View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.nomView = itemView.findViewById(R.id.nom);
        this.ageView = itemView.findViewById(R.id.age);
        Button bI = itemView.findViewById(R.id.incremente);
        Button bD = itemView.findViewById(R.id.decremente);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click++;
                new AlertDialog.Builder(itemView.getContext()).setTitle("Click").setMessage(Integer.toString(click)).show();
                }});
        bI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer age = Integer.parseInt(ageView.getText().toString());
                age = age + 1;
                ageView.setText(age.toString());
                //new AlertDialog.Builder(itemView.getContext()).setTitle("Incremente contact : "+nomView.getText().toString()).setMessage(ageView.getText().toString()).show();
            }});
        bD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer age = Integer.parseInt(ageView.getText().toString());
                age = age -1;
                ageView.setText(age.toString());
                //new AlertDialog.Builder(itemView.getContext()).setTitle("Decremente contact : "+nomView.getText().toString()).setMessage(ageView.getText().toString()).show();
            }});
    }

    public void display(Contact contact) {
        associatedContact = contact;
        nomView.setText(contact.getNom());
        ageView.setText(contact.getAge());
    }
}
