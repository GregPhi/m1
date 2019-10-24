package com.example.projetcontact.view.group;

import android.app.Application;
import android.os.AsyncTask;

import com.example.projetcontact.ContactRoomDatabase;
import com.example.projetcontact.dao.GroupDao;
import com.example.projetcontact.dao.NumeroDao;
import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Groups;
import com.example.projetcontact.objet.Numero;
import com.example.projetcontact.view.numero.NumeroRepository;

import java.util.List;

import androidx.lifecycle.LiveData;

public class GroupRepository {
    private GroupDao mGroupDao;
    private LiveData<List<Groups>> mAllGroups;

    GroupRepository(Application application){
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        mGroupDao = db.groupDao();
        mAllGroups = mGroupDao.getAllGroups();
    }

    LiveData<List<Groups>> getAllGroups(){ return mAllGroups;}

    public void insert (Groups groups){ new GroupRepository.insertAsyncTask(mGroupDao).execute(groups);}

    public void delete (Groups groups) { new GroupRepository.deleteAsyncTask(mGroupDao).execute(groups);}

    private static class insertAsyncTask extends AsyncTask<Groups, Void, Void> {

        private GroupDao mAsyncTaskDao;

        insertAsyncTask(GroupDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Groups... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Groups, Void, Void> {

        private GroupDao mAsyncTaskDao;

        deleteAsyncTask(GroupDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Groups... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
