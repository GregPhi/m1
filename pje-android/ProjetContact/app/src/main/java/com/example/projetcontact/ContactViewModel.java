package com.example.projetcontact;

import android.app.Application;

import com.example.projetcontact.objet.Contact;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository mRepository;

    private LiveData<List<Contact>> mAllContacts;

    public ContactViewModel(Application application) {
        super(application);
        mRepository = new ContactRepository(application);
        mAllContacts = mRepository.getAllWords();
    }

    LiveData<List<Contact>> getmAllContacts() { return mAllContacts; }

    public void insert(Contact contact) { mRepository.insert(contact); }

    public void updateContact(Contact... contacts){
        mRepository.delete(contacts[0]);
        mRepository.insert(contacts[0]);
    }

    public void delete(Contact contact) { mRepository.delete(contact); }

}
