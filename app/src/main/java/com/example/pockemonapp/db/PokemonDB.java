package com.example.pockemonapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pockemonapp.model.Pockemon;


@Database(entities = Pockemon.class,version = 1,exportSchema = false)
public abstract class  PokemonDB extends RoomDatabase {

    public abstract PockemonDao pockemonDao();
    /*
                        Room(DATABASE)wITH DAGGER
    because iam not the owner of this class "Database (Room)" Room is the owner
    i can't put the annotation @inject on it's constructor so i will Create a module to provides me
    with the build of db & dao

     */

}
