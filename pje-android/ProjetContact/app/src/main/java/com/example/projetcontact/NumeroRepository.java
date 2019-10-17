package com.example.projetcontact;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;
import java.util.ArrayList;
import java.util.NavigableMap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NumeroRepository {
    public static LiveData<List<Numero>> mNumerosForId;
    private NumeroDao mNumeroDao;
    private LiveData<List<Numero>> mAllNumeros;

    NumeroRepository(Application application){
        NumeroRoomDatabase db = NumeroRoomDatabase.getDatabase(application);
        mNumeroDao = db.numeroDao();
        mAllNumeros = mNumeroDao.getAllNumeros();
    }

    LiveData<List<Numero>> getAllNumeros(){ return mAllNumeros;}

    public void insert (Numero numero){ new insertAsyncTask(mNumeroDao).execute(numero);}

    LiveData<List<Numero>> getAllNumeroForAContact(Contact contact) {
        new getNumeroForId(mNumeroDao).execute(contact);
        return mNumerosForId;
    }

    /*ArrayList<Numero> res = new ArrayList<Numero>();
        List<Numero> ns = mAllNumeros.getValue();
        for(Numero n : ns){
            if(n.getContactId() == id){
                res.add(n);
            }
        }
        MutableLiveData<List<Numero>> r = new MutableLiveData<List<Numero>>();
        r.setValue(res);
        return r;*/

    private static class insertAsyncTask extends AsyncTask<Numero, Void, Void> {

        private NumeroDao mAsyncTaskDao;

        insertAsyncTask(NumeroDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Numero... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class getNumeroForId extends AsyncTask<Contact, Void, Void> {

        private NumeroDao mAsyncTaskDao;

        getNumeroForId(NumeroDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Contact... params) {
            NumeroRepository.mNumerosForId = mAsyncTaskDao.getAllNumeroForAContact(params[0].getId());
            return null;
        }
    }
}
