package com.example.pockemonapp.db;
import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;





@Module //My MOdule
@InstallIn(ApplicationComponent.class) //will work on all my application


public class DatabaseModule {

    //this function will provide me with database builder



    @Provides
    @Singleton
    public static PokemonDB provideDb(Application application ){

        return Room.databaseBuilder(application,PokemonDB.class,"fav_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

    }

    @Provides
    @Singleton
    public static PockemonDao providDao(PokemonDB pokemonDB){

        return pokemonDB.pockemonDao();
    }

}
