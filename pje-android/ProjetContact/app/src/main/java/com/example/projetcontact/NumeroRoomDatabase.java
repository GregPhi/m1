package com.example.projetcontact;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Numero.class, Contact.class}, version = 1)
public abstract class NumeroRoomDatabase extends RoomDatabase {
    public abstract NumeroDao numeroDao();

    private static volatile NumeroRoomDatabase INSTANCE;

    static NumeroRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (NumeroRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NumeroRoomDatabase.class, "numero_database")
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

        private final NumeroDao mDao;

        PopulateDbAsync(NumeroRoomDatabase db) {
            mDao = db.numeroDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            Numero n1 = new Numero("0750406728","Moi",1);
            mDao.insert(n1);
            return null;
        }
    }
}
