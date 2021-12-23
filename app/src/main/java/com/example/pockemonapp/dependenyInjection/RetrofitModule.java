package com.example.pockemonapp.dependenyInjection;

import com.example.pockemonapp.network.PockemonApiService;

import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module  //cause we not write the class of retrofit or w haven'tcontrol; on it
@InstallIn(ApplicationComponent.class)  // to use on all application
public class RetrofitModule {

    @Provides
    @Singleton
    public static PockemonApiService providepockemonApiService(){
        return new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //to use in background with RX Java .
                .build()
                .create(PockemonApiService.class);


    }

}
