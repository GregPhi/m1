package com.example.projetcontact;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;

public class NumeroViewModel extends AndroidViewModel {
    private NumeroRepository mRepository;

    private LiveData<List<Numero>> mAllNumeros;

    public NumeroViewModel(Application application){
        super(application);
        mRepository = new NumeroRepository(application);
        mAllNumeros = mRepository.getAllNumeros();
    }

    LiveData<List<Numero>> getmAllNumeros(){ return  mAllNumeros;}

    public void insert(Numero numero){ mRepository.insert(numero);}

    MutableLiveData<List<Numero>>  getAllNumeroForAContact(int id){ return mRepository.getAllNumeroForAContact(id);}


}
