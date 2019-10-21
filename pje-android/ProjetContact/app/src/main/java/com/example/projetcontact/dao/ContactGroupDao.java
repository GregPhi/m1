package com.example.projetcontact.dao;

import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Groups;
import com.example.projetcontact.objet.ContactGroup;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
public interface ContactGroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ContactGroup contactGroup);

    @Delete
    void delete(ContactGroup... contactGroups);

    @Query("SELECT * from contact_table "+
           "INNER JOIN contact_group_table "+
           "ON contact_table.id=contact_group_table.contactId "+
           "WHERE contact_group_table.groupId =:gId")
    List<Contact> getContactsForGroup(final int gId);

    @Query("SELECT * from groupe_table "+
            "INNER JOIN contact_group_table "+
            "ON groupe_table.id=contact_group_table.groupId "+
            "WHERE contact_group_table.groupId =:cId")
    List<Groups> getGroupsForContact(final int cId);
}
