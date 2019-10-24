package com.example.projetcontact.view.numero;

import android.app.Application;

import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Numero;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NumeroViewModel extends AndroidViewModel {
    private NumeroRepository mRepository;

    private LiveData<List<Numero>> mAllNumeros;

    public NumeroViewModel(Application application){
        super(application);
        mRepository = new NumeroRepository(application);
        mAllNumeros = mRepository.getAllNumeros();
    }

    public LiveData<List<Numero>> getmAllNumeros(){ return  mAllNumeros;}

    public void insert(Numero numero){ mRepository.insert(numero);}
    public void update(Numero numero){ mRepository.update(numero);}
    public void delete(Numero numero) { mRepository.delete(numero);}

    public LiveData<List<Numero>>  getAllNumeroForAContact(Contact contact){ return mRepository.getAllNumeroForAContact(contact);}

    public void deleteNumerosForAContact(Contact contact){mRepository.deleteNumerosForAContact(contact);}
}
