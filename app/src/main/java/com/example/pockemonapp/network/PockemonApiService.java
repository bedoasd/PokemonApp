package com.example.pockemonapp.network;

import com.example.pockemonapp.model.PockemonResponce;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PockemonApiService {

    @GET("pokemon")
    Observable<PockemonResponce> getpokemons();


}
