package com.example.projetcontact.dao;

import com.example.projetcontact.objet.Groups;

import androidx.lifecycle.LiveData;
import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Groups group);

    @Query("DELETE FROM groupe_table")
    void deleteAll();

    @Query("SELECT * from groupe_table ORDER BY nom ASC")
    LiveData<List<Groups>> getAllGroups();

    @Delete
    void delete(Groups group);
}
