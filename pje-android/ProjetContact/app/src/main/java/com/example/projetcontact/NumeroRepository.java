package com.example.projetcontact;

import android.app.Application;
import android.os.AsyncTask;

import com.example.projetcontact.dao.NumeroDao;
import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Numero;

import java.util.List;
import androidx.lifecycle.LiveData;

public class NumeroRepository {
    public static LiveData<List<Numero>> mNumerosForId;
    private NumeroDao mNumeroDao;
    private LiveData<List<Numero>> mAllNumeros;

    NumeroRepository(Application application){
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        mNumeroDao = db.numeroDao();
        mAllNumeros = mNumeroDao.getAllNumeros();
    }

    LiveData<List<Numero>> getAllNumeros(){ return mAllNumeros;}

    LiveData<List<Numero>> getAllNumeroForAContact(Contact contact) {
        mNumerosForId = mNumeroDao.getAllNumeroForAContact(contact.getId());
        return  mNumerosForId;
    }

    public void insert (Numero numero){ new insertAsyncTask(mNumeroDao).execute(numero);}

    public void delete (Numero numero) { new deleteAsyncTask(mNumeroDao).execute(numero);}

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

    private static class deleteAsyncTask extends AsyncTask<Numero, Void, Void> {

        private NumeroDao mAsyncTaskDao;

        deleteAsyncTask(NumeroDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Numero... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
