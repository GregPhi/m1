package com.example.projetcontact.dao;

import com.example.projetcontact.objet.Contact;
import com.example.projetcontact.objet.Numero;

import java.util.List;

import androidx.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NumeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Numero numero);

    @Update
    void update(Numero numero);

    @Query("DELETE FROM numero_table")
    void deleteAll();

    @Query("SELECT * FROM numero_table")
    List<Numero> getNumeros();

    @Query("SELECT * FROM numero_table")
    LiveData<List<Numero>> getAllNumeros();

    @Query("SELECT * FROM numero_table WHERE contact_id =:id")
    LiveData<List<Numero>>  getAllNumeroForAContact(int id);

    @Delete
    void delete(Numero numero);
}
