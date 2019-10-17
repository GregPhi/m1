package com.example.projetcontact;

import android.app.Application;
import android.os.AsyncTask;
import java.util.List;
import androidx.lifecycle.LiveData;

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
        mNumerosForId = mNumeroDao.getAllNumeroForAContact(contact.getId());
        return  mNumerosForId;
    }

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
}
