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
    private List<ContactGroup> mAllContactGroup;
    private LiveData<List<Contact>> mAllContacts;
    private LiveData<List<Groups>> mAllGroups;


    ContactGroupRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        mDao = db.contact_groupDao();
        mAllContactGroup = mDao.getAllContactGroup();
    }

    List<ContactGroup> getmAllContactGroup(){ return mAllContactGroup; }

    LiveData<List<Contact>> getContactsForGroup(final int gId) {
        mAllContacts = mDao.getContactsForGroup(gId);
        return mAllContacts;
    }

    LiveData<List<Groups>> getGroupsForContact(final int cId) {
        mAllGroups = mDao.getGroupsForContact(cId);
        return mAllGroups;
    }

    List<ContactGroup> getListGroupsForContact(final int cId) {
        return mDao.getListGroupsForContact(cId);
    }

    List<ContactGroup> getListContactsForGroup(final int gId) {
        return mDao.getListContactsForGroup(gId);
    }

    public void deleteGroupsJoinForContact(Contact contact){
        List<ContactGroup> groupsJ = getListGroupsForContact(contact.getId());
        new deleteGpJFContAsyncTask(mDao,groupsJ).execute();
    }

    public void deleteContactsJoinForGroup(Groups groups){
        List<ContactGroup> groupsJ = getListContactsForGroup(groups.getId());
        new deleteCtJFGpAsyncTask(mDao,groupsJ).execute();
    }

    public void insert (ContactGroup ctgrp) { new ContactGroupRepository.insertAsyncTask(mDao).execute(ctgrp); }

    public void delete (ContactGroup ctgrp) { new ContactGroupRepository.deleteAsyncTask(mDao).execute(ctgrp); }

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

    private static class deleteGpJFContAsyncTask extends AsyncTask<Void, Void, Void>{
        private ContactGroupDao mAsyncTaskDao;
        private List<ContactGroup> groupsJ;

        deleteGpJFContAsyncTask(ContactGroupDao dao, List<ContactGroup> n) {
            mAsyncTaskDao = dao;
            groupsJ = n;
        }

        @Override
        protected Void doInBackground(final Void... params){
            for(ContactGroup n : groupsJ){
                new ContactGroupRepository.deleteAsyncTask(mAsyncTaskDao).execute(n);
            }
            return null;
        }
    }

    private static class deleteCtJFGpAsyncTask extends AsyncTask<Void, Void, Void>{
        private ContactGroupDao mAsyncTaskDao;
        private List<ContactGroup> groupsJ;

        deleteCtJFGpAsyncTask(ContactGroupDao dao, List<ContactGroup> n) {
            mAsyncTaskDao = dao;
            groupsJ = n;
        }

        @Override
        protected Void doInBackground(final Void... params){
            for(ContactGroup n : groupsJ){
                new ContactGroupRepository.deleteAsyncTask(mAsyncTaskDao).execute(n);
            }
            return null;
        }
    }
}
