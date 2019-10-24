package com.example.projetcontact.view.contact;

import android.app.Application;
import android.os.AsyncTask;

import com.example.projetcontact.ContactRoomDatabase;
import com.example.projetcontact.dao.ContactDao;
import com.example.projetcontact.objet.Contact;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ContactRepository {

    private ContactDao mContactDao;
    private LiveData<List<Contact>> mAllContacts;

    ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        mContactDao = db.contactDao();
        mAllContacts = mContactDao.getmAllContacts();
    }

    LiveData<List<Contact>> getmAllContacts() {
        return mAllContacts;
    }


    public void insert (Contact contact) {
        new insertAsyncTask(mContactDao).execute(contact);
    }

    public void delete (Contact contact) {
        new deleteAsyncTask(mContactDao).execute(contact);
    }

    private static class insertAsyncTask extends AsyncTask<Contact, Void, Void> {

        private ContactDao mAsyncTaskDao;

        insertAsyncTask(ContactDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Contact... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Contact, Void, Void> {

        private ContactDao mAsyncTaskDao;

        deleteAsyncTask(ContactDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Contact... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
