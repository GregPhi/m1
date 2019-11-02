package com.example.projetcontact;

import android.content.Context;
import android.os.AsyncTask;

import com.example.projetcontact.dao.ContactDao;
import com.example.projetcontact.dao.ContactGroupDao;
import com.example.projetcontact.dao.GroupDao;
import com.example.projetcontact.dao.NumeroDao;
import com.example.projetcontact.objet.Address;
import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.ContactGroup;
import com.example.projetcontact.objet.Groups;
import com.example.projetcontact.objet.Numero;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Contact.class, Numero.class, Groups.class, ContactGroup.class}, version = 4)
public abstract class ContactRoomDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
    public abstract NumeroDao numeroDao();
    public abstract GroupDao groupDao();
    public abstract ContactGroupDao contact_groupDao();

    private static volatile ContactRoomDatabase INSTANCE;

    public static ContactRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class, "contact_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback =
            new Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ContactDao mDao;
        private final NumeroDao numDao;
        private final GroupDao groupDao;
        private final ContactGroupDao contact_groupDao;

        PopulateDbAsync(ContactRoomDatabase db) {
            mDao = db.contactDao();
            numDao = db.numeroDao();
            groupDao = db.groupDao();
            contact_groupDao = db.contact_groupDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            numDao.deleteAll();
            contact_groupDao.deleteAll();
            mDao.deleteAll();
            groupDao.deleteAll();

            Contact c1 = new Contact("Pelage","FX", "666");
            Address a1 = new Address("avenue paul langevin","Villeneuve d'Ascq","59650");
            c1.setAddr(a1);
            c1.setId(2);
            mDao.insert(c1);
            Contact c2 = new Contact("Test","Greg", "42");
            Address a2 = new Address("adress test","St-André","59350");
            c2.setAddr(a2);
            c2.setId(1);
            mDao.insert(c2);
            Numero n1 = new Numero("070000000000","Moi",c2.getId());
            numDao.insert(n1);
            Contact c3 = new Contact("Test","Mms", "00000");
            Address a3 = new Address("adress test","St-André","59350");
            c3.setAddr(a3);
            c3.setId(3);
            mDao.insert(c3);
            Numero n3 = new Numero("060000000000","Mams",c3.getId());
            numDao.insert(n3);

            Groups g1 = new Groups("Fac");
            g1.setId(50);
            groupDao.insert(g1);
            Groups g2 = new Groups("Famille");
            g2.setId(51);
            groupDao.insert(g2);

            ContactGroup cg1 = new ContactGroup(c1.getId(),g1.getId());
            contact_groupDao.insert(cg1);
            ContactGroup cg2 = new ContactGroup(c3.getId(),g2.getId());
            contact_groupDao.insert(cg2);

            return null;
        }
    }
}
