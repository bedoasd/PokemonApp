package com.example.pockemonapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pockemonapp.model.Pockemon;

import java.util.List;

@androidx.room.Dao
public interface PockemonDao {   //the opertions


    @Insert
    public void insertPockemon(Pockemon pockemon);


    @Query("delete from fav_table where name =:pockemonName")
    public void deletePockemon(String pockemonName);


    @Query("select * from fav_table")
    public LiveData <List<Pockemon>> getPokemons();


}
