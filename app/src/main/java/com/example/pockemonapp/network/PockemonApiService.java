package com.example.pockemonapp.network;

import android.database.Observable;

import com.example.pockemonapp.model.PockemonResponce;

import retrofit2.http.GET;

public interface PockemonApiService {

    @GET("pokemon")
    Observable<PockemonResponce> getpokemons();


}
