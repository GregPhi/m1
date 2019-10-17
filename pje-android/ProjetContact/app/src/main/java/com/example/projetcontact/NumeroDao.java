package com.example.projetcontact;

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
    void updateNumero(Numero... numeros);

    @Query("DELETE FROM numero_table")
    void deleteAll();

    @Query("SELECT * FROM numero_table")
    LiveData<List<Numero>> getAllNumeros();

    @Query("SELECT * FROM numero_table WHERE contact_id LIKE :id")
    LiveData<List<Numero>>  getAllNumeroForAContact(int id);

    @Delete
    void delete(Numero numero);

}
