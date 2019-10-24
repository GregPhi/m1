package com.example.projetcontact.view.contactgroup;

import android.app.Application;
import android.os.AsyncTask;

import com.example.projetcontact.ContactRoomDatabase;
import com.example.projetcontact.dao.ContactDao;
import com.example.projetcontact.dao.ContactGroupDao;
import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Groups;
import com.example.projetcontact.objet.ContactGroup;
import com.example.projetcontact.view.contact.ContactRepository;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ContactGroupRepository {
    private ContactGroupDao mDao;
    private LiveData<List<Contact>> mAllContacts;
    private LiveData<List<Groups>> mAllGroups;


    ContactGroupRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        mDao = db.contact_groupDao();
    }

    LiveData<List<Contact>> getContactsForGroup(final int gId) {
        mAllContacts = mDao.getContactsForGroup(gId);
        return mAllContacts;
    }

    LiveData<List<Groups>> getGroupsForContact(final int cId) {
        mAllGroups = mDao.getGroupsForContact(cId);
        return mAllGroups;
    }

    public void insert (ContactGroup ctgrp) {
        new ContactGroupRepository.insertAsyncTask(mDao).execute(ctgrp);
    }

    public void delete (ContactGroup ctgrp) {
        new ContactGroupRepository.deleteAsyncTask(mDao).execute(ctgrp);
    }

    private static class insertAsyncTask extends AsyncTask<ContactGroup, Void, Void> {

        private ContactGroupDao mAsyncTaskDao;

        insertAsyncTask(ContactGroupDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ContactGroup... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<ContactGroup, Void, Void> {

        private ContactGroupDao mAsyncTaskDao;

        deleteAsyncTask(ContactGroupDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ContactGroup... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
