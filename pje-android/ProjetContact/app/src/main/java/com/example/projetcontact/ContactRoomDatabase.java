package com.example.projetcontact;

import android.content.Context;
import android.os.AsyncTask;

import com.example.projetcontact.dao.ContactDao;
import com.example.projetcontact.dao.NumeroDao;
import com.example.projetcontact.objet.Address;
import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Numero;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Contact.class, Numero.class}, version = 2)
public abstract class ContactRoomDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
    public abstract NumeroDao numeroDao();

    private static volatile ContactRoomDatabase INSTANCE;

    static ContactRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class, "contact_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
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

        PopulateDbAsync(ContactRoomDatabase db) {
            mDao = db.contactDao();
            numDao = db.numeroDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            numDao.deleteAll();
            mDao.deleteAll();
            Contact c1 = new Contact("Pelage","FX", "666");
            Address a1 = new Address("avenue paul langevin","Villeneuve d'Ascq","59650");
            c1.setAddr(a1);
            mDao.insert(c1);
            Contact c2 = new Contact("Philippot","Gregoire", "42");
            Address a2 = new Address("135 av Tassigny","St-André","59350");
            c2.setAddr(a2);
            c2.setId(1);
            mDao.insert(c2);
            Numero n1 = new Numero("0750406728","Moi",c2.getId());
            numDao.insert(n1);
            return null;
        }
    }
}
