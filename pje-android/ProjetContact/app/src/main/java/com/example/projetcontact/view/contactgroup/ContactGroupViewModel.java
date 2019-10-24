package com.example.projetcontact.view.contactgroup;

import android.app.Application;

import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Groups;
import com.example.projetcontact.objet.ContactGroup;
import com.example.projetcontact.view.contact.ContactRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ContactGroupViewModel extends AndroidViewModel {
    private ContactGroupRepository mRepository;
    private List<ContactGroup> mAllContactGroup;

    public ContactGroupViewModel(Application application) {
        super(application);
        mRepository = new ContactGroupRepository(application);
        mAllContactGroup = mRepository.getmAllContactGroup();
    }

    public List<ContactGroup> getmAllContactGroup(){ return mAllContactGroup; }

    public void insert(ContactGroup ctgrp) { mRepository.insert(ctgrp); }
    public void delete(ContactGroup ctgrp) { mRepository.delete(ctgrp); }

    public LiveData<List<Contact>> getContactsForGroup(final int gId){return mRepository.getContactsForGroup(gId);}
    public LiveData<List<Groups>> getGroupsForContact(final int cId){return mRepository.getGroupsForContact(cId);}

    public void deleteGroupsJoinForContact(Contact contact){mRepository.deleteGroupsJoinForContact(contact);}
    public void deleteContactsJoinForGroup(Groups groups){mRepository.deleteContactsJoinForGroup(groups);}
}
