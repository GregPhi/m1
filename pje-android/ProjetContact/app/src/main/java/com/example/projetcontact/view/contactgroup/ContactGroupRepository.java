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

    public void deleteGroupsJoinForContact(Contact contact){
        List<ContactGroup> groupsJ = getmAllContactGroup();
        new deleteGpJFContAsyncTask(mDao,contact,groupsJ).execute();
    }

    public void deleteContactsJoinForGroup(Groups groups){
        List<ContactGroup> groupsJ = getmAllContactGroup();
        new deleteCtJFGpAsyncTask(mDao,groups,groupsJ).execute();
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
        private Contact contact;
        private List<ContactGroup> groupsJ;

        deleteGpJFContAsyncTask(ContactGroupDao dao, Contact c, List<ContactGroup> n) {
            mAsyncTaskDao = dao;
            contact = c;
            groupsJ = n;
        }

        @Override
        protected Void doInBackground(final Void... params){
            this.del();
            return null;
        }

        protected void del() {
            for(ContactGroup n : groupsJ){
                if(n.getContactId() == contact.getId()){
                    mAsyncTaskDao.delete(n);
                }
            }
        }
    }

    private static class deleteCtJFGpAsyncTask extends AsyncTask<Void, Void, Void>{
        private ContactGroupDao mAsyncTaskDao;
        private Groups groups;
        private List<ContactGroup> groupsJ;

        deleteCtJFGpAsyncTask(ContactGroupDao dao, Groups c, List<ContactGroup> n) {
            mAsyncTaskDao = dao;
            groups = c;
            groupsJ = n;
        }

        @Override
        protected Void doInBackground(final Void... params){
            this.del();
            return null;
        }

        protected void del() {
            for(ContactGroup n : groupsJ){
                if(n.getGroupId() == groups.getId()){
                    mAsyncTaskDao.delete(n);
                }
            }
        }
    }
}
