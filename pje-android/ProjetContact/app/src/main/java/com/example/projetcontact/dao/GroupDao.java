package com.example.projetcontact.dao;

import com.example.projetcontact.objet.Groups;

import androidx.lifecycle.LiveData;
import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Groups group);

    @Update
    void updateContact(Groups... groups);

    @Query("DELETE FROM contact_table")
    void deleteAll();

    @Query("SELECT * from contact_table ORDER BY nom ASC")
    LiveData<List<Groups>> getAllWords();

    @Delete
    void delete(Groups group);
}
