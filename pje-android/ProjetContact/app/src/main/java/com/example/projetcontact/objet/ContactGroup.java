package com.example.projetcontact.objet;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "contact_group_table",
        primaryKeys = {"contactId","groupId"},
        foreignKeys = {
            @ForeignKey(entity = Contact.class,
                        parentColumns = "id",
                        childColumns = "contactId"),
             @ForeignKey(entity = Groups.class,
                        parentColumns = "id",
                        childColumns = "groupId")
        })
public class ContactGroup {
    public int contactId;
    public int groupId;

    public ContactGroup(){
        this.contactId = 0;
        this.groupId = 0;
    }

    public ContactGroup(int c, int g){
        this.contactId = c;
        this.groupId = g;
    }

    public int getContactId(){return this.contactId;}
    public int getGroupId(){return this.groupId;}

    public void setContactId(int c){this.contactId = c;}
    public void setGroupId(int c){this.groupId = c;}
}
